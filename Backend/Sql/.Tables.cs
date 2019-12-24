using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace KarbonWebForms.Sql
{
    /// <summary>
    /// These class structure representations of SQL Tables
    /// </summary>

    public class Account
    {
        public Account(string username = null, string email = null, string firstname = null, string lastname = null,
            string password = null, string skills = null, string theme = null, string picturepath = null)
        {
            Username = username;
            Email = email;
            FirstName = firstname;
            LastName = lastname;
            Password = password;
            Skills = skills;
            Theme = theme;
            Picturepath = picturepath;
        }


        public string Username { get; set; }
        public string Email { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string Password { get; set; }
        public string Skills { get; set; }
        public string Theme { get; set; }
        public string Picturepath { get; set; }
    }

    public class BuiltOn
    {
        public BuiltOn(string projectId = null, string taskId = null)
        {
            ProjectId = projectId;
            TaskId = taskId;
        }

        public string ProjectId { get; set; }
        public string TaskId { get; set; }
    }

    public class Creates
    {
        public Creates(string username = null, string email = null, string name = null, string projectId = null)
        {
            Username = username;
            Email = email;
            Name = name;
            ProjectId = projectId;
        }
        public string Username { get; set; }
        public string Email { get; set; }
        public string Name { get; set; }
        public string ProjectId { get; set; }
    }

    public class NotesPosted
    {
        public NotesPosted(string text = null, DateTime timeStamp = default, string username = null, string email = null, string taskId = null)
        {
            Text = text;
            TimeStamp = timeStamp;
            Username = username;
            Email = email;
            TaskId = taskId;
        }
        public string Text { get; set; }
        public DateTime TimeStamp { get; set; }
        public string Username { get; set; }
        public string Email { get; set; }
        public string TaskId { get; set; }
    }

    public class Organization
    {
        public Organization(string name = null, string license = null, DateTime activation = default, DateTime expiration = default)
        {
            Name = name;
            License = license;
            Activation = activation;
            Expiration = expiration;
        }
        public string Name { get; set; }
        public string License { get; set; }
        public DateTime Activation { get; set; }
        public DateTime Expiration { get; set; }
    }

    public class OrganizationAbilities
    {
        public OrganizationAbilities(string username = null, string email = null, string name = null, int removeUser = 0,
            int inviteUser = 0, int permissionsEditing = 0)
        {
            Username = username;
            Email = email;
            Name = name;
            RemoveUser = removeUser;
            InviteUser = inviteUser;
            PermissionsEditing = permissionsEditing;
        }

        public string Username { get; set; }
        public string Email { get; set; }
        public string Name { get; set; }
        public int RemoveUser { get; set; }
        public int InviteUser { get; set; }
        public int PermissionsEditing { get; set; }
    }

    public class Parent
    {
        public Parent(string parentTaskId = null, string childTaskId = null)
        {
            ParentTaskId = parentTaskId;
            ChildTaskId = childTaskId;
        }

        public string ParentTaskId { get; set; }
        public string ChildTaskId { get; set; }
    }

    public class Project
    {
        public Project(string projectId = null, DateTime projectDeadline = default, string projectDescription = null)
        {
            ProjectId = projectId;
            ProjectDeadline = projectDeadline;
            ProjectDescription = projectDescription;
        }

        public string ProjectId { get; set; }
        public DateTime ProjectDeadline { get; set; }
        public string ProjectDescription { get; set; }
    }

    public class ProjectAbilities
    {
        public ProjectAbilities(string username = null, string email = null, string projectId = null, 
            int assignmentEditing = 0, int projectEditing = 0, int createProject = 0)
        {
            Username = username;
            Email = email;
            ProjectId = projectId;
            AssignmentEditing = assignmentEditing;
            ProjectEditing = projectEditing;
            CreateProject = createProject;
        }
        public string Username { get; set; }
        public string Email { get; set; }
        public string ProjectId { get; set; }
        public int AssignmentEditing { get; set; }
        public int ProjectEditing { get; set; }
        public int CreateProject { get; set; }
    }

    public class Task
    {
        public Task(string taskId = null, DateTime taskDeadline = default, string taskDescription = null)
        {
            TaskId = taskId;
            TaskDeadline = taskDeadline;
            TaskDescription = taskDescription;
        }
        public string TaskId { get; set; }
        public DateTime TaskDeadline { get; set; }
        public string TaskDescription { get; set; }
    }

    public class TaskAbilities
    {
        public TaskAbilities(string username = null, string email = null, string taskId = null, int assignmentEditing = 0)
        {
            Username = username;
            Email = email;
            TaskId = taskId;
            AssignmentEditing = assignmentEditing;
        }
        public string Username { get; set; }
        public string Email { get; set; }
        public string TaskId { get; set; }
        public int AssignmentEditing { get; set; }
    }

    public class ProjectAssigned
    {
        public ProjectAssigned(string username = null, string email = null, string name = null, string projectId = null)
        {
            Username = username;
            Email = email;
            Name = name;
            ProjectId = projectId;
        }
        public string Username { get; set; }
        public string Email { get; set; }
        public string Name { get; set; }
        public string ProjectId { get; set; }
    }

    public class TaskAssigned
    {
        public TaskAssigned(string username = null, string email = null, string name = null, string taskId = null)
        {
            Username = username;
            Email = email;
            Name = name;
            TaskId = taskId;
        }
        public string Username { get; set; }
        public string Email { get; set; }
        public string Name { get; set; }
        public string TaskId { get; set; }
    }


    /// <summary>
    /// Key Value Pair For Checking Existance in a Table
    /// </summary>
    public class CheckValuePair
    {
        public CheckValuePair(string key = null, string value = null)
        {
            Key = key;
            Value = value;
        }

        public string Key { get; set; }
        public string Value { get; set; }
    }
}