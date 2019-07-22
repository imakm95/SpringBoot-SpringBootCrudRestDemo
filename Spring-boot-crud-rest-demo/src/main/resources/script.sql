drop table employeesb;
drop sequence employeesb_seq;
create table employeesb(id number(11), first_name varchar2(45), last_name varchar2(45), email varchar2(45), primary key (id));
create sequence employeesb_seq start with 1 increment by 1;
insert into employeesb(id,first_name,last_name,email) values (employeesb_seq.nextval,'Ashwani','Maurya','ashwani@gmail.com');
insert into employeesb(id,first_name,last_name,email) values (employeesb_seq.nextval,'Anjani','Maurya','anjani@gmail.com');
commit;
select * from employeesb;
