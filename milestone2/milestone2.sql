-- drop table OlympicEvent_1;
-- drop table OlympicEvent_2;
-- drop table Event;
-- drop table Athlete_1;
-- drop table Athlete_2;
-- drop table Receives;
-- drop table OffersMedal;
-- drop table Game;
-- drop table Participates;
-- drop table Audience;
-- drop table Watches;
-- drop table Volunteers;
-- drop table OlympicJudges;
-- drop table Helps;
-- drop table Judges; 

create table OlympicEvent_2
    (country varchar(20) not null,
    continent varchar(15),
    primary key (country)
    );    


create table OlympicEvent_1
    (year int not null,
    season varchar(6) not null,
    country varchar(20),
    city varchar(20),
    primary key (year, season),
    foreign key (country) references OlympicEvent_2 ON DELETE CASCADE
    );



create table Event
	(sport_name varchar(20) not null,
    sport_category varchar(40) not null,
    year int not null,
    season varchar(6) not null,
    primary key (sport_name,sport_category,year,season),
    foreign key (year,season) references OlympicEvent_1 ON DELETE CASCADE
    );
    
  
create table Athlete_2
    (DOB varchar(10) not null,
    age int,
    primary key (DOB)
    );

create table Athlete_1
	(participantID varchar(10) not null,
    DOB varchar(10),
    country varchar(20),
    name varchar(20),
    medal_count int,
    gender varchar(5),
    primary key (participantID),
    foreign key (DOB) references Athlete_2 ON DELETE CASCADE
    );

  
create table OffersMedal
    (season varchar(6) not null, 
    sport_name varchar(20) not null, 
    sport_category varchar(40) not null, 
    year int not null,
    medal_type varchar(6) not null,
    primary key (season,sport_name,sport_category, year, medal_type),
    foreign key (sport_name,sport_category,year,season) references Event ON DELETE CASCADE
    );
  
create table Game
	(game_type varchar(20) not null,
    sport_name varchar(20) not null,
    sport_category varchar(40) not null,
    year int not null,
    season varchar(6) not null,
    date_ varchar(10), 
    time_ varchar(5),
    building_name varchar(30),
    primary key (game_type, sport_name, season, year, sport_category),
    foreign key (sport_name, sport_category, year, season) references Event ON DELETE CASCADE
    );

create table Receives
    (participantID varchar(10) not null,
    medal_type varchar(6) not null,
	sport_name varchar(20) not null,
    sport_category varchar(40) not null,
    season varchar(6) not null,
    year int not null,
    primary key (participantID, medal_type, sport_name, sport_category, season, year),
    foreign key (participantID) references Athlete_1,
    foreign key (season, sport_name, sport_category, year, medal_type) references OffersMedal
    );
  
  
create table Participates
	(participantID varchar(10) not null,
    sport_name varchar(20) not null,
    sport_category varchar(40) not null,
    year int not null,
    season varchar(6) not null,
    primary key (participantID,sport_name, sport_category, year, season),
    foreign key (participantID) references Athlete_1,
    foreign key (sport_name, sport_category, year, season) references Event
    );
  
create table Audience
	(ticketID varchar(10) not null,
    name varchar(20),
    primary key (ticketID)
    );

  
create table Watches
	(game_type varchar(20) not null,
    sport_name varchar(20) not null,
    sport_category varchar(40) not null,
    season varchar(6) not null,
    year int not null,
    ticketID varchar(10) not null,
    primary key (game_type, sport_name, sport_category, year, season, ticketID),
    foreign key (game_type, sport_name, sport_category, year, season) references Game,
    foreign key (ticketID) references Audience
    );


create table Volunteers
	(employeeID varchar(10) not null,
    name varchar(20),
    primary key (employeeID)
    );

  
create table OlympicJudges
    (employeeID varchar(10) not null,
    name varchar(20),
    primary key (employeeID)
    );

  
create table Helps	
 	(employeeID varchar(10) not null,
    game_type varchar(20) not null,
    sport_name varchar(20) not null,
    sport_category varchar(40) not null,
    year int not null,
    season varchar(6) not null,
    task varchar(50),
    primary key (employeeID, game_type, sport_name, sport_category, year, season),
    foreign key (employeeID) references Volunteers, 
    foreign key (game_type, sport_name, sport_category, year, season) references Game
    );
  
  
