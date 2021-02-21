use attendancedb;

desc user;

ALTER TABLE user ADD CONSTRAINT email_unq UNIQUE (uemail);
ALTER TABLE user ADD CONSTRAINT userid_unq UNIQUE (userid);

select * from user;
select * from skill;

create table slots(
slotid int primary key,
slotname varchar(30)
);

select * from trainer;

insert into slots values (1,"morning"),(2,"afternoon"),(3,"evening"),(4,"night");
