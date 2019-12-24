using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Data;
using System.Diagnostics;

namespace KarbonWebForms.Sql
{
    public class ProjectAssignedSql
    {
        private readonly MySqlFunctions functions = new MySqlFunctions();
        private readonly string Table = "projectassigned";

        /// <summary>
        /// Inserts ProjectAssigned into Sql Database
        /// </summary>
        /// <param name="projectassigned">ProjectAssigned Object</param>
        public void Insert(ProjectAssigned projectassigned)
        {
            if (!Exists(projectassigned.Username))
            {
                string query =
                    $"INSERT INTO {Table} (Username, Email, Name, ProjectId)" +
                    $" VALUES(@username, @email, @name, @projectid);";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("username", MySqlDbType.VarChar) { Value = projectassigned.Username },
                    new MySqlParameter("email", MySqlDbType.VarChar) { Value = projectassigned.Email },
                    new MySqlParameter("name", MySqlDbType.VarChar) { Value = projectassigned.Name },
                    new MySqlParameter("projectid", MySqlDbType.VarChar) { Value = projectassigned.ProjectId },

                };

                if (functions.ExecuteNonQuery(query, parameters))
                {
                    Debug.WriteLine("InsertProjectAssigned: The projectassigned was added successfully.");
                }
                else
                {
                    Debug.WriteLine("InsertProjectAssigned: An error has occured.");
                }
            }
        }

        /// <summary>
        /// Updates a field of ProjectAssigned Sql Table
        /// </summary>
        /// <param name="username">PrimaryKey</param>
        /// <param name="field">Column</param>
        /// <param name="fieldValue">Column new Value</param>
        public void Update(string username, string field, string fieldValue)
        {
            if (Exists(username))
            {
                string query = $"UPDATE {Table} SET {field} = @value WHERE (Username = @username);";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("value", MySqlDbType.VarChar) { Value = fieldValue },
                    new MySqlParameter("username", MySqlDbType.VarChar) { Value =  username}
                };

                if (functions.ExecuteNonQuery(query, parameters))
                {
                    Debug.WriteLine("UpdateProjectAssigned: The projectassigned was updated successfully.");
                }
                else
                {
                    Debug.WriteLine("UpdateProjectAssigned: An error has occured.");
                }
            }
        }

        /// <summary>
        /// Delete ProjectAssigned using Username
        /// </summary>
        /// <param name="username"></param>
        public void Delete(string username)
        {
            Delete(new ProjectAssigned(username));
        }

        /// <summary>
        /// Delete ProjectAssigned using ProjectAssigned Object
        /// </summary>
        /// <param name="projectassigned"></param>
        public void Delete(ProjectAssigned projectassigned)
        {
            if (Exists(projectassigned.Username))
            {
                string query = $"DELETE FROM {Table} WHERE Username = @username;";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("username", MySqlDbType.VarChar) { Value = projectassigned.Username }
                };

                if (functions.ExecuteNonQuery(query, parameters))
                {
                    Debug.WriteLine("DeleteProjectAssigned: The projectassigned was deleted successfully.");
                }
                else
                {
                    Debug.WriteLine("DeleteProjectAssigned: An error has occured.");
                }
            }
            else
            {
                Debug.WriteLine("DeleteProjectAssigned: Cannot delete projectassigned");
            }
        }

        /// <summary>
        /// Get ProjectAssigned Object From Sql Database
        /// </summary>
        /// <param name="username">Primary Key</param>
        /// <returns>ProjectAssigned Object</returns>
        public ProjectAssigned Get(string username)
        {
            if (Exists(username))
            {
                string query =
                    $"SELECT Username, Email, Name, ProjectId " +
                    $"FROM {Table} WHERE(Username = @username)";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("username", MySqlDbType.VarChar) { Value = username }
                };

                if (functions.ExecuteReader(query, parameters, out DataTable dataTable))
                {
                    DataRow row = dataTable.Rows[0];
                    ProjectAssigned projectassigned = new ProjectAssigned
                    {
                        Username = row["username"].ToString(),
                        Email = row["email"].ToString(),
                        Name = row["name"].ToString(),
                        ProjectId = row["projectid"].ToString()
                    };
                    return projectassigned;
                }
                else
                {
                    Debug.WriteLine("GetProjectAssigned: An error has occured while trying to get projectassigned.");
                    return null;
                }
            }
            else
            {
                return null;
            }
        }

        /// <summary>
        /// Get All Entries of ProjectAssigned Table
        /// </summary>
        /// <returns>List of ProjectAssigned Objects</returns>
        public List<ProjectAssigned> GetAll()
        {
            string query =
                $"SELECT Username, Email, Name, ProjectId FROM {Table};";
            if (functions.ExecuteReader(query, null, out DataTable dataTable))
            {
                List<ProjectAssigned> projectassigned = new List<ProjectAssigned>();
                foreach (DataRow row in dataTable.Rows)
                {
                    projectassigned.Add(new ProjectAssigned
                    {
                        Username = row["username"].ToString(),
                        Email = row["email"].ToString(),
                        Name = row["name"].ToString(),
                        ProjectId = row["projectid"].ToString()
                    });
                }
                return projectassigned;
            }
            else
            {
                Debug.WriteLine("GetAllProjectAssigneds: An error has occured while trying to get all projectassigned.");
                return null;
            }
        }

        /// <summary>
        /// Checks if an ProjectAssigned Exists using Username 
        /// </summary>
        /// <param name="keyValue">Primary Key</param>
        /// <returns></returns>
        public bool Exists(string keyValue)
        {
            if (functions.CheckExists(Table, "username", keyValue))
            {
                Debug.WriteLine($"Exists: The projectassigned exists with username: {keyValue}.");
                return true;
            }
            else
            {
                Debug.WriteLine($"Exists: The projectassigned doesn't exists with username: {keyValue}.");
                return false;
            }
        }

    }
}