create table Judges
	(employeeID varchar(10) not null,
    game_type varchar(20) not null,
    sport_name varchar(20) not null,
    sport_category varchar(40) not null,
    year int not null,
    season varchar(6) not null,
    primary key (employeeID, game_type, sport_name, sport_category, year, season),
    foreign key (employeeID) references OlympicJudges, 
    foreign key (game_type, sport_name, sport_category, year, season) references Game
    );



-- OlympicEvent_2 inserts
 
INSERT INTO OlympicEvent_2(country, continent)
VALUES('Japan', 'Asia');
 
INSERT INTO OlympicEvent_2(country, continent)
VALUES('Republic of Korea', 'Asia');
 
INSERT INTO OlympicEvent_2(country, continent)
VALUES('Canada', 'North America');
 
INSERT INTO OlympicEvent_2(country, continent)
VALUES('China', 'Asia');
 
INSERT INTO OlympicEvent_2(country, continent)
VALUES('Australia', 'Oceania');


-- OlympicEvent_1 inserts

INSERT INTO OlympicEvent_1(year, season, country, city)
VALUES(2020, 'Summer', 'Japan', 'Tokyo');
 
INSERT INTO OlympicEvent_1(country, year, season, city)
VALUES(2018, 'Winter', 'Republic of Korea', 'PyeongChang');

INSERT INTO OlympicEvent_1(country, year, season, city)
VALUES(2010, 'Winter', 'Canada', 'Vancouver');

INSERT INTO OlympicEvent_1(country, year, season, city)
VALUES(2008, 'Summer', 'China', 'Beijing');
 
INSERT INTO OlympicEvent_1(country, year, season, city)
VALUES(2000, 'Summer', 'Australia', 'Sydney');
 


-- Event inserts

INSERT INTO Event(sport_name, sport_category, year, season)
VALUES("Athletics", "Men's 100 metres", 2020, 'Summer');
 
INSERT INTO Event(sport_name, sport_category, year, season)
VALUES("Athletics", "Women's 100 metres", 2020, 'Summer');
 
INSERT INTO Event(sport_name, sport_category, year, season)
VALUES("Athletics", "Men's 200 metres", 2020, 'Summer');

INSERT INTO Event (sport_name, sport_category, year, season)
VALUES("Athletics", "Women's 200 metres", 2020, 'Summer');
 
INSERT INTO Event(sport_name, sport_category, year, season)
VALUES("Athletics", "Men's 200 metres", 2008, 'Summer');
 


-- Athlete_2 inserts

INSERT INTO Athlete_2(DOB, age)
VALUES('1994-11-10', 26);
 
INSERT INTO Athlete_2 (DOB, age)
VALUES('1986-08-21', 35);
 
INSERT INTO Athlete_2 (DOB, age)
VALUES('1997-03-14', 24);
 
INSERT INTO Athlete_2 (DOB, age)
VALUES('1985-06-30', 36);
 
INSERT INTO Athlete_2 (DOB, age)
VALUES('1994-05-25', 27);



-- Athlete_1 inserts

INSERT INTO Athlete_1(participantID, DOB, country, name, medal_count, gender) 
VALUES('C1', '1994-11-10', 'Canada', 'Andre De Grasse', 6, 'Male');
 
INSERT INTO Athlete_1(participantID, DOB, country, name, medal_count, gender) 
VALUES('J1', '1986-08-21', 'Jamaica', 'Usain Bolt', 8, 'Male');
 
INSERT INTO Athlete_1(participantID, DOB, country, name, medal_count, gender) 
VALUES('U1', '1997-03-14', 'United States', 'Simone Biles', 7, 'Female');
 
INSERT INTO Athlete_1(participantID, DOB, country, name, medal_count, gender) 
VALUES('U2', '1985-06-30', 'United States', 'Michael PHelps', 28, 'Male');
 
INSERT INTO Athlete_1(participantID, DOB, country, name, medal_count, gender) 
VALUES('U3', '1994-05-25', 'United States', 'Aly Raisman', 6, 'Female');


-- OffersMedal inserts

INSERT INTO OffersMedal(season, sport_name, sport_category, year, medal_type)
VALUES('Winter', 'Figure skating', 'Ice dance', 2018, 'Gold');

