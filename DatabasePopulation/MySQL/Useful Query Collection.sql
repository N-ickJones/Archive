/*This is a summary query for our purposes.*/
select * from 
	(select count(*) as `accounts`           	from account         	      ) as a  ,
	(select count(*) as `organizations`      	from organization    		  ) as o  ,
	(select count(*) as `projects`           	from project         		  ) as p  ,
	(select count(*) as `creates`            	from creates         		  ) as c  ,
	(select count(*) as `tasks`              	from task            		  ) as t  ,
	(select count(*) as `projectAssignments` 	from projectassigned 		  ) as pa ,
	(select count(*) as `taskAssignments`    	from taskassigned    		  ) as ta ,
	(select count(*) as `builton`            	from builton         		  ) as bo ,
	(select count(*) as `parents`            	from parent          		  ) as par,
    (select count(*) as `taskabilities` 	 	from taskabilities   		  ) as tab,
    (select count(*) as `projectabilities` 	 	from projectabilities   	  ) as pab,
    (select count(*) as `organizationabilities`	from organizationabilities    ) as oab,
    (select count(*) as `notesposted`	 	 	from notesposted			  ) as np;

#RESET QUERY STUFF BELOW

SET SQL_SAFE_UPDATES = 0;
delete from organization          where name         != '';
delete from account               where username     != '';
delete from project               where projectid    != '';
delete from task                  where taskid       != '';
delete from builton               where taskid       != '';
delete from creates               where email        != ''; 
delete from projectAssigned       where email        != '';
delete from taskAssigned          where email        != '';
delete from parent                where parentTaskId != '';
delete from taskabilities         where taskId       != '';
delete from projectabilities      where projectid    != '';
delete from organizationabilities where name         != '';
delete from notesposted			  where taskid       != '';
SET SQL_SAFE_UPDATES = 1;
insert into account(Username       , Email                  , FirstName, LastName, Password , Skills, Theme, PicturePath) 
            values ('un'           ,'someEmail@bullshit.com', 'Travis' , 'Idlay' , 'nope'   , ''    , ''   , ''         ),
                   ('georgey'      ,'mbmnbm@bnmn.com'       ,'fn1234'  ,'ln1234' ,'password', ''    , ''   , ''         ),
                   ('TestUser12345','armyjonesn@gmail.com'  ,'Nicholas','Jones'  ,'password', ''    , ''   , ''         ),
                   ('TestUser321'  ,'armyjonesn@gmail.com'  ,'Nicholas','Jones'  ,'password', ''    , ''   , ''         ); 



/*The following set of queries assist in assigning organization leaders for all organizations generated, and 
 *grants them access to every project and task as befits their title.*/

/*This query ensures that at least one account from every organization is assigned to every project.
 *These accounts are considered to be the organization leaders from a generative perspective.*/
insert into projectassigned select * from 
	(select  username, email,projectid from 
		(select distinct organization.name, ProjectId from organization, creates where creates.name = organization.name
		order by organization.name, projectid) as orgProjects, 
		(select name, orgAccounts.username, email from 
			(select min(organization.name) as `name`, min(account.username) as `username`
			from account, organization, creates
			where organization.name = creates.name and account.username = creates.username
			group by organization.name) as orgAccounts, 
            account
			where account.username = orgAccounts.username) as orgLeads
		where orgProjects.name = orgLeads.name) as potentialAssignments left join
	(select * from 
		(select  username, email,projectid from 
			(select distinct organization.name, ProjectId from organization, creates where creates.name = organization.name
			order by organization.name, projectid) as orgProjects, 
			(select name, orgAccounts.username, email from 
				(select min(organization.name) as `name`, min(account.username) as `username`
				from account, organization, creates
				where organization.name = creates.name and account.username = creates.username
				group by organization.name) as orgAccounts, 
				account
				where account.username = orgAccounts.username) as orgLeads
			where orgProjects.name = orgLeads.name) as potentialAssignments inner join projectassigned using (username, email, projectid)) as duplicates
	using (username, email, projectid) 
	where duplicates.username is null;
/*This query gives all organization leaders full permissions throughout the projects within their organizations.*/
insert into projectabilities
	select username, email, projectid,1,1,1,1
		from (select distinct name, projectid from 
			(select username, email, name, creates.projectid as `projectid`, taskid 
				from builton, creates 
				where builton.projectid = creates.projectid) as projtaskmasterlist
			) as tasksByOrganization,
			(select orgAccounts.username, email, name from 
				(select min(organization.name) as `name`, min(account.username) as `username`
				from account, organization, creates
				where organization.name = creates.name and account.username = creates.username
				group by organization.name) as orgAccounts, 
				account
				where account.username = orgAccounts.username) as orgLeads
			where orgLeads.name like tasksByOrganization.name;
