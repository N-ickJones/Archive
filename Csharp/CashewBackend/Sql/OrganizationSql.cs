using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Data;
using System.Diagnostics;

namespace KarbonWebForms.Sql
{
    public class OrganizationSql
    {
        private readonly MySqlFunctions functions = new MySqlFunctions();
        private readonly string Table = "organization";

        /// <summary>
        /// Inserts Organization into Sql Database
        /// </summary>
        /// <param name="organization">Organization Object</param>
        public void Insert(Organization organization)
        {
            if (!Exists(organization.Name))
            {
                string query =
                    $"INSERT INTO {Table} (Name, License, Activation, Expiration)" +
                    $" VALUES(@name, @license, @activation, @expiration);";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("name", MySqlDbType.VarChar) { Value = organization.Name },
                    new MySqlParameter("license", MySqlDbType.VarChar) { Value = organization.License },
                    new MySqlParameter("activation", MySqlDbType.DateTime) { Value = organization.Activation },
                    new MySqlParameter("expiration", MySqlDbType.DateTime) { Value = organization.Expiration },
                };
                if (functions.ExecuteNonQuery(query, parameters))
                {
                    Debug.WriteLine("InsertOrganization: The organization was added successfully.");
                }
                else
                {
                    Debug.WriteLine("InsertOrganization: An error has occured.");
                }
            }
        }

        /// <summary>
        /// Updates a field of Organization Sql Table
        /// </summary>
        /// <param name="username">PrimaryKey</param>
        /// <param name="field">Column</param>
        /// <param name="fieldValue">Column new Value</param>
        public void Update(string name, string field, string fieldValue)
        {
            if (Exists(name))
            {
                string query = $"UPDATE {Table} SET {field} = @value WHERE (Name = @name);";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("value", MySqlDbType.VarChar) { Value = fieldValue },
                    new MySqlParameter("name", MySqlDbType.VarChar) { Value =  name}
                };

                if (functions.ExecuteNonQuery(query, parameters))
                {
                    Debug.WriteLine("UpdateOrganization: The organization was updated successfully.");
                }
                else
                {
                    Debug.WriteLine("UpdateOrganization: An error has occured.");
                }
            }
        }

        /// <summary>
        /// Delete Organization using Username
        /// </summary>
        /// <param name="username"></param>
        public void Delete(string username)
        {
            Delete(new Organization(username));
        }

        /// <summary>
        /// Delete Organization using Organization Object
        /// </summary>
        /// <param name="organization"></param>
        public void Delete(Organization organization)
        {
            if (Exists(organization.Name))
            {
                string query = $"DELETE FROM {Table} WHERE Name = @name;";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("name", MySqlDbType.VarChar) { Value = organization.Name }
                };

                if (functions.ExecuteNonQuery(query, parameters))
                {
                    Debug.WriteLine("DeleteOrganization: The organization was deleted successfully.");
                }
                else
                {
                    Debug.WriteLine("DeleteOrganization: An error has occured.");
                }
            }
            else
            {
                Debug.WriteLine("DeleteOrganization: Cannot delete organization");
            }
        }

        /// <summary>
        /// Get Organization Object From Sql Database
        /// </summary>
        /// <param name="username">Primary Key</param>
        /// <returns>Organization Object</returns>
        public Organization Get(string username)
        {
            if (Exists(username))
            {
                string query =
                    $"SELECT Name, License, Activation, Expiration " +
                    $"FROM {Table} WHERE (Name = @name)";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("name", MySqlDbType.VarChar) { Value = username }
                };

                if (functions.ExecuteReader(query, parameters, out DataTable dataTable))
                {
                    DataRow row = dataTable.Rows[0];
                    Organization organization = new Organization
                    {
                        Name = row["name"].ToString(),
                        License = row["license"].ToString(),
                        Activation = Convert.ToDateTime(row["activation"]),
                        Expiration = Convert.ToDateTime(row["expiration"]),
                    };
                    return organization;
                }
                else
                {
                    Debug.WriteLine("GetOrganization: An error has occured while trying to get organization.");
                    return null;
                }
            }
            else
            {
                return null;
            }
        }

        /// <summary>
        /// Get All Entries of Organization Table
        /// </summary>
        /// <returns>List of Organization Objects</returns>
        public List<Organization> GetAll()
        {
            string query =
                $"SELECT Name, License, Activation, Expiration FROM {Table};";
            if (functions.ExecuteReader(query, null, out DataTable dataTable))
            {
                List<Organization> organization = new List<Organization>();
                foreach (DataRow row in dataTable.Rows)
                {
                    organization.Add(new Organization
                    {
                        Name = row["name"].ToString(),
                        License = row["license"].ToString(),
                        Activation = Convert.ToDateTime(row["activation"]),
                        Expiration = Convert.ToDateTime(row["expiration"]),
                    });
                }
                return organization;
            }
            else
            {
                Debug.WriteLine("GetAllOrganizations: An error has occured while trying to get all organization.");
                return null;
            }
        }

        /// <summary>
        /// Checks if an Organization Exists using Username 
        /// </summary>
        /// <param name="keyValue">Primary Key</param>
        /// <returns></returns>
        public bool Exists(string keyValue)
        {
            if (functions.CheckExists(Table, "name", keyValue))
            {
                Debug.WriteLine($"Exists: The organization exists with name: {keyValue}.");
                return true;
            }
            else
            {
                Debug.WriteLine($"Exists: The organization doesn't exists with name: {keyValue}.");
                return false;
            }
        }

    }
}