INSERT INTO OffersMedal(season, sport_name, sport_category, year, medal_type)
VALUES('Winter', 'Figure skating', "Ladies' singles", 2014, 'Silver');

INSERT INTO OffersMedal(season, sport_name, sport_category, year, medal_type)
VALUES('Summer', 'Archery', 'Mixed team', 2020, 'Gold');

INSERT INTO OffersMedal(season, sport_name, sport_category, year, medal_type)
VALUES('Summer', 'Archery', 'Mixed team', 2020, 'Silver');

INSERT INTO OffersMedal(season, sport_name, sport_category, year, medal_type)
VALUES('Summer', 'Athletics', '100 metres', 2012, 'Bronze');



-- Game inserts

INSERT INTO Game(game_type, sport_name, sport_category, year, season, date_, time_, building_name)
VALUES('final', 'Figure skating', "Ladies' singles", 2014, 'Winter', '2014-02-20', '19:00', 'Iceberg Skating Palace');

INSERT INTO Game(game_type, sport_name, sport_category, year, season, date_, time_, building_name)
VALUES('final', 'Figure skating', 'Ice dance', 2018, 'Winter', '2018-02-20', '10:00', 'Gangneung Ice Arena');

INSERT INTO Game(game_type, sport_name, sport_category, year, season, date_, time_, building_name)
VALUES('final', 'Speed skating ', "Women's 500 metres", 2014, 'Winter', '2014-02-11', '16:45', 'Adler Arena');

INSERT INTO Game(game_type, sport_name, sport_category, year, season, date_, time_, building_name)
VALUES('semi-final', 'Athletics', "Women's 100 metres", 2020, 'Summer', '2020-07-30', '11:00', 'Japan National Stadium');

INSERT INTO Game(game_type, sport_name, sport_category, year, season, date_, time_, building_name)
VALUES('semi-final', 'Athletics', "Men's 100 metres", 2020, 'Summer', '2020-07-31', '11:00', 'Japan National Stadium');



-- Receives inserts

INSERT INTO Receives(participantID, medal_type, sport_name, sport_category, season, year)
VALUES('C1', 'Gold', 'Athletics', "Men's 200 metres", 'Summer', 2020);

INSERT INTO Receives(participantID, medal_type, sport_name, sport_category, season, year)
VALUES('C1', 'Bronze', 'Athletics', "Men's 100 metres", 'Summer', 2016);

INSERT INTO Receives(participantID, medal_type, sport_name, sport_category, season, year)
VALUES('U3', 'Silver', 'Gymnastics', "Women's individual all-around", 'Summer', 2016);

INSERT INTO Receives(participantID, medal_type, sport_name, sport_category, season, year)
VALUES('U1', 'Gold', 'Gymnastics', "Women's vault", 'Summer', 2016);

INSERT INTO Receives(participantID, medal_type, sport_name, sport_category, season, year)
VALUES('U2', 'Gold', 'Swimming', "Men's 200m individual medley", 'Summer', 2016);



-- Participates inserts
INSERT INTO Participates (participantID, sport_name, sport_category, year, season)
VALUES('U2', 'Swimming', "Men's 200m individual medley", 2016, 'Summer');

INSERT INTO Participates(participantID, sport_name, sport_category, year, season)
VALUES('U1', 'Gymnastics', "Women's vault", 2016, 'Summer');

INSERT INTO Participates(participantID, sport_name, sport_category, year, season)
VALUES('U1', 'Gymnastics', "Women's individual all-around", 2016, 'Summer');

INSERT INTO Participates(participantID, sport_name, sport_category, year, season)
VALUES('U3', 'Gymnastics', "Women's individual all-around", 2016, 'Summer');

INSERT INTO Participates(participantID, sport_name, sport_category, year, season)
VALUES('U3', 'Gymnastics', "Women's artistic team all-around", 2012, 'Summer');



-- Audience inserts

INSERT INTO Audience (ticketID, name)
VALUES('1', 'Bryan Chan');

INSERT INTO Audience (ticketID, name)
VALUES('2', 'Bob');

INSERT INTO Audience (ticketID, name)
VALUES('3', 'Bobby');

INSERT INTO Audience (ticketID, name)
VALUES('4', 'Bobby Bob');

