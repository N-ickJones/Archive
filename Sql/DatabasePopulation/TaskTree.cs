using System;
using System.Collections.Generic;
using System.Text;

namespace DatabasePopulation
{
    public class TaskTree
    {
        static Random rand = new Random();
        WordListManager words;
        List<TaskTree> subTasks;
        Task task;
        string projectPK;
        //Time to make a new Task Tree!
        public TaskTree(WordListManager words, DateTime deadline, string description, string projectPK)
        {
            this.projectPK = projectPK;
            this.words = words;
            this.task  = new Task(description, deadline);
            subTasks   = new List<TaskTree>();
            //Decide if this is the end of the tree.
            int decider = rand.Next(100);
            bool needSubTasks = decider % 10 == 3 || decider % 5 == 2 ;
            if (needSubTasks)
            {
                //If it's not for whatever reason, time to add 1 to 3 more children.
                int howMany = rand.Next(1, 5);
                for(int i = 0; i < howMany; i++)
                {
                    //Every child is another tree. 
                    this.subTasks.Add(new TaskTree(this.words, deadline.AddDays(rand.Next(7, 365)), words.getRandomWords(rand.Next(250, 500)), this.projectPK));
                }
            }            
        }
        //TODO: Recursively collapse the tree into a set of parent records to be added to the Database.
        //TODO: Recursively collapse the tree into a set of task tuples to be added to the Database.
        public string printTree()
        {
            string outstring = "(root: " + this.task.getPK() + "children:\n";
            foreach (TaskTree tasks in this.subTasks)
                outstring += "\t" + tasks.printTree() + ",\n";
            outstring += ")";


            return outstring;
        }
        //This forwards the primary key of the task at this node in the tree.
        public string getTaskPK()
        {
            return this.task.getPK();
        }
        //This recursively builds the parent relation values for the whole tree.
        public string getParentAddTupleString()
        {
            string start =  "(" + this.task.getPK() +",";
            string total = "";
            foreach(TaskTree taskTree in this.subTasks)
            {
                total += start + taskTree.getTaskPK() + "),";
                total += taskTree.getParentAddTupleString();
            }

            return total;
        }
        //This recursively builds the builton
        public string getBuiltOnTupleString()
        {
            string addstring = "(" + this.projectPK + "," + this.task.getPK() + ")";
            foreach (TaskTree taskTree in this.subTasks)
                addstring += "," + taskTree.getBuiltOnTupleString();
            return addstring;
        }
        //This provides the Task record tuple for each task. 
        public string getTaskAddTupleString()
        {
            string addstring = this.task.getAddTupleQuerryString();
            foreach (TaskTree taskTree in this.subTasks)
                addstring += "," + taskTree.getTaskAddTupleString();
            return addstring;
        }
        //This constructs a flat list of all Tasks contained in the Task Tree. 
        public List<Task> getTaskList()
        {
            List<Task> taskList = new List<Task>();
            taskList.Add(this.task);
            foreach (TaskTree taskTree in this.subTasks)
                taskList.AddRange(taskTree.getTaskList());
            return taskList;
        }
    }
}
