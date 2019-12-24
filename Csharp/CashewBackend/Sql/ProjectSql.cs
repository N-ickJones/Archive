using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Data;
using System.Diagnostics;

namespace KarbonWebForms.Sql
{
    public class ProjectSql
    {
        private readonly MySqlFunctions functions = new MySqlFunctions();
        private readonly string Table = "project";

        /// <summary>
        /// Inserts Project into Sql Database
        /// </summary>
        /// <param name="project">Project Object</param>
        public void Insert(Project project)
        {
            if (!Exists(project.ProjectId))
            {
                string query =
                    $"INSERT INTO {Table} (ProjectId, ProjectDeadline, ProjectDescription)" +
                    $" VALUES(@projectid, @projectdeadline, @projectdescription);";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("projectid", MySqlDbType.VarChar) { Value = project.ProjectId },
                    new MySqlParameter("projectdeadline", MySqlDbType.DateTime) { Value = project.ProjectDeadline },
                    new MySqlParameter("projectdescription", MySqlDbType.VarChar) { Value = project.ProjectDescription },
                };

                if (functions.ExecuteNonQuery(query, parameters))
                {
                    Debug.WriteLine("InsertProject: The project was added successfully.");
                }
                else
                {
                    Debug.WriteLine("InsertProject: An error has occured.");
                }
            }
        }

        /// <summary>
        /// Updates a field of Project Sql Table
        /// </summary>
        /// <param name="username">PrimaryKey</param>
        /// <param name="field">Column</param>
        /// <param name="fieldValue">Column new Value</param>
        public void Update(string projectId, string field, string fieldValue)
        {
            if (Exists(projectId))
            {
                string query = $"UPDATE {Table} SET {field} = @value WHERE (ProjectId = @projectid);";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("value", MySqlDbType.VarChar) { Value = fieldValue },
                    new MySqlParameter("projectid", MySqlDbType.VarChar) { Value =  projectId}
                };

                if (functions.ExecuteNonQuery(query, parameters))
                {
                    Debug.WriteLine("UpdateProject: The project was updated successfully.");
                }
                else
                {
                    Debug.WriteLine("UpdateProject: An error has occured.");
                }
            }
        }

        /// <summary>
        /// Delete Project using Username
        /// </summary>
        /// <param name="username"></param>
        public void Delete(string projectId)
        {
            Delete(new Project(projectId));
        }

        /// <summary>
        /// Delete Project using Project Object
        /// </summary>
        /// <param name="project"></param>
        public void Delete(Project project)
        {
            if (Exists(project.ProjectId))
            {
                string query = $"DELETE FROM {Table} WHERE ProjectId = @projectid;";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("projectid", MySqlDbType.VarChar) { Value = project.ProjectId }
                };

                if (functions.ExecuteNonQuery(query, parameters))
                {
                    Debug.WriteLine("DeleteProject: The project was deleted successfully.");
                }
                else
                {
                    Debug.WriteLine("DeleteProject: An error has occured.");
                }
            }
            else
            {
                Debug.WriteLine("DeleteProject: Cannot delete project");
            }
        }

        /// <summary>
        /// Get Project Object From Sql Database
        /// </summary>
        /// <param name="username">Primary Key</param>
        /// <returns>Project Object</returns>
        public Project Get(string projectid)
        {
            if (Exists(projectid))
            {
                string query =
                    $"SELECT ProjectId, ProjectDeadline, ProjectDescription " +
                    $"FROM {Table} WHERE(ProjectId = @projectid)";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("projectid", MySqlDbType.VarChar) { Value = projectid }
                };

                if (functions.ExecuteReader(query, parameters, out DataTable dataTable))
                {
                    DataRow row = dataTable.Rows[0];
                    Project project = new Project
                    {
                        ProjectId = row["projectid"].ToString(),
                        ProjectDeadline = Convert.ToDateTime(row["projectdeadline"]),
                        ProjectDescription = row["projectdescription"].ToString()
                    };
                    return project;
                }
                else
                {
                    Debug.WriteLine("GetProject: An error has occured while trying to get project.");
                    return null;
                }
            }
            else
            {
                return null;
            }
        }

        /// <summary>
        /// Get All Entries of Project Table
        /// </summary>
        /// <returns>List of Project Objects</returns>
        public List<Project> GetAll()
        {
            string query =
                $"SELECT ProjectId, ProjectDeadline, ProjectDescription FROM {Table};";
            if (functions.ExecuteReader(query, null, out DataTable dataTable))
            {
                List<Project> project = new List<Project>();
                foreach (DataRow row in dataTable.Rows)
                {
                    project.Add(new Project
                    {
                        ProjectId = row["projectid"].ToString(),
                        ProjectDeadline = Convert.ToDateTime(row["projectdeadline"]),
                        ProjectDescription = row["projectdescription"].ToString()
                    });
                }
                return project;
            }
            else
            {
                Debug.WriteLine("GetAllProjects: An error has occured while trying to get all project.");
                return null;
            }
        }

        /// <summary>
        /// Checks if an Project Exists using Username 
        /// </summary>
        /// <param name="keyValue">Primary Key</param>
        /// <returns></returns>
        public bool Exists(string keyValue)
        {
            if (functions.CheckExists(Table, "projectid", keyValue))
            {
                Debug.WriteLine($"Exists: The project exists with projectid: {keyValue}.");
                return true;
            }
            else
            {
                Debug.WriteLine($"Exists: The project doesn't exists with projectid: {keyValue}.");
                return false;
            }
        }

    }
}
