using System;
using System.Collections.Generic;
using System.Text;

namespace DatabasePopulation
{
    public class Account
    {
        string username;
        string email;
        string firstName;
        string lastName;
        string password;
        string skills;
        string theme;
        string picturePath;
        //The Account for generation only needs a first name, last name and password. Everything else applied by default
        //is generated from those where necessary, or left blank where permitted. 
        public Account(string firstName, string lastName, string password)
        {
            this.firstName = firstName;
            this.lastName = lastName;
            this.password = password;
            this.username = firstName.Substring(0,5) + lastName.Substring(0, 5);
            this.email = username + "@notreal.com";
            this.theme = "";
            this.picturePath = "";
        }
        //It may be convenient at some point to have the functionality to randomly populate themes. 
        public void setTheme(string theme)
        {
            this.theme = theme;
        }
        //It may be convenient at some point to have the functionality to randomly populate skills. 
        public void setSkills(string skills)
        {
            this.skills = skills;
        }
        //It may be convenient at some point to have the functionality to randomly populate picture paths. 
        public void setPicturePath(string picturePath)
        {
            this.picturePath = picturePath;
        }
        //This generates the tuple necessary for the insert query in the proper field order. 
        public string getAddTupleQuerryString()
        {
            return "(\""+username + "\",\"" + email + "\",\"" + firstName + "\",\"" + lastName + "\",\"" +  password + "\",\""  +  skills + "\",\"" +  theme + "\",\"" + picturePath +"\")";
        }
        //This pulls the information from the class consistent with the table's primary key in the database. 
        public string getPK()
        {
            return "\"" + this.username + "\",\"" + this.email + "\"";
        }
    }
}
