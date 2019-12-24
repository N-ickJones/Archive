using System;
using System.Collections.Generic;
using System.Text;

namespace DatabasePopulation
{
    class ProjectGenerator
    {
        Random rand = new Random();
        WordListManager words;
        //If we don't get a WordListManager, we need to make one. 
        public ProjectGenerator()
        {
            words = new WordListManager();
        }
        //If we do get a WordListManager, then we can use that instead.
        public ProjectGenerator(WordListManager words)
        {
            this.words = words;
        }
        //This constructs a new date and description to populate a project, and then initializes and returns 
        //thew new instance to the caller.
        private Project generateProject()
        {
            DateTime deadline = DateTime.Today.AddDays(rand.Next(1200));
            string description = words.getRandomWords(rand.Next(250,500));
            return new Project(deadline, description);
        }
        //This Generates a list of projects for use in the SQL Interface. 
        public List<Project> generateProjects(int number)
        {
            List<Project> projects = new List<Project>();
            for(int i = 0; i < number; i++)
            {
                projects.Add(generateProject());
            }
            return projects;
        }
    }
}
