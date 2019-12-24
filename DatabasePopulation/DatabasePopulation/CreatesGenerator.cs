using System;
using System.Collections.Generic;
using System.Text;

namespace DatabasePopulation
{
    class CreatesGenerator
    {
        WordListManager words;
        Random rand;
        public CreatesGenerator()
        {
            this.words = new WordListManager();
            this.rand = new Random();
        }
        public CreatesGenerator(WordListManager words)
        {
            this.words = words;
            this.rand = new Random();
        }
        //This takes an SQLInterface instance from within SQLInterface's function so that it 
        //can consistently construct an organization, accounts within that organization, and
        public List<Create> generateCreateSet(SQLInterface sql)
        {
            //Crude hack: Make one organization and put it in the database.
            Organization  o = sql.generateOrganizations(1)[0];
            //Create a random number of accounts, and put them in the database.
            List<Account> a = sql.generateAccounts(rand.Next(3, 20));
            //Create a random number of projects, and put them in the database. 
            List<Project> p = sql.generateProjects(rand.Next(2, 7));
            List<Create>  c = new List<Create>();
            //Associate every project created with a random account, and with the organization from before.
            foreach(Project project in p)
            {
                int choice = rand.Next(a.Count);
                c.Add(new Create(a[choice], project, o));
                sql.registerAssignments(a, project, a[choice]);
                sql.addTaskHierarchyTuples(project.getTaskTrees());
            }
            //Return this list of create records to SQLInterface for storage. 
            return c;

        }
    }
}