/*This query ensures that the organization leaders have full permissions throughout the organization.*/
insert into organizationabilities 
	select orgAccounts.username, email, name, 1, 1, 1 from 
			(select min(organization.name) as `name`, min(account.username) as `username`
			from account, organization, creates
			where organization.name = creates.name and account.username = creates.username
			group by organization.name) as orgAccounts, 
            account
			where account.username = orgAccounts.username;
/*This query inserts the non-duplicate task assignments for the organization leaders. */
insert into taskassigned
	select *
		from (select username, email, taskid
			from (select distinct name, taskid from 
				(select username, email, name, creates.projectid as `projectid`, taskid 
					from builton, creates 
					where builton.projectid = creates.projectid) as projtaskmasterlist
				) as tasksByOrganization,
				(select orgAccounts.username, email, name from 
					(select min(organization.name) as `name`, min(account.username) as `username`
					from account, organization, creates
					where organization.name = creates.name and account.username = creates.username
					group by organization.name) as orgAccounts, 
					account
					where account.username = orgAccounts.username) as orgLeads
				where orgLeads.name like tasksByOrganization.name) as candidates 
				left join 
				(select distinct * from 
					taskassigned inner join 
					(select username, email, taskid
					from (select distinct name, taskid from 
						(select username, email, name, creates.projectid as `projectid`, taskid 
							from builton, creates 
							where builton.projectid = creates.projectid) as projtaskmasterlist
						) as tasksByOrganization,
						(select orgAccounts.username, email, name from 
							(select min(organization.name) as `name`, min(account.username) as `username`
							from account, organization, creates
							where organization.name = creates.name and account.username = creates.username
							group by organization.name) as orgAccounts, 
							account
							where account.username = orgAccounts.username) as orgLeads
						where orgLeads.name like tasksByOrganization.name) as candidates 
						using (username, email, taskid)) as duplicates using (username, email, taskid)
		where duplicates.username is null;
/*This grants all organization leaders full permissions on all of their tasks.*/
insert into taskabilities
	 select username, email, taskid, 1, 1, 1, 1
		from (select distinct name, taskid from 
			(select username, email, name, creates.projectid as `projectid`, taskid 
				from builton, creates 
				where builton.projectid = creates.projectid) as projtaskmasterlist
			) as tasksByOrganization,
			(select orgAccounts.username, email, name from 
				(select min(organization.name) as `name`, min(account.username) as `username`
				from account, organization, creates
				where organization.name = creates.name and account.username = creates.username
				group by organization.name) as orgAccounts, 
				account
				where account.username = orgAccounts.username) as orgLeads
		where orgLeads.name like tasksByOrganization.name;


select * from 
	(select  username, email,projectid from 
		(select distinct organization.name, ProjectId from organization, creates where creates.name = organization.name
		order by organization.name, projectid) as orgProjects, 
		(select name, orgAccounts.username, email from 
			(select min(organization.name) as `name`, min(account.username) as `username`
			from account, organization, creates
			where organization.name = creates.name and account.username = creates.username
			group by organization.name) as orgAccounts, 
            account
			where account.username = orgAccounts.username) as orgLeads
		where orgProjects.name = orgLeads.name) as potentialAssignments left join
	(select * from 
		(select  username, email,projectid from 
			(select distinct organization.name, ProjectId from organization, creates where creates.name = organization.name
			order by organization.name, projectid) as orgProjects, 
			(select name, orgAccounts.username, email from 
				(select min(organization.name) as `name`, min(account.username) as `username`
				from account, organization, creates
				where organization.name = creates.name and account.username = creates.username
				group by organization.name) as orgAccounts, 
				account
				where account.username = orgAccounts.username) as orgLeads
			where orgProjects.name = orgLeads.name) as potentialAssignments inner join projectassigned using (username, email, projectid)) as duplicates
	using (username, email, projectid) 
	where duplicates.username is null;



/*This is a query that gets all of the organizationleaders and their project counts.*/
select  username, count(projectid) from 
	(select  username, email,projectid from 
		(select distinct organization.name, ProjectId from organization, creates where creates.name = organization.name
		order by organization.name, projectid) as orgProjects, 
		(select orgAccounts.username, email, name from 
			(select min(organization.name) as `name`, min(account.username) as `username`
			from account, organization, creates
			where organization.name = creates.name and account.username = creates.username
			group by organization.name) as orgAccounts, 
            account
			where account.username = orgAccounts.username) as orgLeads
		where orgProjects.name = orgLeads.name) as potentialAssignments
        group by username;
        
