using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Data;
using System.Diagnostics;

namespace KarbonWebForms.Sql
{
    public class TaskSql
    {
        private readonly MySqlFunctions functions = new MySqlFunctions();
        private readonly string Table = "task";

        /// <summary>
        /// Inserts Task into Sql Database
        /// </summary>
        /// <param name="task">Task Object</param>
        public void Insert(Task task)
        {
            if (!Exists(task.TaskId))
            {
                string query =
                    $"INSERT INTO {Table} (TaskId, TaskDeadline, TaskDescription)" +
                    $" VALUES(@taskid, @taskdeadline, @taskdescription);";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("taskid", MySqlDbType.VarChar) { Value = task.TaskId },
                    new MySqlParameter("taskdeadline", MySqlDbType.DateTime) { Value = task.TaskDeadline },
                    new MySqlParameter("taskdescription", MySqlDbType.VarChar) { Value = task.TaskDescription },
                };

                if (functions.ExecuteNonQuery(query, parameters))
                {
                    Debug.WriteLine("InsertTask: The task was added successfully.");
                }
                else
                {
                    Debug.WriteLine("InsertTask: An error has occured.");
                }
            }
        }

        /// <summary>
        /// Updates a field of Task Sql Table
        /// </summary>
        /// <param name="username">PrimaryKey</param>
        /// <param name="field">Column</param>
        /// <param name="fieldValue">Column new Value</param>
        public void Update(string taskid, string field, DateTime fieldValue)
        {
            if (Exists(taskid))
            {
                string query = $"UPDATE {Table} SET {field} = @value WHERE (TaskId = @taskid);";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("value", MySqlDbType.DateTime) { Value = fieldValue },
                    new MySqlParameter("taskid", MySqlDbType.VarChar) { Value =  taskid}
                };

                if (functions.ExecuteNonQuery(query, parameters))
                {
                    Debug.WriteLine("UpdateTask: The task was updated successfully.");
                }
                else
                {
                    Debug.WriteLine("UpdateTask: An error has occured.");
                }
            }
        }

        /// <summary>
        /// Delete Task using Username
        /// </summary>
        /// <param name="username"></param>
        public void Delete(string taskid)
        {
            Delete(new Task(taskid));
        }

        /// <summary>
        /// Delete Task using Task Object
        /// </summary>
        /// <param name="task"></param>
        public void Delete(Task task)
        {
            if (Exists(task.TaskId))
            {
                string query = $"DELETE FROM {Table} WHERE TaskId = @taskid;";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("taskid", MySqlDbType.VarChar) { Value = task.TaskId }
                };

                if (functions.ExecuteNonQuery(query, parameters))
                {
                    Debug.WriteLine("DeleteTask: The task was deleted successfully.");
                }
                else
                {
                    Debug.WriteLine("DeleteTask: An error has occured.");
                }
            }
            else
            {
                Debug.WriteLine("DeleteTask: Cannot delete task");
            }
        }

        /// <summary>
        /// Get Task Object From Sql Database
        /// </summary>
        /// <param name="username">Primary Key</param>
        /// <returns>Task Object</returns>
        public Task Get(string taskId)
        {
            if (Exists(taskId))
            {
                string query =
                    $"SELECT TaskId, TaskDeadline, TaskDescription " +
                    $"FROM {Table} WHERE(TaskId = @taskid)";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("taskid", MySqlDbType.VarChar) { Value = taskId }
                };

                if (functions.ExecuteReader(query, parameters, out DataTable dataTable))
                {
                    DataRow row = dataTable.Rows[0];
                    Task task = new Task
                    {
                        TaskId = row["taskid"].ToString(),
                        TaskDeadline = Convert.ToDateTime(row["taskdeadline"]),
                        TaskDescription = row["taskdescription"].ToString(),
                    };
                    return task;
                }
                else
                {
                    Debug.WriteLine("GetTask: An error has occured while trying to get task.");
                    return null;
                }
            }
            else
            {
                return null;
            }
        }

        /// <summary>
        /// Get All Entries of Task Table
        /// </summary>
        /// <returns>List of Task Objects</returns>
        public List<Task> GetAll()
        {
            string query =
                $"SELECT TaskId, TaskDeadline, TaskDescription FROM {Table};";
            if (functions.ExecuteReader(query, null, out DataTable dataTable))
            {
                List<Task> task = new List<Task>();
                foreach (DataRow row in dataTable.Rows)
                {
                    task.Add(new Task
                    {
                        TaskId = row["taskid"].ToString(),
                        TaskDeadline = Convert.ToDateTime(row["taskdeadline"]),
                        TaskDescription = row["taskdescription"].ToString(),
                    });
                }
                return task;
            }
            else
            {
                Debug.WriteLine("GetAllTasks: An error has occured while trying to get all task.");
                return null;
            }
        }

        /// <summary>
        /// Checks if an Task Exists using Username 
        /// </summary>
        /// <param name="keyValue">Primary Key</param>
        /// <returns></returns>
        public bool Exists(string keyValue)
        {
            if (functions.CheckExists(Table, "taskid", keyValue))
            {
                Debug.WriteLine($"Exists: The task exists with taskid: {keyValue}.");
                return true;
            }
            else
            {
                Debug.WriteLine($"Exists: The task doesn't exists with taskid: {keyValue}.");
                return false;
            }
        }

    }
}
