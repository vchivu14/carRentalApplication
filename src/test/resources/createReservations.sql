INSERT INTO car (car_id,brand,model,price_per_day) values (1,'ferrari','f50',1000);
INSERT INTO car (car_id,brand,model,price_per_day) values (2,'mercedes','benz',800);
INSERT INTO car (car_id,brand,model,price_per_day) values (3,'ford','mustang',600);
INSERT INTO member (member_id,first_name,last_name,email,approved,ranking) values (1,'a1','a2','a3',1,1);
INSERT INTO member (member_id,first_name,last_name,email,approved,ranking) values (2,'b1','b2','b3',1,2);
INSERT INTO member (member_id,first_name,last_name,email,approved,ranking) values (3,'c1','c2','c3',1,3);
INSERT INTO reservation (rental_date, reserved_car_car_id, reserved_to_member_id) values (current_date, 1, 1);
INSERT INTO reservation (rental_date, reserved_car_car_id, reserved_to_member_id) values (current_date, 2, 2);