DELETE FROM member where member_id > 0;
INSERT INTO member (member_id,first_name,last_name,email,approved,ranking) values (1,'a1','a2','a3',1,1);
INSERT INTO member (member_id,first_name,last_name,email,approved,ranking) values (2,'b1','b2','b3',1,2);
INSERT INTO member (member_id,first_name,last_name,email,approved,ranking) values (3,'c1','c2','c3',1,3);