using System;
using System.Collections.Generic;
using System.Text;

namespace DatabasePopulation
{
    class OrganizationGenerator
    {
        WordListManager words;
        //If we don't get a WordListManager, we need to make one. 
        public OrganizationGenerator()
        {
            this.words = new WordListManager();
        }
        //If we get a WordListManager, we can use it instead.
        public OrganizationGenerator(WordListManager words)
        {
            this.words = words;
        }
        //This function will generate an alphanumeric license of a predetermined length. 
        private string genLicense(int length)
        {
            string s = "";
            bool upper = false;
            bool isLetter = false;
            char letter;
            Random rand = new Random();
            for(int i = 0; i < length; i++)
            {
                //Randomly populate upper and isLetter flags. 
                upper = rand.Next(20) % 2 == 0;
                isLetter = rand.Next(20) % 2 == 0;
                if (isLetter)
                {
                    if (upper)
                    {
                        //Make an upper case letter.
                        letter = Convert.ToChar(Convert.ToInt32('A') + rand.Next(25));
                    }
                    else
                    {
                        //Make a lower case letter.
                        letter = Convert.ToChar(Convert.ToInt32('a') + rand.Next(25));
                    }
                    //Add the letter that was made.
                    s += letter;
                }
                else
                {
                    //Add a number to the license. 
                    s += rand.Next(9);
                }
            }
            //Give the license to whoever wanted it.
            return s;
        }
        //Make the name.
        private string generateName()
        {
            return words.getRandomWords(2) + "Corporation";
        }
        //Generate an organization using a fresh name and license. 
        private Organization newOrganization()
        {
            return new Organization(generateName(), genLicense(12));
        }
        //Generate a predefined number of organizations using the function above. 
        public List<Organization> generateOrganizations(int number)
        {
            List<Organization> orgs = new List<Organization>();
            for(int i = 0; i < number; i++)
            {
                orgs.Add(newOrganization());
            }
            return orgs;
        }
    }
}
