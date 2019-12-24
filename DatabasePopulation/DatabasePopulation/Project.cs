using System;
using System.Collections.Generic;
using System.Text;

namespace DatabasePopulation
{
    public class Project
    {
        //We'll use a static id as a counter. Each new instance's myID will be defined by the current id,
        //and then the id will be incremented again. 
        //TODO: Replace this system with an alphanumeric id system with the same concept in place. 
        static int id = 0;
        int myId;
        DateTime deadline; //"yyyy-MM-dd HH:mm:ss" required for ToString for mySQL compatibility
        string description;
        List<TaskTree> tasks;
        public Project()
        {
            myId = -1;
            deadline = DateTime.Today;
            description = "I don't know how this got here.";
            //Generate the task trees for the project.
            Random rand = new Random();
            int taskLimit = rand.Next(5, 20);
            WordListManager words = new WordListManager();
            for (int i = 0; i < taskLimit; i++)
                this.tasks.Add(new TaskTree(words, DateTime.Today, words.getRandomWords(rand.Next(250, 500)), this.getPK()));
        }
        //A project minimally requires a deadline and a description. 
        public Project(DateTime deadline, string description)
        {
            myId = id++;
            this.deadline = deadline;
            this.description = description;
            //Generate the task trees for the project.
            this.tasks = new List<TaskTree>();
            Random rand = new Random();           
            int taskLimit = rand.Next(5, 20);
            WordListManager words = new WordListManager();
            for (int i = 0; i < taskLimit; i++)
                this.tasks.Add(new TaskTree(words, DateTime.Today, words.getRandomWords(rand.Next(250, 500)), this.getPK()));
        }
        //This generates the tuple required for the insert query string. 
        public string getAddTupleQuerryString()
        {
            return "(" +myId + ",\"" + deadline.ToString("yyyy-MM-dd HH:mm:ss") + "\",\"" + description+ "\")";
        }
        //This provides the primary key for use in generating relation records. 
        public string getPK()
        {
            return "\"" + this.myId + "\"";
        }
        //Generate a Project Assigned record string
        public string getProjectAssignedAddString(Account assignee)
        {
            return "(" + assignee.getPK() + "," + this.getPK() + ")";
        }
        //Give the Task Trees to the requester.
        public List<TaskTree> getTaskTrees()
        {
            return this.tasks;
        }
    }
}
