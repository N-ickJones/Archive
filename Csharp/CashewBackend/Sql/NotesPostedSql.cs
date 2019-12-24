using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Data;
using System.Diagnostics;

namespace KarbonWebForms.Sql
{
    public class NotesPostedSql
    {
        private readonly MySqlFunctions functions = new MySqlFunctions();
        private readonly string Table = "notesposted";

        /// <summary>
        /// Inserts NotesPosted into Sql Database
        /// </summary>
        /// <param name="notesPosted">NotesPosted Object</param>
        public void Insert(NotesPosted notesPosted)
        {
            if (!Exists(notesPosted.Username))
            {
                string query =
                    $"INSERT INTO {Table} (Text, `TimeStamp`, Username, Email, TaskId)" +
                    $" VALUES(@text, @tstamp, @username, @email, @taskid);";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("text", MySqlDbType.VarChar) { Value = notesPosted.Text },
                    new MySqlParameter("tstamp", MySqlDbType.DateTime) { Value = notesPosted.TimeStamp },
                    new MySqlParameter("username", MySqlDbType.VarChar) { Value = notesPosted.Username },
                    new MySqlParameter("email", MySqlDbType.VarChar) { Value = notesPosted.Email },
                    new MySqlParameter("taskid", MySqlDbType.VarChar) { Value = notesPosted.TaskId },
                };

                if (functions.ExecuteNonQuery(query, parameters))
                {
                    Debug.WriteLine("InsertNotesPosted: The notesPosted was added successfully.");
                }
                else
                {
                    Debug.WriteLine("InsertNotesPosted: An error has occured.");
                }
            }
        }

        /// <summary>
        /// Updates a field of NotesPosted Sql Table
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
                    Debug.WriteLine("UpdateNotesPosted: The notesPosted was updated successfully.");
                }
                else
                {
                    Debug.WriteLine("UpdateNotesPosted: An error has occured.");
                }
            }
        }

        /// <summary>
        /// Delete NotesPosted using Username
        /// </summary>
        /// <param name="username"></param>
        public void Delete(string username)
        {
            Delete(new NotesPosted(username));
        }

        /// <summary>
        /// Delete NotesPosted using NotesPosted Object
        /// </summary>
        /// <param name="notesPosted"></param>
        public void Delete(NotesPosted notesPosted)
        {
            if (Exists(notesPosted.Username))
            {
                string query = $"DELETE FROM {Table} WHERE Username = @username;";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("username", MySqlDbType.VarChar) { Value = notesPosted.Username }
                };

                if (functions.ExecuteNonQuery(query, parameters))
                {
                    Debug.WriteLine("DeleteNotesPosted: The notesPosted was deleted successfully.");
                }
                else
                {
                    Debug.WriteLine("DeleteNotesPosted: An error has occured.");
                }
            }
            else
            {
                Debug.WriteLine("DeleteNotesPosted: Cannot delete notesPosted");
            }
        }

        /// <summary>
        /// Get NotesPosted Object From Sql Database
        /// </summary>
        /// <param name="username">Primary Key</param>
        /// <returns>NotesPosted Object</returns>
        public NotesPosted Get(string username)
        {
            if (Exists(username))
            {
                string query =
                    $"SELECT Text, `TimeStamp`, Username, Email, TaskId " +
                    $"FROM {Table} WHERE(Username = @username)";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("username", MySqlDbType.VarChar) { Value = username }
                };

                if (functions.ExecuteReader(query, parameters, out DataTable dataTable))
                {
                    DataRow row = dataTable.Rows[0];
                    NotesPosted notesPosted = new NotesPosted
                    {
                        Text = row["text"].ToString(),
                        TimeStamp = Convert.ToDateTime(row["timestamp"]),
                        Username = row["username"].ToString(),
                        Email = row["email"].ToString(),
                        TaskId = row["taskid"].ToString()

                    };
                    return notesPosted;
                }
                else
                {
                    Debug.WriteLine("GetNotesPosted: An error has occured while trying to get notesPosted.");
                    return null;
                }
            }
            else
            {
                return null;
            }
        }

        /// <summary>
        /// Get All Entries of NotesPosted Table
        /// </summary>
        /// <returns>List of NotesPosted Objects</returns>
        public List<NotesPosted> GetAll()
        {
            string query =
                $"SELECT Text, `TimeStamp`, Username, Email, TaskId FROM {Table};";
            if (functions.ExecuteReader(query, null, out DataTable dataTable))
            {
                List<NotesPosted> notesPosted = new List<NotesPosted>();
                foreach (DataRow row in dataTable.Rows)
                {
                    notesPosted.Add(new NotesPosted
                    {
                        Text = row["text"].ToString(),
                        TimeStamp = Convert.ToDateTime(row["timestamp"]),
                        Username = row["username"].ToString(),
                        Email = row["email"].ToString(),
                        TaskId = row["taskid"].ToString(),
                    });
                }
                return notesPosted;
            }
            else
            {
                Debug.WriteLine("GetAllNotesPosteds: An error has occured while trying to get all notesPosted.");
                return null;
            }
        }

        /// <summary>
        /// Checks if an NotesPosted Exists using Username 
        /// </summary>
        /// <param name="keyValue">Primary Key</param>
        /// <returns></returns>
        public bool Exists(string keyValue)
        {
            if (functions.CheckExists(Table, "username", keyValue))
            {
                Debug.WriteLine($"Exists: The notesPosted exists with username: {keyValue}.");
                return true;
            }
            else
            {
                Debug.WriteLine($"Exists: The notesPosted doesn't exists with username: {keyValue}.");
                return false;
            }
        }

    }
}
