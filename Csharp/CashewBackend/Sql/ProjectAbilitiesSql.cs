using MySql.Data.MySqlClient;
using System.Collections.Generic;
using System.Data;
using System.Diagnostics;

namespace KarbonWebForms.Sql
{
    public class ProjectAbilitiesSql
    {
        private readonly MySqlFunctions functions = new MySqlFunctions();
        private readonly string Table = "projectAbilities";

        /// <summary>
        /// Inserts ProjectAbilities into Sql Database
        /// </summary>
        /// <param name="projectAbilities">ProjectAbilities Object</param>
        public void Insert(ProjectAbilities projectAbilities)
        {
            if (!Exists(projectAbilities.Username))
            {
                string query =
                    $"INSERT INTO {Table} (Username, Email, ProjectId, AssignmentEditing, ProjectEditing, CreateProject)" +
                    $" VALUES(@username, @email, @projectId, @assignmentediting, @projectediting, @createproject);";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("username", MySqlDbType.VarChar) { Value = projectAbilities.Username },
                    new MySqlParameter("email", MySqlDbType.VarChar) { Value = projectAbilities.Email },
                    new MySqlParameter("projectId", MySqlDbType.VarChar) { Value = projectAbilities.ProjectId },
                    new MySqlParameter("assignmentediting", MySqlDbType.Int16) { Value = projectAbilities.AssignmentEditing },
                    new MySqlParameter("projectediting", MySqlDbType.Int16) { Value = projectAbilities.ProjectEditing },
                    new MySqlParameter("createproject", MySqlDbType.Int16) { Value = projectAbilities.CreateProject },
                };

                if (functions.ExecuteNonQuery(query, parameters))
                {
                    Debug.WriteLine("InsertProjectAbilities: The projectAbilities was added successfully.");
                }
                else
                {
                    Debug.WriteLine("InsertProjectAbilities: An error has occured.");
                }
            }
        }

        /// <summary>
        /// Updates a field of ProjectAbilities Sql Table
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
                    Debug.WriteLine("UpdateProjectAbilities: The projectAbilities was updated successfully.");
                }
                else
                {
                    Debug.WriteLine("UpdateProjectAbilities: An error has occured.");
                }
            }
        }

        /// <summary>
        /// Delete ProjectAbilities using Username
        /// </summary>
        /// <param name="username"></param>
        public void Delete(string username)
        {
            Delete(new ProjectAbilities(username));
        }

        /// <summary>
        /// Delete ProjectAbilities using ProjectAbilities Object
        /// </summary>
        /// <param name="projectAbilities"></param>
        public void Delete(ProjectAbilities projectAbilities)
        {
            if (Exists(projectAbilities.Username))
            {
                string query = $"DELETE FROM {Table} WHERE Username = @username;";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("username", MySqlDbType.VarChar) { Value = projectAbilities.Username }
                };

                if (functions.ExecuteNonQuery(query, parameters))
                {
                    Debug.WriteLine("DeleteProjectAbilities: The projectAbilities was deleted successfully.");
                }
                else
                {
                    Debug.WriteLine("DeleteProjectAbilities: An error has occured.");
                }
            }
            else
            {
                Debug.WriteLine("DeleteProjectAbilities: Cannot delete projectAbilities");
            }
        }

        /// <summary>
        /// Get ProjectAbilities Object From Sql Database
        /// </summary>
        /// <param name="username">Primary Key</param>
        /// <returns>ProjectAbilities Object</returns>
        public ProjectAbilities Get(string username)
        {
            if (Exists(username))
            {
                string query =
                    $"SELECT Username, Email, ProjectId, AssignmentEditing, ProjectEditing, CreateProject " +
                    $"FROM {Table} WHERE(Username = @username)";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("username", MySqlDbType.VarChar) { Value = username }
                };

                if (functions.ExecuteReader(query, parameters, out DataTable dataTable))
                {
                    DataRow row = dataTable.Rows[0];
                    ProjectAbilities projectAbilities = new ProjectAbilities
                    {
                        Username = row["username"].ToString(),
                        Email = row["email"].ToString(),
                        ProjectId = row["projectid"].ToString(),
                        AssignmentEditing = (int)row["assignmentediting"],
                        ProjectEditing = (int)row["projectediting"],
                        CreateProject = (int)row["createproject"],
                    };
                    return projectAbilities;
                }
                else
                {
                    Debug.WriteLine("GetProjectAbilities: An error has occured while trying to get projectAbilities.");
                    return null;
                }
            }
            else
            {
                return null;
            }
        }

        /// <summary>
        /// Get All Entries of ProjectAbilities Table
        /// </summary>
        /// <returns>List of ProjectAbilities Objects</returns>
        public List<ProjectAbilities> GetAll()
        {
            string query =
                $"SELECT Username, Email, ProjectId, AssignmentEditing, ProjectEditing, CreateProject FROM {Table};";
            if (functions.ExecuteReader(query, null, out DataTable dataTable))
            {
                List<ProjectAbilities> projectAbilities = new List<ProjectAbilities>();
                foreach (DataRow row in dataTable.Rows)
                {
                    projectAbilities.Add(new ProjectAbilities
                    {
                        Username = row["username"].ToString(),
                        Email = row["email"].ToString(),
                        ProjectId = row["projectid"].ToString(),
                        AssignmentEditing = (int)row["assignmentediting"],
                        ProjectEditing = (int)row["projectediting"],
                        CreateProject = (int)row["createproject"],
                    });
                }
                return projectAbilities;
            }
            else
            {
                Debug.WriteLine("GetAllProjectAbilitiess: An error has occured while trying to get all projectAbilities.");
                return null;
            }
        }

        /// <summary>
        /// Checks if an ProjectAbilities Exists using Username 
        /// </summary>
        /// <param name="keyValue">Primary Key</param>
        /// <returns></returns>
        public bool Exists(string keyValue)
        {
            if (functions.CheckExists(Table, "username", keyValue))
            {
                Debug.WriteLine($"Exists: The projectAbilities exists with username: {keyValue}.");
                return true;
            }
            else
            {
                Debug.WriteLine($"Exists: The projectAbilities doesn't exists with username: {keyValue}.");
                return false;
            }
        }

    }
}
