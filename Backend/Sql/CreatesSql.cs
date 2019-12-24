using MySql.Data.MySqlClient;
using System.Collections.Generic;
using System.Data;
using System.Diagnostics;

namespace KarbonWebForms.Sql
{
    public class CreatesSql
    {
        private readonly MySqlFunctions functions = new MySqlFunctions();
        private readonly string Table = "creates";

        /// <summary>
        /// Inserts Creates into Sql Database
        /// </summary>
        /// <param name="creates">Creates Object</param>
        public void Insert(Creates creates)
        {
            if (!Exists(creates.Username))
            {
                string query =
                    $"INSERT INTO {Table} (Username, Email, Name, ProjectId)" +
                    $" VALUES(@username, @email, @name, @projectId);";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("username", MySqlDbType.VarChar) { Value = creates.Username },
                    new MySqlParameter("email", MySqlDbType.VarChar) { Value = creates.Email },
                    new MySqlParameter("name", MySqlDbType.VarChar) { Value = creates.Name },
                    new MySqlParameter("projectId", MySqlDbType.VarChar) { Value = creates.ProjectId },
                };

                if (functions.ExecuteNonQuery(query, parameters))
                {
                    Debug.WriteLine("InsertCreates: The creates was added successfully.");
                }
                else
                {
                    Debug.WriteLine("InsertCreates: An error has occured.");
                }
            }
        }

        /// <summary>
        /// Updates a field of Creates Sql Table
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
                    Debug.WriteLine("UpdateCreates: The creates was updated successfully.");
                }
                else
                {
                    Debug.WriteLine("UpdateCreates: An error has occured.");
                }
            }
        }

        /// <summary>
        /// Delete Creates using Username
        /// </summary>
        /// <param name="username"></param>
        public void Delete(string username)
        {
            Delete(new Creates(username));
        }

        /// <summary>
        /// Delete Creates using Creates Object
        /// </summary>
        /// <param name="creates"></param>
        public void Delete(Creates creates)
        {
            if (Exists(creates.Username))
            {
                string query = $"DELETE FROM {Table} WHERE Username = @username;";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("username", MySqlDbType.VarChar) { Value = creates.Username }
                };

                if (functions.ExecuteNonQuery(query, parameters))
                {
                    Debug.WriteLine("DeleteCreates: The creates was deleted successfully.");
                }
                else
                {
                    Debug.WriteLine("DeleteCreates: An error has occured.");
                }
            }
            else
            {
                Debug.WriteLine("DeleteCreates: Cannot delete creates");
            }
        }

        /// <summary>
        /// Get Creates Object From Sql Database
        /// </summary>
        /// <param name="username">Primary Key</param>
        /// <returns>Creates Object</returns>
        public Creates Get(string username)
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
                    Creates creates = new Creates
                    {
                        Username = row["username"].ToString(),
                        Email = row["email"].ToString(),
                        Name = row["name"].ToString(),
                        ProjectId = row["projectId"].ToString(),
                    };
                    return creates;
                }
                else
                {
                    Debug.WriteLine("GetCreates: An error has occured while trying to get creates.");
                    return null;
                }
            }
            else
            {
                return null;
            }
        }

        /// <summary>
        /// Get All Entries of Creates Table
        /// </summary>
        /// <returns>List of Creates Objects</returns>
        public List<Creates> GetAll()
        {
            string query =
                $"SELECT Username, Email, Name, ProjectId FROM {Table};";
            if (functions.ExecuteReader(query, null, out DataTable dataTable))
            {
                List<Creates> creates = new List<Creates>();
                foreach (DataRow row in dataTable.Rows)
                {
                    creates.Add(new Creates
                    {
                        Username = row["username"].ToString(),
                        Email = row["email"].ToString(),
                        Name = row["name"].ToString(),
                        ProjectId = row["projectid"].ToString(),
                    });
                }
                return creates;
            }
            else
            {
                Debug.WriteLine("GetAllCreatess: An error has occured while trying to get all creates.");
                return null;
            }
        }

        /// <summary>
        /// Checks if an Creates Exists using Username 
        /// </summary>
        /// <param name="keyValue">Primary Key</param>
        /// <returns></returns>
        public bool Exists(string keyValue)
        {
            if (functions.CheckExists(Table, "username", keyValue))
            {
                Debug.WriteLine($"Exists: The creates exists with username: {keyValue}.");
                return true;
            }
            else
            {
                Debug.WriteLine($"Exists: The creates doesn't exists with username: {keyValue}.");
                return false;
            }
        }
        
    }
}
