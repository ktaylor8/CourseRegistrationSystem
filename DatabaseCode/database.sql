-- student account 
create table student ( 
  -- unique id for each student 
  student_id int primary key not null, 
  -- password for log-in
  student_pass varchar(200) not null, 
  -- name in format: FirstName LastName
  student_name varchar(50) not null, 
  -- email 
  student_email varchar(30)
); 

-- professor account
create table professor ( 
  -- unique id for each professor
  prof_id int primary key not null , 
  -- password
  prof_pass varchar(200) not null, 
  -- Dr. FirstName LastName
  prof_name varchar(50) not null, 
  -- professor's email 
  prof_email varchar(30)
); 

-- course taught by a professor
create table course ( 
  -- with course number it makes up a unique course id ex) CS
  department varchar(6) not null, 
  -- 4 digit code
  course_num int not null, 
  -- number of students enrolled
  enrolled int default 0, 
  -- long name
  course_name varchar(40) not null,
  -- maximum capacity
  capacity int default 10, 
  -- price of the course (used to send to billing system)
  price double(6,2) not null, 
  -- list of required prerequisites
  prerequisites varchar(50) not null, 
  -- the Professor instructing the course
  prof_id int not null, 
  
  primary key (department, course_num),
  foreign key (prof_id) references professor (prof_id) 
); 

-- specific registration of a student in a course 
create table registration ( 
  -- department short code (could connect to a lookup table if wanted)
  department varchar(6) not null,
  -- identify course 
  course_num int not null, 
  -- C or A 
  registration_type varchar(1) not null,
  -- identify student enrolled in course 
  student_id int not null, 
  
  primary key (course_num, department, student_id),
  foreign key (student_id) references student (student_id),
  foreign key (department, course_num) references course (department, course_num)
);

-- this makes it so the registration type will only accept the desired values 
alter table registration add constraint registration_type_verification check (registration_type in ('C', 'A'));

-- procedure to add a student to the database with the id, account password, student full name, and email as parameters
-- ensures that a student doesn't share an id with a professor
delimiter $$
create procedure addStudent(id int, pass varchar(20), name varchar(50), email varchar(30))
begin 
    if (not exists (select 1 from student where student_id = id)) or (exists (select 1 from professor where prof_id = id)) then
      insert into student (student_id, student_pass, student_name, student_email) values (id, md5(pass), name, email);
    end if;
end$$
delimiter ;

-- procedure to add a professor to the database with the account password, student full name, and email as parameters
-- ensures that a professor doesn't share an id with a student
delimiter $$
create procedure addProfessor(id int, pass varchar(20), name varchar(50), email varchar(30))
begin 
    if (not exists (select 1 from student where student_id = id)) or (exists (select 1 from professor where prof_id = id)) then
      insert into professor (prof_id, prof_pass, prof_name, prof_email) values (id, sha1(pass), name, email);
    end if;
    
end$$
delimiter ;

-- procedure to create a course with the parameters department code, course number, full name, price, prerequisites, and professor id
-- professor id should be taken from the current account that the user is using
delimiter $$ 
create procedure createCourse(dept varchar(6), num int, name varchar(40), price double(6,2), prereqs varchar(50), prof int) 
begin
  if (not exists (select 1 from course where course_num = num and department = dept)) then
    insert into course (department, course_num, price, course_name, prerequisites, prof_id) values (dept, num, price, name, prereqs, prof);
  else 
    signal sqlstate '45001' 
    set message_text = 'This course already exists.';
  end if;
end$$
delimiter ; 

-- procedure to register a student in a course (not as an alternate)
-- this updates the enrolled attribute of the course to be incremented by one
delimiter $$ 
create procedure registerInCourse(dept varchar(6), num int, id int) 
begin
  declare enr int; 
  insert into registration values (dept, num, 'C', id);
  set enr = (select enrolled from course where department = dept and course_num = num);
  update course set enrolled = (enr + 1) where department = dept and course_num = num; 
end$$
delimiter ; 

-- procedure to register a student in a course as an alternate
-- this does not update the enrolled attribute of the course to be incremented by one
delimiter $$ 
create procedure registerInAlternate(dept varchar(6), num int, id int) 
begin
  insert into registration values  (dept, num, 'A', id);
end$$
delimiter ; 

-- procedure to display the students enrolled in a course
delimiter $$ 
create procedure viewStudentsInCourse(dept varchar(6), num int) 
begin
  select student_id as ID, student_name as Name, student_email as Email from registration natural join student where department = dept and course_num = num and registration_type = 'C';
end$$
delimiter ; 

-- procedure to cancel a course 
delimiter $$ 
create procedure cancelCourse(dept varchar(6), num int) 
begin
  delete from registration where department = dept and course_num = num;
  delete from course where department = dept and course_num = num;
end$$
delimiter ;

-- procedure to list the courses that are available that a student has not registered in (as an alternate or regular course)
delimiter $$ 
create procedure viewAvailableCourses(student int) 
begin
  select concat(department, ' ', course_num) as Course from course natural join registration where student_id != student;
end$$
delimiter ;

-- procedure to display the courses that a professor is currently teaching 
delimiter $$ 
create procedure viewProfsCourses(id int) 
begin
  select concat(department, ' ', course_num) as Course from course where prof_id = id;
end$$
delimiter ;

-- deletes contents of all tables 
delimiter $$ 
create procedure startFresh()
begin
  delete from registration;
  delete from course;
  delete from professor;
  delete from student;
end$$
delimiter ;

-- deletes all courses and registrations so that new ones can be added for the new semester
delimiter $$ 
create procedure newSemester()
begin
  delete from registration;
  delete from course;
end$$
delimiter ;

-- selects the number of courses a student has registered in 
delimiter $$ 
create procedure getCourseNum(id int)
begin
  select count(*) from registration natural join student where student_id = id and registration_type = 'C';
end$$
delimiter ;

-- selects the number of alternates a student has registered in
delimiter $$ 
create procedure getAltNum(id int)
begin
  select count(*) from registration natural join student where student_id = id and registration_type = 'A';
end$$
delimiter ;

-- procedure that returns the billing information for a student based on the courses they have registered in
delimiter $$
create procedure getBillingInfo(id int)
begin
  select concat(department, ' ', course_num) as Course, price as Price from course natural join registration where student_id = id and registration_type = 'C'; 
end$$ 
delimiter ;