INSERT INTO Audience (ticketID, name)
VALUES('5', 'Bryan Chan');


-- Watches inserts

INSERT INTO Watches(game_type, sport_name, sport_category, season, year, ticketID)
VALUES('Finals', 'Swimming', "Men's 200m individual medley", 'Summer', 2012 , '1');

INSERT INTO Watches(game_type, sport_name, sport_category, season, year, ticketID)
VALUES('Finals', 'Athletics', "Men's 200 metres", 'Summer', 2016 , '1');

INSERT INTO Watches(game_type, sport_name, sport_category, season, year, ticketID)
VALUES('Finals', 'Athletics', "Men's 400 metres",'Summer', 2020 , '3');

INSERT INTO Watches(game_type, sport_name, sport_category, season, year, ticketID)
VALUES('Semi-Finals', 'Athletics', "Men's 100 metres", 'Summer', 2012 , '4');

INSERT INTO Watches(game_type, sport_name, sport_category, season, year, ticketID)
VALUES('Finals', 'Athletics', "Men's 100 metres",'Summer', 2012 , '2');


--Volunteers inserts

INSERT INTO Volunteers(employeeID, name)
VALUES('0000', 'Jenny Lee');

INSERT INTO Volunteers(employeeID, name)
VALUES('0001', 'Bo Bobby');

INSERT INTO Volunteers(employeeID, name)
VALUES('0002', 'Joyce Chen');

INSERT INTO Volunteers(employeeID, name)
VALUES('0003', 'Bryan Chan');

INSERT INTO Volunteers(employeeID, name)
VALUES('0004', 'Jennifer Li');


-- OlympicJudges inserts

INSERT INTO OlympicJudge(employeeID, name)
VALUES('1000', 'Joyce Chen');

INSERT INTO OlympicJudge(employeeID, name)
VALUES('1001', 'BOBB');

INSERT INTO OlympicJudge(employeeID, name)
VALUES('1002', 'Jenny Lee');

INSERT INTO OlympicJudge(employeeID, name)
VALUES('1003', 'Bryan Chan');

INSERT INTO OlympicJudge(employeeID, name)
VALUES('1004', 'Boo');


-- Helps inserts

INSERT INTO Helps(employeeID, game_type, sport_name, sport_category, year, season, task)
VALUES('0000', 'entertain', 'Finals', 'Athletics', "Men's 200 metres", 2012, 'Summer');

INSERT INTO Helps(employeeID, game_type, sport_name, sport_category, year, season, task)
VALUES('0001', 'clean', 'Finals', 'Athletics', "Men's 200 metres", 2012, 'Summer');

INSERT INTO Helps(employeeID, game_type, sport_name, sport_category, year, season, task)
VALUES('0002', 'clean', 'Finals', 'Athletics', "Men's 200 metres", 2012, 'Summer');

INSERT INTO Helps(employeeID, game_type, sport_name, sport_category, year, season, task)
VALUES('0003', 'entertain', 'Finals', 'Athletics', "Men's 200 metres", 2012, 'Summer');

INSERT INTO Helps(employeeID, game_type, sport_name, sport_category, year, season, task)
VALUES('0004', 'cheer', 'Finals', 'Athletics', "Men's 200 metres", 2016, 'Summer');


-- Judges inserts


INSERT INTO judges(employeeID, game_type, sport_name, sport_category, year, season)
VALUES('1000', 'final', 'Figure skating', 'Ice dance', 2018, 'Winter');

INSERT INTO judges(employeeID, game_type, sport_name, sport_category, year, season)
VALUES('1001', 'final', 'Short track speed skating', "Women's team 3000 metre relay", 2018, 'Winter');

INSERT INTO judges(employeeID, game_type, sport_name, sport_category, year, season)
VALUES('1002', 'final', 'Gymnastics', "Women's individual all-around", 2020, 'Summer');

INSERT INTO judges(employeeID, game_type, sport_name, sport_category, year, season)
VALUES('1003', 'semi-final', 'Gymnastics', "Women's individual all-around", 2020, 'Summer');

INSERT INTO judges(employeeID, game_type, sport_name, sport_category, year, season)
VALUES('1004', 'semi-final', 'Athletics', "Men's 200 metres", 2020, 'Summer');

  