/*This gathers the organization leaders' un/pw pairs.*/
select orgAccounts.username, password, email, name 
	from 
		(select min(organization.name) as `name`, min(account.username) as `username`
			from account, organization, creates
			where organization.name = creates.name and account.username = creates.username
			group by organization.name) as orgAccounts, 
            account
	where account.username = orgAccounts.username;
        
        
 /* This query gathers org leader information for every taskid by organization.*/
 select username, email, taskid
	from (select distinct name, taskid from 
		(select username, email, name, creates.projectid as `projectid`, taskid 
			from builton, creates 
			where builton.projectid = creates.projectid) as projtaskmasterlist
		) as tasksByOrganization,
        (select orgAccounts.username, email, name from 
			(select min(organization.name) as `name`, min(account.username) as `username`
			from account, organization, creates
			where organization.name = creates.name and account.username = creates.username
			group by organization.name) as orgAccounts, 
            account
			where account.username = orgAccounts.username) as orgLeads
		where orgLeads.name like tasksByOrganization.name;
        
        
        
        
 /* This query gathers org leader information for every projectid by organization.*/
 select username, email, projectid
	from (select distinct name, projectid from 
		(select username, email, name, creates.projectid as `projectid`, taskid 
			from builton, creates 
			where builton.projectid = creates.projectid) as projtaskmasterlist
		) as tasksByOrganization,
        (select orgAccounts.username, email, name from 
			(select min(organization.name) as `name`, min(account.username) as `username`
			from account, organization, creates
			where organization.name = creates.name and account.username = creates.username
			group by organization.name) as orgAccounts, 
            account
			where account.username = orgAccounts.username) as orgLeads
		where orgLeads.name like tasksByOrganization.name;
            
            
select count(orgAccounts.username) from 
			(select min(organization.name) as `name`, min(account.username) as `username`
			from account, organization, creates
			where organization.name = creates.name and account.username = creates.username
			group by organization.name) as orgAccounts, 
            account
			where account.username = orgAccounts.username;

select * from organizationabilities;

select count(*)
	from (select username, email, taskid
		from (select distinct name, taskid from 
			(select username, email, name, creates.projectid as `projectid`, taskid 
				from builton, creates 
				where builton.projectid = creates.projectid) as projtaskmasterlist
			) as tasksByOrganization,
			(select orgAccounts.username, email, name from 
				(select min(organization.name) as `name`, min(account.username) as `username`
				from account, organization, creates
				where organization.name = creates.name and account.username = creates.username
				group by organization.name) as orgAccounts, 
				account
				where account.username = orgAccounts.username) as orgLeads
			where orgLeads.name like tasksByOrganization.name) as candidates 
            left join 
			(select distinct * from 
				taskassigned inner join 
				(select username, email, taskid
				from (select distinct name, taskid from 
					(select username, email, name, creates.projectid as `projectid`, taskid 
						from builton, creates 
						where builton.projectid = creates.projectid) as projtaskmasterlist
					) as tasksByOrganization,
					(select orgAccounts.username, email, name from 
						(select min(organization.name) as `name`, min(account.username) as `username`
						from account, organization, creates
						where organization.name = creates.name and account.username = creates.username
						group by organization.name) as orgAccounts, 
						account
						where account.username = orgAccounts.username) as orgLeads
					where orgLeads.name like tasksByOrganization.name) as candidates 
					using (username, email, taskid)) as duplicates using (username, email, taskid)
                    where duplicates.username is null;

/*This is a duplicate collection query for the task assignments for all organization leaders.*/
select count(*) from (select * from 
				taskassigned inner join 
				(select username, email, taskid
				from (select distinct name, taskid from 
					(select username, email, name, creates.projectid as `projectid`, taskid 
						from builton, creates 
						where builton.projectid = creates.projectid) as projtaskmasterlist
					) as tasksByOrganization,
					(select orgAccounts.username, email, name from 
						(select min(organization.name) as `name`, min(account.username) as `username`
						from account, organization, creates
						where organization.name = creates.name and account.username = creates.username
						group by organization.name) as orgAccounts, 
						account
						where account.username = orgAccounts.username) as orgLeads
					where orgLeads.name like tasksByOrganization.name) as candidates 
					using (username, email, taskid)) as duplicates;

#From here, there are only scratch queries used throughout development for various purposes.

select * 
from parent;

select * from taskabilities;

select * 
from task 
where taskid like '981';

select * 
from account;

select * 
from account 
where username like 'un';

select * 
from(
	select un, count(un) as num
	from (
		select creates.username as un, password as pw 
		from account, creates 
        where creates.email = account.email) as coolstuff
	group by un) as morestuff 
where num > 2;

select * 
from creates 
where username = 'TreesCareT';

select * 
from account 
where username like 'TreesCareT';

select * 
from project 
where projectId like '103';

alter table task add column `TaskCompleted` DATETIME after `TaskDeadline`;

select * 
from task;

insert into task value ('1337', '2019-01-01', null, 'hahahaha' );

Set SQL_SAFE_UPDATES = 0;
delete from projectassigned;
Insert into projectassigned select * from creates;
Set SQL_SAFE_UPDATES = 1;

select * from task;
select * from builton;
select * from taskassigned;
select * from projectassigned;