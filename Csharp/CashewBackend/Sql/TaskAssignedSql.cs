using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Data;
using System.Diagnostics;

namespace KarbonWebForms.Sql
{
    public class TaskAssignedSql
    {
        private readonly MySqlFunctions functions = new MySqlFunctions();
        private readonly string Table = "taskassigned";

        /// <summary>
        /// Inserts TaskAssigned into Sql Database
        /// </summary>
        /// <param name="taskassigned">TaskAssigned Object</param>
        public void Insert(TaskAssigned taskassigned)
        {
            if (!Exists(taskassigned.Username))
            {
                string query =
                    $"INSERT INTO {Table} (Username, Email, Name, TaskId)" +
                    $" VALUES(@username, @email, @name, @taskid);";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("username", MySqlDbType.VarChar) { Value = taskassigned.Username },
                    new MySqlParameter("email", MySqlDbType.VarChar) { Value = taskassigned.Email },
                    new MySqlParameter("name", MySqlDbType.VarChar) { Value = taskassigned.Name },
                    new MySqlParameter("taskid", MySqlDbType.VarChar) { Value = taskassigned.TaskId },

                };

                if (functions.ExecuteNonQuery(query, parameters))
                {
                    Debug.WriteLine("InsertTaskAssigned: The taskassigned was added successfully.");
                }
                else
                {
                    Debug.WriteLine("InsertTaskAssigned: An error has occured.");
                }
            }
        }

        /// <summary>
        /// Updates a field of TaskAssigned Sql Table
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
                    Debug.WriteLine("UpdateTaskAssigned: The taskassigned was updated successfully.");
                }
                else
                {
                    Debug.WriteLine("UpdateTaskAssigned: An error has occured.");
                }
            }
        }

        /// <summary>
        /// Delete TaskAssigned using Username
        /// </summary>
        /// <param name="username"></param>
        public void Delete(string username)
        {
            Delete(new TaskAssigned(username));
        }

        /// <summary>
        /// Delete TaskAssigned using TaskAssigned Object
        /// </summary>
        /// <param name="taskassigned"></param>
        public void Delete(TaskAssigned taskassigned)
        {
            if (Exists(taskassigned.Username))
            {
                string query = $"DELETE FROM {Table} WHERE Username = @username;";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("username", MySqlDbType.VarChar) { Value = taskassigned.Username }
                };

                if (functions.ExecuteNonQuery(query, parameters))
                {
                    Debug.WriteLine("DeleteTaskAssigned: The taskassigned was deleted successfully.");
                }
                else
                {
                    Debug.WriteLine("DeleteTaskAssigned: An error has occured.");
                }
            }
            else
            {
                Debug.WriteLine("DeleteTaskAssigned: Cannot delete taskassigned");
            }
        }

        /// <summary>
        /// Get TaskAssigned Object From Sql Database
        /// </summary>
        /// <param name="username">Primary Key</param>
        /// <returns>TaskAssigned Object</returns>
        public TaskAssigned Get(string username)
        {
            if (Exists(username))
            {
                string query =
                    $"SELECT Username, Email, Name, TaskId " +
                    $"FROM {Table} WHERE(Username = @username)";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("username", MySqlDbType.VarChar) { Value = username }
                };

                if (functions.ExecuteReader(query, parameters, out DataTable dataTable))
                {
                    DataRow row = dataTable.Rows[0];
                    TaskAssigned taskassigned = new TaskAssigned
                    {
                        Username = row["username"].ToString(),
                        Email = row["email"].ToString(),
                        Name = row["name"].ToString(),
                        TaskId = row["taskid"].ToString()
                    };
                    return taskassigned;
                }
                else
                {
                    Debug.WriteLine("GetTaskAssigned: An error has occured while trying to get taskassigned.");
                    return null;
                }
            }
            else
            {
                return null;
            }
        }

        /// <summary>
        /// Get All Entries of TaskAssigned Table
        /// </summary>
        /// <returns>List of TaskAssigned Objects</returns>
        public List<TaskAssigned> GetAll()
        {
            string query =
                $"SELECT Username, Email, Name, TaskId FROM {Table};";
            if (functions.ExecuteReader(query, null, out DataTable dataTable))
            {
                List<TaskAssigned> taskassigned = new List<TaskAssigned>();
                foreach (DataRow row in dataTable.Rows)
                {
                    taskassigned.Add(new TaskAssigned
                    {
                        Username = row["username"].ToString(),
                        Email = row["email"].ToString(),
                        Name = row["name"].ToString(),
                        TaskId = row["taskid"].ToString()
                    });
                }
                return taskassigned;
            }
            else
            {
                Debug.WriteLine("GetAllTaskAssigneds: An error has occured while trying to get all taskassigned.");
                return null;
            }
        }

        /// <summary>
        /// Checks if an TaskAssigned Exists using Username 
        /// </summary>
        /// <param name="keyValue">Primary Key</param>
        /// <returns></returns>
        public bool Exists(string keyValue)
        {
            if (functions.CheckExists(Table, "username", keyValue))
            {
                Debug.WriteLine($"Exists: The taskassigned exists with username: {keyValue}.");
                return true;
            }
            else
            {
                Debug.WriteLine($"Exists: The taskassigned doesn't exists with username: {keyValue}.");
                return false;
            }
        }

    }
}
