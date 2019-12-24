using System;
using System.Collections.Generic;
using System.Text;

namespace DatabasePopulation
{
    class AccountGenerator
    {
        WordListManager words;
        //If we don't get a WordListManager instance, we need to make one.
        public AccountGenerator()
        {
            words = new WordListManager();
        }
        //If we do get a WordListManager instance, we're happy to save the resources. 
        public AccountGenerator(WordListManager words)
        {
            this.words = words;
        }
        //This function generates a new account by collecting firstName, lastName, and password information
        //from the WordListManager. It returns an instance of the class Account based on that constructor.
        public Account newAccount()
        {
            string firstName = words.getName();
            string lastName  = words.getName();
            string password  = words.getName();
            return new Account(firstName, lastName, password);
        }
        //This function generates a list of account instances according to the number requested. 
        //This is particularly useful in SQLInterface and CreatesGenerator.
        public List<Account> generateAccounts(int number)
        {
            List<Account> accounts = new List<Account>();
            for(int i = 0; i < number; i++)
            {
                accounts.Add(newAccount());
            }
            return accounts;
        }
    }
}
