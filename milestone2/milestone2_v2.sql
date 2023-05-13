drop table Judges;
drop table Helps; 
drop table OlympicJudges;
drop table Volunteers;
drop table Watches;
drop table Audience;
drop table Participates;
drop table Game;
drop table Receives;
drop table Athlete_1;
drop table Athlete_2;
drop table OffersMedal;
drop table Event;
drop table OlympicEvent_1;
drop table OlympicEvent_2;


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
    (sport_name varchar(30) not null,
    sport_category varchar(40) not null,
    year int not null,
    season varchar(6) not null,
    primary key (sport_name, sport_category, year, season),
    foreign key (year, season) references OlympicEvent_1 ON DELETE CASCADE
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
    gender varchar(6),
    primary key (participantID),
    foreign key (DOB) references Athlete_2 ON DELETE CASCADE
    );

  
create table OffersMedal
    (medal_type varchar(6) not null,
    sport_name varchar(30) not null,
    sport_category varchar(40) not null, 
    year int not null,
    season varchar(6) not null,  
    primary key (medal_type, sport_name, sport_category, year, season),
    foreign key (sport_name, sport_category, year, season) references Event ON DELETE CASCADE
    );
  
create table Game
    (game_type varchar(20) not null,
    sport_name varchar(30) not null,
    sport_category varchar(40) not null,
    year int not null,
    season varchar(6) not null,
    date_ varchar(10), 
    time_ varchar(5),
    building_name varchar(30),
    primary key (game_type, sport_name,  sport_category, year, season),
    foreign key (sport_name, sport_category, year, season) references Event ON DELETE CASCADE
    );

create table Receives
    (participantID varchar(10) not null,
    medal_type varchar(6) not null,
	sport_name varchar(30) not null,
    sport_category varchar(40) not null,
    year int not null,
    season varchar(6) not null,
    primary key (participantID, medal_type, sport_name, sport_category, year, season),
    foreign key (participantID) references Athlete_1,
    foreign key (medal_type, sport_name, sport_category, year, season) references OffersMedal
    );
  
  
create table Participates
    (participantID varchar(10) not null,
    game_type varchar(20) not null,
    sport_name varchar(30) not null,
    sport_category varchar(40) not null,
    year int not null,
    season varchar(6) not null,
    primary key (participantID, game_type, sport_name, sport_category, year, season),
    foreign key (participantID) references Athlete_1,
    foreign key (game_type, sport_name, sport_category, year, season) references Game
    );
  
create table Audience
    (ticketID varchar(10) not null,
    name varchar(20),
    primary key (ticketID)
    );

  
create table Watches
    (game_type varchar(20) not null,
    sport_name varchar(30) not null,
    sport_category varchar(40) not null,
    year int not null,
    season varchar(6) not null,
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
    sport_name varchar(30) not null,
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
    sport_name varchar(30) not null,
    sport_category varchar(40) not null,
    year int not null,
    season varchar(6) not null,
    primary key (employeeID, game_type, sport_name, sport_category, year, season),
    foreign key (employeeID) references OlympicJudges, 
    foreign key (game_type, sport_name, sport_category, year, season) references Game
    );



-- OlympicEvent_2 inserts

INSERT INTO OlympicEvent_2
VALUES('Republic of Korea', 'Asia');
 
INSERT INTO OlympicEvent_2
VALUES('Canada', 'North America');
 
INSERT INTO OlympicEvent_2
VALUES('Japan', 'Asia');

INSERT INTO OlympicEvent_2
VALUES('Brazil', 'South America');
 
INSERT INTO OlympicEvent_2
VALUES('China', 'Asia');


-- OlympicEvent_1 inserts

 
INSERT INTO OlympicEvent_1
VALUES(2018, 'Winter', 'Republic of Korea', 'PyeongChang');

INSERT INTO OlympicEvent_1
VALUES(1988, 'Summer', 'Republic of Korea', 'Seoul');

INSERT INTO OlympicEvent_1
VALUES(2010, 'Winter', 'Canada', 'Vancouver');

INSERT INTO OlympicEvent_1
VALUES(1988, 'Winter', 'Canada', 'Calgary');

INSERT INTO OlympicEvent_1
VALUES(1976, 'Summer', 'Canada', 'Montreal');

INSERT INTO OlympicEvent_1
VALUES(2020, 'Summer', 'Japan', 'Tokyo');

INSERT INTO OlympicEvent_1
VALUES(1998, 'Winter', 'Japan', 'Nagano');

INSERT INTO OlympicEvent_1
VALUES(1972, 'Winter', 'Japan', 'Sapporo Hokkaido');

INSERT INTO OlympicEvent_1
VALUES(1964, 'Summer', 'Japan', 'Tokyo');

INSERT INTO OlympicEvent_1
VALUES(2016, 'Summer', 'Brazil', 'Rio');

INSERT INTO OlympicEvent_1
VALUES(2008, 'Summer', 'China', 'Beijing');


-- Event inserts

INSERT INTO Event
VALUES('Figure skating', 'Ice dance', 2018, 'Winter');
 
