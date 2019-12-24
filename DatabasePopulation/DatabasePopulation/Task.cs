using System;
using System.Collections.Generic;
using System.Text;

namespace DatabasePopulation
{
    public class Task
    {
        //We'll use a static id as a counter. Each new instance's myID will be defined by the current id,
        //and then the id will be incremented again. 
        //TODO: Replace this system with an alphanumeric id system with the same concept in place. 
        static long id;
        long myId;
        string description;
        DateTime deadline; //"yyyy-MM-dd HH:mm:ss" required for ToString for mySQL compatibility

        public Task(string description, DateTime deadline)
        {
            this.description = description;
            this.deadline    = deadline;
            this.myId = id++;
        }
        //Generate the value tuple for the insert query string
        public string getAddTupleQuerryString()
        {
            return "(\"" + this.myId + "\", \"" + this.deadline.ToString("yyyy-MM-dd HH:mm:ss") + "\",null,\"" + this.description + "\")";
        }
        //Return primary key
        public string getPK()
        {
            return "\"" + this.myId + "\"";
        }
        //Generate a Task Assigned record string
        public string getTaskAssignedAddString(Account assignee)
        {
            return "(" + assignee.getPK() + ","  + this.getPK() + ")";
        }
    }
}
