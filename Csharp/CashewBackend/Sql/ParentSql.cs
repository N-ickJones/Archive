using MySql.Data.MySqlClient;
using System.Collections.Generic;
using System.Data;
using System.Diagnostics;

namespace KarbonWebForms.Sql
{
    public class ParentSql
    {
        private readonly MySqlFunctions functions = new MySqlFunctions();
        private readonly string Table = "parent";

        /// <summary>
        /// Inserts Parent into Sql Database
        /// </summary>
        /// <param name="parent">Parent Object</param>
        public void Insert(Parent parent)
        {
            if (!Exists(parent.ParentTaskId))
            {
                string query =
                    $"INSERT INTO {Table} (ParentTaskId, ChildTaskId)" +
                    $" VALUES(@parenttaskid, @childtaskid);";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("parenttaskid", MySqlDbType.VarChar) { Value = parent.ParentTaskId },
                    new MySqlParameter("childtaskid", MySqlDbType.VarChar) { Value = parent.ChildTaskId },
                    
                };

                if (functions.ExecuteNonQuery(query, parameters))
                {
                    Debug.WriteLine("InsertParent: The parent was added successfully.");
                }
                else
                {
                    Debug.WriteLine("InsertParent: An error has occured.");
                }
            }
        }

        /// <summary>
        /// Updates a field of Parent Sql Table
        /// </summary>
        /// <param name="username">PrimaryKey</param>
        /// <param name="field">Column</param>
        /// <param name="fieldValue">Column new Value</param>
        public void Update(string parentTaskId, string field, string fieldValue)
        {
            //THIS NEEDS TO BE REWRITTEN
            if (Exists(parentTaskId))
            {
                string query = $"UPDATE {Table} SET {field} = @value WHERE (ParentTaskId = @parentTaskId);";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("value", MySqlDbType.VarChar) { Value = fieldValue },
                    new MySqlParameter("parentTaskId", MySqlDbType.VarChar) { Value =  parentTaskId}
                };

                if (functions.ExecuteNonQuery(query, parameters))
                {
                    Debug.WriteLine("UpdateParent: The parent was updated successfully.");
                }
                else
                {
                    Debug.WriteLine("UpdateParent: An error has occured.");
                }
            }
        }

        /// <summary>
        /// Delete Parent using Username
        /// </summary>
        /// <param name="id"></param>
        public void Delete(string id)
        {
            Delete(new Parent(id));
        }

        /// <summary>
        /// Delete Parent using Parent Object
        /// </summary>
        /// <param name="parent"></param>
        public void Delete(Parent parent)
        {
            if (Exists(parent.ParentTaskId))
            {
                string query = $"DELETE FROM {Table} WHERE parenttaskid = @parenttaskid;";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("parenttaskid", MySqlDbType.VarChar) { Value = parent.ParentTaskId }
                };

                if (functions.ExecuteNonQuery(query, parameters))
                {
                    Debug.WriteLine("DeleteParent: The parent was deleted successfully.");
                }
                else
                {
                    Debug.WriteLine("DeleteParent: An error has occured.");
                }
            }
            else
            {
                Debug.WriteLine("DeleteParent: Cannot delete parent");
            }
        }

        /// <summary>
        /// Get Parent Object From Sql Database
        /// </summary>
        /// <param name="username">Primary Key</param>
        /// <returns>Parent Object</returns>
        public Parent Get(string parentTaskId)
        {
            if (Exists(parentTaskId))
            {
                string query =
                    $"SELECT ParentTaskId, ChildTaskId " +
                    $"FROM {Table} WHERE(ParentTaskId = @parenttaskid)";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("parenttaskid", MySqlDbType.VarChar) { Value = parentTaskId }
                };

                if (functions.ExecuteReader(query, parameters, out DataTable dataTable))
                {
                    DataRow row = dataTable.Rows[0];
                    Parent parent = new Parent
                    {
                        ParentTaskId = row["parenttaskid"].ToString(),
                        ChildTaskId = row["childtaskid"].ToString()
                    };
                    return parent;
                }
                else
                {
                    Debug.WriteLine("GetParent: An error has occured while trying to get parent.");
                    return null;
                }
            }
            else
            {
                return null;
            }
        }

        /// <summary>
        /// Get All Entries of Parent Table
        /// </summary>
        /// <returns>List of Parent Objects</returns>
        public List<Parent> GetAll()
        {
            string query =
                $"SELECT ParentTaskId, ChildTaskId FROM {Table};";
            if (functions.ExecuteReader(query, null, out DataTable dataTable))
            {
                List<Parent> parent = new List<Parent>();
                foreach (DataRow row in dataTable.Rows)
                {
                    parent.Add(new Parent
                    {
                        ParentTaskId = row["parenttaskid"].ToString(),
                        ChildTaskId = row["childtaskid"].ToString(),
                    });
                }
                return parent;
            }
            else
            {
                Debug.WriteLine("GetAllParents: An error has occured while trying to get all parent.");
                return null;
            }
        }

        /// <summary>
        /// Checks if an Parent Exists using Username 
        /// </summary>
        /// <param name="keyValue">Primary Key</param>
        /// <returns></returns>
        public bool Exists(string keyValue)
        {
            if (functions.CheckExists(Table, "parenttaskid", keyValue))
            {
                Debug.WriteLine($"Exists: The parent exists with username: {keyValue}.");
                return true;
            }
            else
            {
                Debug.WriteLine($"Exists: The parent doesn't exists with username: {keyValue}.");
                return false;
            }
        }

    }
}
