using MySql.Data.MySqlClient;
using System.Collections.Generic;
using System.Data;
using System.Diagnostics;

namespace KarbonWebForms.Sql
{
    public class TaskAbilitiesSql
    {
        private readonly MySqlFunctions functions = new MySqlFunctions();
        private readonly string Table = "taskAbilities";

        /// <summary>
        /// Inserts TaskAbilities into Sql Database
        /// </summary>
        /// <param name="taskAbilities">TaskAbilities Object</param>
        public void Insert(TaskAbilities taskAbilities)
        {
            if (!Exists(taskAbilities.Username))
            {
                string query =
                    $"INSERT INTO {Table} (Username, Email, TaskId, AssignmentEditing)" +
                    $" VALUES(@username, @email, @taskid, @assignmentid);";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("username", MySqlDbType.VarChar) { Value = taskAbilities.Username },
                    new MySqlParameter("email", MySqlDbType.VarChar) { Value = taskAbilities.Email },
                    new MySqlParameter("taskid", MySqlDbType.VarChar) { Value = taskAbilities.TaskId },
                    new MySqlParameter("assignmentid", MySqlDbType.VarChar) { Value = taskAbilities.AssignmentEditing },
                };

                if (functions.ExecuteNonQuery(query, parameters))
                {
                    Debug.WriteLine("InsertTaskAbilities: The taskAbilities was added successfully.");
                }
                else
                {
                    Debug.WriteLine("InsertTaskAbilities: An error has occured.");
                }
            }
        }

        /// <summary>
        /// Updates a field of TaskAbilities Sql Table
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
                    Debug.WriteLine("UpdateTaskAbilities: The taskAbilities was updated successfully.");
                }
                else
                {
                    Debug.WriteLine("UpdateTaskAbilities: An error has occured.");
                }
            }
        }

        /// <summary>
        /// Delete TaskAbilities using Username
        /// </summary>
        /// <param name="username"></param>
        public void Delete(string username)
        {
            Delete(new TaskAbilities(username));
        }

        /// <summary>
        /// Delete TaskAbilities using TaskAbilities Object
        /// </summary>
        /// <param name="taskAbilities"></param>
        public void Delete(TaskAbilities taskAbilities)
        {
            if (Exists(taskAbilities.Username))
            {
                string query = $"DELETE FROM {Table} WHERE Username = @username;";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("username", MySqlDbType.VarChar) { Value = taskAbilities.Username }
                };

                if (functions.ExecuteNonQuery(query, parameters))
                {
                    Debug.WriteLine("DeleteTaskAbilities: The taskAbilities was deleted successfully.");
                }
                else
                {
                    Debug.WriteLine("DeleteTaskAbilities: An error has occured.");
                }
            }
            else
            {
                Debug.WriteLine("DeleteTaskAbilities: Cannot delete taskAbilities");
            }
        }

        /// <summary>
        /// Get TaskAbilities Object From Sql Database
        /// </summary>
        /// <param name="username">Primary Key</param>
        /// <returns>TaskAbilities Object</returns>
        public TaskAbilities Get(string username)
        {
            if (Exists(username))
            {
                string query =
                    $"SELECT Username, Email, TaskId, AssignmentEditing " +
                    $"FROM {Table} WHERE(Username = @username)";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("username", MySqlDbType.VarChar) { Value = username }
                };

                if (functions.ExecuteReader(query, parameters, out DataTable dataTable))
                {
                    DataRow row = dataTable.Rows[0];
                    TaskAbilities taskAbilities = new TaskAbilities
                    {
                        Username = row["username"].ToString(),
                        Email = row["email"].ToString(),
                        TaskId = row["taskid"].ToString(),
                        AssignmentEditing = (int)row["assignmentediting"],
                    };
                    return taskAbilities;
                }
                else
                {
                    Debug.WriteLine("GetTaskAbilities: An error has occured while trying to get taskAbilities.");
                    return null;
                }
            }
            else
            {
                return null;
            }
        }

        /// <summary>
        /// Get All Entries of TaskAbilities Table
        /// </summary>
        /// <returns>List of TaskAbilities Objects</returns>
        public List<TaskAbilities> GetAll()
        {
            string query =
                $"SELECT Username, Email, TaskId, AssignmentEditing FROM {Table};";
            if (functions.ExecuteReader(query, null, out DataTable dataTable))
            {
                List<TaskAbilities> taskAbilities = new List<TaskAbilities>();
                foreach (DataRow row in dataTable.Rows)
                {
                    taskAbilities.Add(new TaskAbilities
                    {
                        Username = row["username"].ToString(),
                        Email = row["email"].ToString(),
                        TaskId = row["taskid"].ToString(),
                        AssignmentEditing = (int)row["assignmentediting"],
                    });
                }
                return taskAbilities;
            }
            else
            {
                Debug.WriteLine("GetAllTaskAbilitiess: An error has occured while trying to get all taskAbilities.");
                return null;
            }
        }

        /// <summary>
        /// Checks if an TaskAbilities Exists using Username 
        /// </summary>
        /// <param name="keyValue">Primary Key</param>
        /// <returns></returns>
        public bool Exists(string keyValue)
        {
            if (functions.CheckExists(Table, "username", keyValue))
            {
                Debug.WriteLine($"Exists: The taskAbilities exists with username: {keyValue}.");
                return true;
            }
            else
            {
                Debug.WriteLine($"Exists: The taskAbilities doesn't exists with username: {keyValue}.");
                return false;
            }
        }

    }
}