INSERT INTO Event
VALUES('Figure skating', 'Ladies singles', 2010, 'Winter');
 
INSERT INTO Event
VALUES('Archery', 'Mixed team', 2020, 'Summer');

INSERT INTO Event
VALUES('Gymnastics', 'Womens balance beam', 2020, 'Summer');

INSERT INTO Event
VALUES('Gymnastics', 'Womens individual all-around', 2016, 'Summer');
 
INSERT INTO Event
VALUES('Athletics', 'Mens 100 metres', 2008, 'Summer');
 

-- Athlete_2 inserts

INSERT INTO Athlete_2
VALUES('1989-05-17', 32);
 
INSERT INTO Athlete_2 
VALUES('1987-09-02', 34);
 
INSERT INTO Athlete_2 
VALUES('1990-09-05', 31);
 
INSERT INTO Athlete_2 
VALUES('2001-02-27', 20);
 
INSERT INTO Athlete_2 
VALUES('1997-03-14', 24);

INSERT INTO Athlete_2 
VALUES('1986-08-21', 35);


-- Athlete_1 inserts

INSERT INTO Athlete_1 
VALUES('C1', '1989-05-17', 'Canada', 'Tessa Virtue', 5, 'Female');

INSERT INTO Athlete_1 
VALUES('C2', '1987-09-02', 'Canada', 'Scott Moir', 5, 'Male');
 
INSERT INTO Athlete_1 
VALUES('K1', '1990-09-05', 'Republic of Korea', 'Kim Yuna', 2, 'Female');
 
INSERT INTO Athlete_1 
VALUES('K2', '2001-02-27', 'Republic of Korea', 'An San', 3, 'Female');
 
INSERT INTO Athlete_1 
VALUES('U1', '1997-03-14', 'United States', 'Simone Biles', 7, 'Female');

INSERT INTO Athlete_1
VALUES('U2', '1986-08-21', 'Jamaica', 'Usain Bolt', 8, 'Male');


-- OffersMedal inserts

INSERT INTO OffersMedal
VALUES('Gold', 'Figure skating', 'Ice dance', 2018, 'Winter');

INSERT INTO OffersMedal
VALUES('Gold', 'Figure skating', 'Ladies singles', 2010, 'Winter');

INSERT INTO OffersMedal
VALUES('Gold', 'Archery', 'Mixed team', 2020, 'Summer');

INSERT INTO OffersMedal
VALUES('Bronze', 'Gymnastics', 'Womens balance beam', 2020, 'Summer');

INSERT INTO OffersMedal
VALUES('Gold', 'Gymnastics', 'Womens individual all-around', 2016, 'Summer');

INSERT INTO OffersMedal
VALUES('Gold', 'Athletics', 'Mens 100 metres', 2008, 'Summer');


-- Game inserts

INSERT INTO Game
VALUES('Free dance', 'Figure skating', 'Ice dance', 2018, 'Winter', '2018-02-20', '10:00', 'Gangneung Ice Arena');

INSERT INTO Game
VALUES('Free skating', 'Figure skating', 'Ladies singles', 2010, 'Winter', '2010-02-25', '17:00', 'Pacific Coliseum');

INSERT INTO Game
VALUES('Gold medal match', 'Archery', 'Mixed team', 2020, 'Summer', '2021-07-24', '16:45', 'Yumenoshima Park');

INSERT INTO Game
VALUES('Final', 'Gymnastics', 'Womens balance beam', 2020, 'Summer', '2021-08-03', '17:50', 'Ariake Gymnastics Centre');

INSERT INTO Game
VALUES('Qualification', 'Gymnastics', 'Womens individual all-around', 2016, 'Summer', '2016-08-07', '10:30', 'Arena Olímpica do Rio');

INSERT INTO Game
VALUES('Semi-Final', 'Athletics', 'Mens 100 metres', 2008, 'Summer', '2008-08-16', '20:00', 'Beijing National Stadium');


-- Receives inserts

INSERT INTO Receives
VALUES('C1', 'Gold', 'Figure skating', 'Ice dance', 2018, 'Winter');

INSERT INTO Receives
VALUES('C2', 'Gold', 'Figure skating', 'Ice dance', 2018, 'Winter');

INSERT INTO Receives
VALUES('K1', 'Gold', 'Figure skating', 'Ladies singles', 2010, 'Winter');

INSERT INTO Receives
VALUES('K2', 'Gold', 'Archery', 'Mixed team', 2020, 'Summer');

INSERT INTO Receives
VALUES('U1', 'Bronze', 'Gymnastics', 'Womens balance beam', 2020, 'Summer');

INSERT INTO Receives
VALUES('U1', 'Gold', 'Gymnastics', 'Womens individual all-around', 2016, 'Summer');

INSERT INTO Receives
VALUES('U2', 'Gold', 'Athletics', 'Mens 100 metres', 2008, 'Summer');


-- Participates inserts

INSERT INTO Participates 
VALUES('C1', 'Free dance', 'Figure skating', 'Ice dance', 2018, 'Winter');

