using System;
using System.Collections.Generic;
using System.Text;

namespace DatabasePopulation
{
    //The WordListManage is the class responsible for managing the wordlist used in all of the
    //random text field population throughout the whole of the record generation process. 
    public class WordListManager
    {
        string[] words;
        //When an instance of the WordListManager is initialized, it populates the words list with the contents
        //of the 2000 word text file. 
        public WordListManager()
        {
            words = System.IO.File.ReadAllLines("G:\\Users\\Shenzao\\Documents\\College\\Software Engineering\\Group Project\\SoftwareEngineeringProject\\DatabasePopulation\\DatabasePopulation\\wordlist.txt");
        }
        //getName returns a word composed of two words for the purpose of generating names of minimally 5 characters
        //in length. It also increases the number of names we can get out of the random number generator without
        //risking collisions. 
        public string getName()
        {
            string word1 = getRandomWord();
            string word2 = getRandomWord();
            word1 = word1.ToUpper()[0] + word1.Substring(1,word1.Length-1);
            word2 = word2.ToUpper()[0] + word2.Substring(1, word2.Length - 1);
            return word1 + word2;
        }
        //Get a random word from the word list. 
        private string getRandomWord()
        {
            Random rand = new Random();
            return words[rand.Next(words.Length)];
        }
        //Get a predetermined number of words from the database. 
        //This is predominantly used for populating descriptive fields. 
        public string getRandomWords(int howMany)
        {
            Random rand = new Random();
            string nonsense = "";
            for (int i = 0; i < howMany; i++)
            {
                nonsense += words[rand.Next(words.Length)] + " ";
            }
            return nonsense;
        }
    }
}
