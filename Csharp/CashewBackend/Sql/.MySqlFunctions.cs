using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Diagnostics;
using System.Linq;
using System.Web;

namespace KarbonWebForms.Sql
{
    public class MySqlFunctions
    {
        private readonly string _connectionString =
            "server=shsuse.mysql.database.azure.com;" +
            "database=shsuse;" +
            "port=3306;" +
            "user=cashewclub@shsuse;" +
            "password=D1ipDan*gDelux5e;";

        public bool ExecuteNonQuery(string query, List<MySqlParameter> sqlParameters)
        {
            try
            {
                int result = -1;
                using (MySqlConnection sqlConnection = new MySqlConnection(_connectionString))
                {
                    using (MySqlCommand sqlCommand = CreateCommand(query, sqlConnection, sqlParameters))
                    {
                        if (sqlCommand != null)
                        {
                            sqlConnection.Open();
                            result = sqlCommand.ExecuteNonQuery();
                            if (result > 0)
                            {
                                return true;
                            }
                            else
                            {
                                Debug.WriteLine("ExecuteNonQuery: execution failure");
                                return false;
                            }
                        }
                        else
                        {
                            Debug.WriteLine("ExecuteNonQuery: sqlCommand = null");
                            return false;
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                Debug.WriteLine(ex.Message);
                return false;
            }
        }

        public bool ExecuteReader(string query, List<MySqlParameter> sqlParameters, out DataTable dataTable)
        {
            try
            {
                using (MySqlConnection sqlConnection = new MySqlConnection(_connectionString))
                {
                    using (MySqlCommand sqlCommand = CreateCommand(query, sqlConnection, sqlParameters))
                    {
                        if (sqlCommand != null)
                        {
                            sqlConnection.Open();
                            dataTable = new DataTable();
                            using (MySqlDataAdapter adapter = new MySqlDataAdapter(sqlCommand))
                            {
                                adapter.Fill(dataTable);
                                if (dataTable != null)
                                {
                                    if (dataTable.Rows.Count > 0)
                                    {
                                        return true;
                                    }
                                    else
                                    {
                                        dataTable = null;
                                        return false;
                                    }
                                }
                                else
                                {
                                    dataTable = null;
                                    return false;
                                }
                            }
                        }
                        else
                        {
                            dataTable = null;
                            return false;
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                Debug.WriteLine(ex.Message);
                dataTable = null;
                return false;
            }
        }

        public bool CheckExists(string table, string key, string keyValue)
        {
            string query = $"SELECT * FROM {table} WHERE {key} = @key;";
            List<MySqlParameter> sqlParameters = new List<MySqlParameter>
            {
                new MySqlParameter("key", MySqlDbType.VarChar) { Value = keyValue }
            };
            try
            {
                using (MySqlConnection sqlConnection = new MySqlConnection(_connectionString))
                {
                    using (MySqlCommand sqlCommand = CreateCommand(query, sqlConnection, sqlParameters))
                    {
                        if (sqlCommand != null)
                        {
                            sqlConnection.Open();
                            object result = sqlCommand.ExecuteScalar();
                            if (result != null)
                            {
                                return true;
                            }
                            else
                            {
                                return false;
                            }
                        }
                        else
                        {
                            return false;
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                Debug.WriteLine(ex.Message);
                return false;
            }
        }

        public bool CheckExists(string table, List<CheckValuePair> checkList)
        {
            string query = $"SELECT * FROM {table} WHERE";

            List<MySqlParameter> sqlParameters = new List<MySqlParameter>();

            for (int i = 0; i < checkList.Count; i++)
            {
                if (i == checkList.Count - 1)
                {
                    query += $" {checkList[i].Key} = @key{i};";
                }
                else
                {
                    query += $" {checkList[i].Key} = @key{i} AND";
                }
                sqlParameters.Add(new MySqlParameter($"key{i}", MySqlDbType.VarChar) { Value = checkList[i].Value });
            }

            try
            {
                using (MySqlConnection sqlConnection = new MySqlConnection(_connectionString))
                {
                    using (MySqlCommand sqlCommand = CreateCommand(query, sqlConnection, sqlParameters))
                    {
                        if (sqlCommand != null)
                        {
                            sqlConnection.Open();
                            object result = sqlCommand.ExecuteScalar();
                            if (result != null)
                            {
                                return true;
                            }
                            else
                            {
                                return false;
                            }
                        }
                        else
                        {
                            return false;
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                Debug.WriteLine(ex.Message);
                return false;
            }
        }

        /// <summary>
        /// Private Helpers
        /// </summary>
        private MySqlCommand CreateCommand(string query, MySqlConnection sqlConnection, List<MySqlParameter> sqlParameters)
        {
            try
            {
                MySqlCommand command = new MySqlCommand(query, sqlConnection);
                if (sqlParameters != null)
                {
                    foreach (MySqlParameter param in sqlParameters)
                    {
                        command.Parameters.Add(new MySqlParameter(param.ParameterName, param.MySqlDbType) { Value = param.Value });
                    }
                }
                return command;
            }
            catch (Exception ex)
            {
                Debug.WriteLine(ex.Message);
                return null;
            }
        }

    }
}