INSERT INTO Participates
VALUES('C2', 'Free dance', 'Figure skating', 'Ice dance', 2018, 'Winter');

INSERT INTO Participates
VALUES('K1', 'Free skating', 'Figure skating', 'Ladies singles', 2010, 'Winter');

INSERT INTO Participates
VALUES('K2', 'Gold medal match', 'Archery', 'Mixed team', 2020, 'Summer');

INSERT INTO Participates
VALUES('U1', 'Final', 'Gymnastics', 'Womens balance beam', 2020, 'Summer');

INSERT INTO Participates
VALUES('U1', 'Qualification', 'Gymnastics', 'Womens individual all-around', 2016, 'Summer');

INSERT INTO Participates
VALUES('U2', 'Semi-Final', 'Athletics', 'Mens 100 metres', 2008, 'Summer');


-- Audience inserts

INSERT INTO Audience
VALUES('1', 'Bryan Chan');

INSERT INTO Audience 
VALUES('2', 'Bob');

INSERT INTO Audience
VALUES('3', 'Bobby');

INSERT INTO Audience
VALUES('4', 'Bobby Bob');

INSERT INTO Audience
VALUES('5', 'Bryan Chan');


-- Watches inserts

INSERT INTO Watches
VALUES('Free dance', 'Figure skating', 'Ice dance', 2018, 'Winter', '1');

INSERT INTO Watches
VALUES('Free skating', 'Figure skating', 'Ladies singles',  2010, 'Winter', '1');

INSERT INTO Watches
VALUES('Gold medal match', 'Archery', 'Mixed team', 2020, 'Summer', '3');

INSERT INTO Watches
VALUES('Gold medal match', 'Archery', 'Mixed team', 2020, 'Summer', '4');

INSERT INTO Watches
VALUES('Semi-Final', 'Athletics', 'Mens 100 metres', 2008, 'Summer', '2');


--Volunteers inserts

INSERT INTO Volunteers
VALUES('0000', 'Jenny Lee');

INSERT INTO Volunteers
VALUES('0001', 'Bo Bobby');

INSERT INTO Volunteers
VALUES('0002', 'Joyce Chen');

INSERT INTO Volunteers
VALUES('0003', 'Bryan Chan');

INSERT INTO Volunteers
VALUES('0004', 'Jennifer Li');


-- OlympicJudges inserts

INSERT INTO OlympicJudges
VALUES('1000', 'Joyce Chen');

INSERT INTO OlympicJudges
VALUES('1001', 'BOBB');

INSERT INTO OlympicJudges
VALUES('1002', 'Jenny Lee');

INSERT INTO OlympicJudges
VALUES('1003', 'Bryan Chan');

INSERT INTO OlympicJudges
VALUES('1004', 'Boo');


-- Helps inserts

INSERT INTO Helps
VALUES('0000', 'Free dance', 'Figure skating', 'Ice dance', 2018, 'Winter', 'clean ice rink with zamboni');

INSERT INTO Helps
VALUES('0001', 'Free skating', 'Figure skating', 'Ladies singles', 2010, 'Winter', 'pick up flowers from rink');

INSERT INTO Helps
VALUES('0002', 'Gold medal match', 'Archery', 'Mixed team', 2020, 'Summer', 'sanitize audience seats');

INSERT INTO Helps
VALUES('0003', 'Gold medal match', 'Archery', 'Mixed team', 2020, 'Summer', 'sanitize audience seats');

INSERT INTO Helps
VALUES('0003', 'Free dance', 'Figure skating', 'Ice dance', 2018, 'Winter', 'organize audience seating');

INSERT INTO Helps
VALUES('0003', 'Free skating', 'Figure skating', 'Ladies singles', 2010, 'Winter', 'sell popcorn during game');

INSERT INTO Helps
VALUES('0003', 'Final', 'Gymnastics', 'Womens balance beam', 2020, 'Summer', 'sell merch during game');

INSERT INTO Helps
VALUES('0003', 'Qualification', 'Gymnastics', 'Womens individual all-around', 2016, 'Summer', 'organize athletes');

INSERT INTO Helps
VALUES('0003', 'Semi-Final', 'Athletics', 'Mens 100 metres', 2008, 'Summer', 'organize audience seatings');

INSERT INTO Helps
VALUES('0004', 'Qualification', 'Gymnastics', 'Womens individual all-around', 2016, 'Summer', 'help people find their seats');


-- Judges inserts

INSERT INTO Judges
VALUES('1000', 'Free dance', 'Figure skating', 'Ice dance', 2018, 'Winter');

INSERT INTO Judges
VALUES('1001', 'Free skating', 'Figure skating', 'Ladies singles', 2010, 'Winter');

INSERT INTO Judges
VALUES('1002', 'Final', 'Gymnastics', 'Womens balance beam', 2020, 'Summer');

INSERT INTO Judges
VALUES('1003', 'Qualification', 'Gymnastics', 'Womens individual all-around', 2016, 'Summer');

INSERT INTO Judges
VALUES('1004', 'Semi-Final', 'Athletics', 'Mens 100 metres', 2008, 'Summer');
