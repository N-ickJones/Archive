using MySql.Data.MySqlClient;
using System.Collections.Generic;
using System.Data;
using System.Diagnostics;

namespace KarbonWebForms.Sql
{
    public class BuiltOnSql
    {
        private readonly MySqlFunctions functions = new MySqlFunctions();

        public void Insert(BuiltOn builtOn)
        {
            if (!Exists(builtOn.ProjectId))
            {
                string query =
                    "INSERT INTO builtOn (ProjectId, TaskId)" +
                    " VALUES(@projectid, @taskid);";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("projectid", MySqlDbType.VarChar) { Value = builtOn.ProjectId },
                    new MySqlParameter("taskid", MySqlDbType.VarChar) { Value = builtOn.TaskId },
                };

                if (functions.ExecuteNonQuery(query, parameters))
                {
                    Debug.WriteLine("InsertBuiltOn: The builtOn was added successfully.");
                }
                else
                {
                    Debug.WriteLine("InsertBuiltOn: An error has occured.");
                }
            }
        }

        public void Update(string projectid, string field, string fieldValue)
        {
            if (Exists(projectid))
            {
                string query = $"UPDATE builtOn SET {field} = @value WHERE (ProjectId = @projectid);";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("value", MySqlDbType.VarChar) { Value = fieldValue },
                    new MySqlParameter("projectid", MySqlDbType.VarChar) { Value = projectid }
                };

                if (functions.ExecuteNonQuery(query, parameters))
                {
                    Debug.WriteLine("UpdateBuiltOn: The builtOn was updated successfully.");
                }
                else
                {
                    Debug.WriteLine("UpdateBuiltOn: An error has occured.");
                }
            }
        }

        public void Delete(string projectId)
        {
            Delete(new BuiltOn(projectId));
        }

        public void Delete(BuiltOn builtOn)
        {
            if (Exists(builtOn.ProjectId))
            {
                string query = "DELETE FROM builtOn WHERE ProjectId = @projectid;";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("projectid", MySqlDbType.VarChar) { Value = builtOn.ProjectId }
                };

                if (functions.ExecuteNonQuery(query, parameters))
                {
                    Debug.WriteLine("DeleteBuiltOn: The builtOn was deleted successfully.");
                }
                else
                {
                    Debug.WriteLine("DeleteBuiltOn: An error has occured.");
                }
            }
            else
            {
                Debug.WriteLine("DeleteBuiltOn: Cannot delete builtOn");
            }
        }

        public BuiltOn Get(string projectid)
        {
            if (Exists(projectid))
            {
                string query =
                    "SELECT ProjectId, TaskId " +
                    "FROM builtOn WHERE(ProjectId = @projectid)";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("projectid", MySqlDbType.VarChar) { Value = projectid }
                };

                if (functions.ExecuteReader(query, parameters, out DataTable dataTable))
                {
                    DataRow row = dataTable.Rows[0];
                    BuiltOn builtOn = new BuiltOn
                    {
                        ProjectId = row["projectid"].ToString(),
                        TaskId = row["taskid"].ToString(),
                    };
                    return builtOn;
                }
                else
                {
                    Debug.WriteLine("GetBuiltOn: An error has occured while trying to get builtOn.");
                    return null;
                }
            }
            else
            {
                return null;
            }
        }

        public List<BuiltOn> GetAll()
        {
            string query =
                "SELECT ProjectId, TaskId FROM builtOn;";
            if (functions.ExecuteReader(query, null, out DataTable dataTable))
            {
                List<BuiltOn> builtOns = new List<BuiltOn>();
                foreach (DataRow row in dataTable.Rows)
                {
                    builtOns.Add(new BuiltOn
                    {
                        ProjectId = row["projectid"].ToString(),
                        TaskId = row["taskid"].ToString(),
                    });
                }
                return builtOns;
            }
            else
            {
                Debug.WriteLine("GetAllBuiltOns: An error has occured while trying to get all builtOns.");
                return null;
            }
        }

        public bool Exists(string keyValue)
        {
            if (functions.CheckExists("builtOn", "projectid", keyValue))
            {
                Debug.WriteLine($"Exists: The builtOn exists with projectid: {keyValue}.");
                return true;
            }
            else
            {
                Debug.WriteLine($"Exists: The builtOn doesn't exists with projectid: {keyValue}.");
                return false;
            }
        }
        
    }
}
