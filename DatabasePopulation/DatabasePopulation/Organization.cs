using System;
using System.Collections.Generic;
using System.Text;

namespace DatabasePopulation
{
    public class Organization
    {
        string name, license;
        //VERY IMPORTANT: "yyyy-MM-dd HH:mm:ss" is the DateTime ToString argument for MySQL compatibility.
        DateTime activation;
        DateTime expiration;
        //All we need from the generator is a name and a license. 
        public Organization(string name, string license)
        {
            this.name = name;
            this.license = license;
            Random rand = new Random();
            
            //I'm generating the dates using my birthday as an offset, just for fun. 
            DateTime temp = new DateTime(1993, 11, 16);
            int limit = (DateTime.Today - temp).Days;
            this.activation = temp.AddDays(rand.Next(limit));
            //Licenses last between 1 and 5 years. 
            this.expiration = activation.AddDays(rand.Next(365, 365 * 5 + 1));
        }
        //This generates the tuple necessary for the insert query string. 
        public string getAddTupleQuerryString()
        {
            return "(\"" + this.name + "\",\"" +  this.license + "\",\"" + this.activation.ToString("yyyy-MM-dd HH:mm:ss") + "\",\"" +  this.expiration.ToString("yyyy-MM-dd HH:mm:ss") + "\")";
        }
        //This is required for the create record generation in CreatesGenerator
        public string getPK()
        {
            return "\"" + this.name + "\"";
        }
    }
}
