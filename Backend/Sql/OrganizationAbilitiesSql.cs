using MySql.Data.MySqlClient;
using System.Collections.Generic;
using System.Data;
using System.Diagnostics;

namespace KarbonWebForms.Sql
{
    public class OrganizationAbilitiesSql
    {
        private readonly MySqlFunctions functions = new MySqlFunctions();
        private readonly string Table = "organizationAbilities";

        /// <summary>
        /// Inserts OrganizationAbilities into Sql Database
        /// </summary>
        /// <param name="organizationAbilities">OrganizationAbilities Object</param>
        public void Insert(OrganizationAbilities organizationAbilities)
        {
            if (!Exists(organizationAbilities.Username))
            {
                string query =
                    $"INSERT INTO {Table} (Username, Email, Name, RemoveUser, InviteUser, PermissionsEditing)" +
                    $" VALUES(@username, @email, @name, @removeuser, @inviteuser, @permissionsediting);";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("username", MySqlDbType.VarChar) { Value = organizationAbilities.Username },
                    new MySqlParameter("email", MySqlDbType.VarChar) { Value = organizationAbilities.Email },
                    new MySqlParameter("name", MySqlDbType.VarChar) { Value = organizationAbilities.Name },
                    new MySqlParameter("removeuser", MySqlDbType.Int16) { Value = organizationAbilities.RemoveUser },
                    new MySqlParameter("inviteuser", MySqlDbType.Int16) { Value = organizationAbilities.InviteUser },
                    new MySqlParameter("permissionsediting", MySqlDbType.Int16) { Value = organizationAbilities.PermissionsEditing },
                };

                if (functions.ExecuteNonQuery(query, parameters))
                {
                    Debug.WriteLine("InsertOrganizationAbilities: The organizationAbilities was added successfully.");
                }
                else
                {
                    Debug.WriteLine("InsertOrganizationAbilities: An error has occured.");
                }
            }
        }

        /// <summary>
        /// Updates a field of OrganizationAbilities Sql Table
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
                    Debug.WriteLine("UpdateOrganizationAbilities: The organizationAbilities was updated successfully.");
                }
                else
                {
                    Debug.WriteLine("UpdateOrganizationAbilities: An error has occured.");
                }
            }
        }

        /// <summary>
        /// Delete OrganizationAbilities using Username
        /// </summary>
        /// <param name="username"></param>
        public void Delete(string username)
        {
            Delete(new OrganizationAbilities(username));
        }

        /// <summary>
        /// Delete OrganizationAbilities using OrganizationAbilities Object
        /// </summary>
        /// <param name="organizationAbilities"></param>
        public void Delete(OrganizationAbilities organizationAbilities)
        {
            if (Exists(organizationAbilities.Username))
            {
                string query = $"DELETE FROM {Table} WHERE Username = @username;";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("username", MySqlDbType.VarChar) { Value = organizationAbilities.Username }
                };

                if (functions.ExecuteNonQuery(query, parameters))
                {
                    Debug.WriteLine("DeleteOrganizationAbilities: The organizationAbilities was deleted successfully.");
                }
                else
                {
                    Debug.WriteLine("DeleteOrganizationAbilities: An error has occured.");
                }
            }
            else
            {
                Debug.WriteLine("DeleteOrganizationAbilities: Cannot delete organizationAbilities");
            }
        }

        /// <summary>
        /// Get OrganizationAbilities Object From Sql Database
        /// </summary>
        /// <param name="username">Primary Key</param>
        /// <returns>OrganizationAbilities Object</returns>
        public OrganizationAbilities Get(string username)
        {
            if (Exists(username))
            {
                string query =
                    $"SELECT Username, Email, Name, RemoveUser, InviteUser, PermissionsEditing " +
                    $"FROM {Table} WHERE(Username = @username)";

                List<MySqlParameter> parameters = new List<MySqlParameter>
                {
                    new MySqlParameter("username", MySqlDbType.VarChar) { Value = username }
                };

                if (functions.ExecuteReader(query, parameters, out DataTable dataTable))
                {
                    DataRow row = dataTable.Rows[0];
                    OrganizationAbilities organizationAbilities = new OrganizationAbilities
                    {
                        Username = row["username"].ToString(),
                        Email = row["email"].ToString(),
                        Name = row["name"].ToString(),
                        RemoveUser = (int)row["removeuser"],
                        InviteUser = (int)row["inviteuser"],
                        PermissionsEditing = (int)row["permissionsediting"],
                    };
                    return organizationAbilities;
                }
                else
                {
                    Debug.WriteLine("GetOrganizationAbilities: An error has occured while trying to get organizationAbilities.");
                    return null;
                }
            }
            else
            {
                return null;
            }
        }

        /// <summary>
        /// Get All Entries of OrganizationAbilities Table
        /// </summary>
        /// <returns>List of OrganizationAbilities Objects</returns>
        public List<OrganizationAbilities> GetAll()
        {
            string query =
                $"SELECT Username, Email, Name, RemoveUser, InviteUser, PermissionsEditing FROM {Table};";
            if (functions.ExecuteReader(query, null, out DataTable dataTable))
            {
                List<OrganizationAbilities> organizationAbilities = new List<OrganizationAbilities>();
                foreach (DataRow row in dataTable.Rows)
                {
                    organizationAbilities.Add(new OrganizationAbilities
                    {
                        Username = row["username"].ToString(),
                        Email = row["email"].ToString(),
                        Name = row["name"].ToString(),
                        RemoveUser = (int)row["removeuser"],
                        InviteUser = (int)row["inviteuser"],
                        PermissionsEditing = (int)row["permissionsediting"],
                    });
                }
                return organizationAbilities;
            }
            else
            {
                Debug.WriteLine("GetAllOrganizationAbilitiess: An error has occured while trying to get all organizationAbilities.");
                return null;
            }
        }

        /// <summary>
        /// Checks if an OrganizationAbilities Exists using Username 
        /// </summary>
        /// <param name="keyValue">Primary Key</param>
        /// <returns></returns>
        public bool Exists(string keyValue)
        {
            if (functions.CheckExists(Table, "username", keyValue))
            {
                Debug.WriteLine($"Exists: The organizationAbilities exists with username: {keyValue}.");
                return true;
            }
            else
            {
                Debug.WriteLine($"Exists: The organizationAbilities doesn't exists with username: {keyValue}.");
                return false;
            }
        }

    }
}
