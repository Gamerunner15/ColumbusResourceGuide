DROP TABLE IF EXISTS foodbank_zipcodes;
DROP TABLE IF EXISTS foodbank;
DROP TABLE IF EXISTS app_user;
DROP TABLE IF EXISTS address;

CREATE TABLE address (

        id serial,
        street_address varchar(64) NOT NULL,
        city varchar(64) NOT NULL,
        state varchar(64) NOT NULL,
        zipcode integer NOT NULL,
        building_type varchar(64),
        
        CONSTRAINT pk_address primary key (id)


);

CREATE TABLE app_user (
    
    username varchar(64) NOT NULL,
    password varchar(64) NOT NULL,
    first_name varchar(64) NOT NULL,
    last_name varchar(64) NOT NULL,
    birth_date date,
    gender varchar(64),
    address_id integer,
    annual_income decimal,
    needs_phone boolean,
    needs_job boolean,
    needs_food boolean,
    needs_housing boolean,
    
    constraint pk_user primary key (username),
    constraint fk_app_user_address foreign key (address_id) references address(id)
 
);


CREATE TABLE foodbank (

        id serial,
        name varchar(64) NOT NULL,
        address_id integer NOT NULL,
        open_day integer,
        closed_day integer,
        open_time integer,
        closed_time integer,
        phone_number varchar(10),
        
        constraint pk_foodbank primary key (id),
        constraint fk_foodbank_address foreign key (address_id) references address(id)
);

CREATE TABLE foodbank_zipcodes (

        zipcode integer NOT NULL,
        foodbank_id integer NOT NULL,
        
        constraint pk_foodbank_zipcodes primary key (zipcode, foodbank_id),
        constraint fk_foodbank_zipcodes_foodbank foreign key (foodbank_id) references foodbank(id)

);




------------------------------------------------------INSERT FOODBANKS-----------------------------------
--Address 1
INSERT INTO address (street_address, city, state, zipcode)
VALUES ('1950 N. 4th Street', 'Columbus', 'Ohio', 43201);
--Foodbank 1
INSERT INTO foodbank (name, address_id, open_day, closed_day, open_time, closed_time, phone_number)
VALUES ('Neighborhood Services, Inc.', 1, 2, 5, 10, 13, '6142970592');
--Zips
INSERT INTO foodbank_zipcodes (zipcode, foodbank_id)
VALUES (43201, 1);

INSERT INTO foodbank_zipcodes (zipcode, foodbank_id)
VALUES (43202, 1);

INSERT INTO foodbank_zipcodes (zipcode, foodbank_id)
VALUES (43211, 1);

INSERT INTO foodbank_zipcodes (zipcode, foodbank_id)
VALUES (43212, 1);

--Address 2
INSERT INTO address (street_address, city, state, zipcode)
VALUES ('1460 S. Champion Avenue', 'Columbus', 'Ohio', 43206);
--Foodank 2
INSERT INTO foodbank (name, address_id, open_day, closed_day, open_time, closed_time, phone_number)
VALUES ('Champion Avenue Food Pantry', 2, 2, 5, 12, 14, '6144435130');
--Zips
INSERT INTO foodbank_zipcodes (zipcode, foodbank_id)
VALUES (43206, 2);

--------------------------INSERT Users
INSERT INTO address (street_address, city, state, zipcode, building_type)
VALUES ('1473 Arlington Ave', 'Columbus', 'Ohio', 43211, 'House');
INSERT INTO app_user (username, password, first_name, last_name, birth_date, gender, address_id, annual_income, needs_phone, needs_job, needs_food, needs_housing)
VALUES ('gamerunner15', 'crosscountry', 'Kolton', 'Nay', CAST('1993-01-07' AS DATE), 'Gender-Queer', 3, 30000, false, true, true, false);


INSERT INTO app_user (username, password, first_name, last_name, birth_date)
VALUES ('RoseofThorns', 'blackrose', 'Kayla', 'Nay', CAST('1994-12-04' AS DATE));


--TESTS
SELECT * FROM address
JOIN app_user ON app_user.address_id = address.id
WHERE app_user.username = 'gamerunner15' AND app_user.password = 'crosscountry';

SELECT * FROM app_user
JOIN address ON app_user.address_id = address.id
WHERE app_user.username = 'RoseofThorns';


UPDATE app_user
SET birth_date = CAST('1993-01-07' AS DATE),
gender = 'Gender-Queer',
annual_income = 30000,
needs_phone = false,
needs_job = true,
needs_food = false,
needs_housing = false
WHERE username = 'gamerunner15';


