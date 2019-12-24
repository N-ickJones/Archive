import psycopg2
from psycopg2.extensions import AsIs
from decouple import config


def main():

    schema_name = "codeblood_schema"
    table_name = "user_accounts"
    remove_type = 'CASCADE'
    selection = input("Build or Remove? ")

    if 'Build' in selection or 'build' in selection or 'b' in selection:
        build = True
    elif 'Remove' in selection or 'remove' in selection or 'r' in selection:
        build = False
    elif 'Cancel' in selection or 'cancel' in selection or 'c' in selection:
        return
    else:
        return

    schema = Schema(schema_name, remove_type)
    tables = Tables(schema_name, table_name, remove_type)

    if build:
        if schema.exist() is False:
            print("Schema", schema_name, "does not exist, creating it now.")
            schema.create()
        else:
            print("Schema", schema_name, "up to date.")
        if tables.exist() is False:
            print("Table(s) do(es) not exist creating Now")
            tables.create()
        else:
            print("Table(s) up to date.")

    elif build is False:
        if tables.exist() is True:
            print("Table(s)", table_name, "exist dropping Now")
            tables.drop()
        else:
            print("Table(s) didn't exist and nothing performed.")
        if schema.exist() is True:
            print("Schema", schema_name, "exists, dropping Now")
            schema.drop()
        else:
            print("Schema didn't exist and nothing performed.")
    else:
        return

    main()


class Schema:

    def __init__(self, schema_name, remove_type):
        self.conn = psycopg2.connect(config('DATABASE_URL'))
        self.cur = self.conn.cursor()
        self.schema_name = schema_name
        self.remove_type = remove_type

    def exist(self):
        sql = "SELECT EXISTS(SELECT 1 FROM information_schema.schemata WHERE schema_name = %s);"
        data = (self.schema_name,)
        self.cur.execute(sql, data)
        result = self.cur.fetchone()
        if 'False' in str(result):
            return False
        elif 'True' in str(result):
            return True

    def create(self):
        # TODO Fix to Rest
        statement = 'CREATE SCHEMA ' + self.schema_name
        self.cur.execute(statement)
        print("Schema created successfully")
        self.conn.commit()
        self.conn.close()

    def drop(self):
        # TODO FIX to Rest
        statement = 'DROP SCHEMA ' + self.schema_name + ' ' + self.remove_type
        self.cur.execute(statement)
        print("Schema dropped successfully")
        self.conn.commit()
        self.conn.close()


class Tables:

    def __init__(self, schema_name, table_name, remove_type):
        self.conn = psycopg2.connect(config('DATABASE_URL'))
        self.cur = self.conn.cursor()
        self.schema_name = schema_name
        self.table_name = table_name
        self.remove_type = remove_type

    def exist(self):
        sql = "SELECT EXISTS(SELECT 1 FROM information_schema.tables WHERE table_schema = %s AND table_name = %s);"
        data = (self.schema_name, self.table_name,)

        self.cur.execute(sql, data)
        result = self.cur.fetchone()
        # print(result)
        if 'True' in str(result):
            return True
        elif 'False' in str(result):
            return False

    def create(self):
        sql = """CREATE TABLE %s.%s(
                    ID       INT          NOT NULL PRIMARY KEY,
                    USERNAME VARCHAR(50)  NOT NULL,
                    PASSWORD VARCHAR(128) NOT NULL,
                    F_NAME   VARCHAR(30)  NOT NULL,
                    L_NAME   VARCHAR(30)  NOT NULL,
                    EMAIL    VARCHAR(100) NOT NULL,
                    AGE      INT,
                    ADDRESS  VARCHAR(150),
                      );"""

        data = (AsIs(self.schema_name), AsIs(self.table_name))
        print(sql, data)
        self.cur.execute(sql, data)
        print("Table created successfully")
        self.conn.commit()
        self.conn.close()

    def drop(self):
        self.cur.execute('''DROP TABLE %s.%s %s ''', (AsIs(self.schema_name), AsIs(self.table_name),
                                                      AsIs(self.remove_type)))
        print("Table dropped successfully")
        self.conn.commit()
        self.conn.close()


main()

"""
    Side Notes
    result = cur.fetchall()
    conn = psycopg2.connect(database="test", user='user', password='password', host="127.0.0.1", port="5432")
    CASCADE FORCE DELETES TABLE,
    RESTRICT DELETES TABLE IF THERE ARE NO RELATIONSHIPS
"""
