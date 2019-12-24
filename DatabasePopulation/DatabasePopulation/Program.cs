using System;
using System.Collections.Generic;

namespace DatabasePopulation
{
    class Program
    {
        static void Main(string[] args)
        {
            //Make an interface.
            SQLInterface sql = new SQLInterface();
            //Clear the Database.
            sql.reset();
            //Populate the Database.
            sql.generateCreateSets(20);


            //List<TaskTree> test = new List<TaskTree>();
            //for (int i = 0; i < 5; i++)
            //    test.Add(new TaskTree(new WordListManager(), DateTime.Today, "Hogwash", "\"1\""));
            //string s = "";
            //string b = "";
            //List<Task> taskList = new List<Task>();
            //HashSet<string> taskSet = new HashSet<string>();
            //HashSet<string> taskTreeSet = new HashSet<string>();
            //foreach (TaskTree tree in test)
            //{
            //    //Console.WriteLine(tree.printTree());
            //    //s += tree.getParentAddTupleString();
            //    //b += tree.getBuiltOnTupleString() + ",";
            //    taskList.AddRange(tree.getTaskList());
            //    foreach (Task task in taskList)
            //        taskSet.Add(task.getAddTupleQuerryString());
            //    taskTreeSet.Add(tree.getAddTupleQuerryString());

            //}
            //Console.WriteLine(s);
            //Console.WriteLine(b);
            //foreach (Task task in taskList)
            //    Console.WriteLine("\t" + task.getAddTupleQuerryString());
            //Console.WriteLine("taskList     count: " + taskList.Count);
            //foreach (string ss in taskSet)
            //    Console.WriteLine("\t" + ss);
            //Console.WriteLine("taskSet      count: " + taskSet.Count);
            //foreach (string ss in taskTreeSet)
            //    Console.WriteLine("\t" + ss);
            //Console.WriteLine("taskTreeSet  count: " + taskTreeSet.Count);
        }
    }
}
