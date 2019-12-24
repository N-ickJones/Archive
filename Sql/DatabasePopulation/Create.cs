using System;
using System.Collections.Generic;
using System.Text;

namespace DatabasePopulation
{
    public class Create
    {
        Account account;
        Project project;
        Organization organization;
        //A create record is a relationship between an accounr, a project, and an organization documenting
        //Who at what organization created what project. 
        public Create(Account account, Project project, Organization organization)
        {
            this.account      = account;
            this.project      = project;
            this.organization = organization;
        }
        //The insert tuple generated for a create record is composed of primary keys. This creates that record.
        public string getAddTupleQuerryString()
        {
            return "(" + this.account.getPK() + "," + this.organization.getPK() + "," + this.project.getPK() + ")";
        }


    }
}
