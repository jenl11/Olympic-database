package Database;

import UI.ErrorWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.ibatis.jdbc.ScriptRunner;



public class DatabaseConnectionHandler {

    // Use this version of the ORACLE_URL if you are running the code off of the server
	//private static final String ORACLE_URL = "jdbc:oracle:thin:@dbhost.students.cs.ubc.ca:1522:stu";
    // Use this version of the ORACLE_URL if you are tunneling into the undergrad servers
    private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";

    private Connection connection = null;


    public DatabaseConnectionHandler() {
        try {
            // Load the Oracle JDBC driver
            // Note that the path could change for new drivers
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void databaseSetup() {
        try {
            ScriptRunner sr = new ScriptRunner(connection);
            Reader reader = new BufferedReader(new FileReader("resources/sql/databaseSetup.sql"));
            sr.runScript(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean login() {
        try {
            if (connection != null) {
                connection.close();
            }

            connection = DriverManager.getConnection(ORACLE_URL, "ora_bch4n", "a56432339");
            connection.setAutoCommit(false);

            System.out.println("\nConnected to Oracle!");
            return true;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return false;
        }
    }
    //ObservableList<String> athleteAttributes = FXCollections.observableArrayList("Age","Participant ID",
    //            "Name", "Country", "Medal Count", "Birthday", "Gender");
    //    ObservableList<String> eventAttributes = FXCollections.observableArrayList("Sport Name",
    //            "Sport Category", "Year", "Season");
    //    ObservableList<String> gameAttributes = FXCollections.observableArrayList("Game Type","Sport Name",
    //            "Sport Category", "Year", "Season", "Date", "Time", "Building Name");
    //    ObservableList<String> audienceAttributes = FXCollections.observableArrayList("Ticket ID","Name");
    //    ObservableList<String> medalAttributes = FXCollections.observableArrayList("Medal Type", "Sport Name",
    //            "Sport Category", "Year", "Season");
    //    ObservableList<String> olympicEventAttributes = FXCollections.observableArrayList("Country", "Continent",
    //            "Year", "Season", "City");
    //    ObservableList<String> judgeAttributes = FXCollections.observableArrayList("Employee ID","Name");
    //    ObservableList<String> volunteerAttributes = FXCollections.observableArrayList("Employee ID","Name");



    //////////////////////////////////////// INSERT //////////////////////////////////////////////


    public void insertAthlete(String[] entryList) {
        // entrylist order:
        //ObservableList<String> athleteAttributes = FXCollections.observableArrayList("Participant ID",
        //            "Name", "Country", "Medal Count", "Birthday", "Gender");
        // NOTE: Strings can be null but int can't, so we need if statements for ints
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Athlete VALUES (?,?,?,?,?,?)");
            ps.setString(1, entryList[0]);
            ps.setString(2, entryList[4]);
            ps.setString(3, entryList[2]);
            ps.setString(4, entryList[1]);
            if (entryList[5] == null) {
                ps.setNull(5, Types.INTEGER);
            } else {
                ps.setInt(5, Integer.parseInt(entryList[3]));
            }
            ps.setString(6, entryList[5]);

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        } catch (NumberFormatException e) {
            new ErrorWindow("expected integer but got other types.");
            rollbackConnection();
        }
    }

    public void insertAudience(String[] entryList) {
        //    ObservableList<String> audienceAttributes = FXCollections.observableArrayList("Ticket ID","Name");

        try {

            PreparedStatement ps = connection.prepareStatement("INSERT INTO Audience VALUES (?,?)");
            ps.setString(1, entryList[0]);
            ps.setString(2, entryList[1]);

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }

    }

    public void insertEvent(String[] entryList) {
        //    ObservableList<String> eventAttributes = FXCollections.observableArrayList("Sport Name",
        //            "Sport Category", "Year", "Season");

        try {

            PreparedStatement ps = connection.prepareStatement("INSERT INTO Event VALUES (?,?,?,?)");
            ps.setString(1, entryList[0]);
            ps.setString(2, entryList[1]);
            if (entryList[2] == null) {
                ps.setNull(3, Types.INTEGER);
            } else {
                ps.setInt(3, Integer.parseInt(entryList[2]));
            }
            ps.setString(4, entryList[3]);

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        } catch (NumberFormatException e) {
            new ErrorWindow("expected integer but got other types.");
            rollbackConnection();
        }

    }

    public void insertGame(String[] entryList) {
        //    ObservableList<String> gameAttributes = FXCollections.observableArrayList("Game Type","Sport Name",
        //            "Sport Category", "Year", "Season", "Date", "Time", "Building Name");
        try {

            PreparedStatement ps = connection.prepareStatement("INSERT INTO Game VALUES (?,?,?,?,?,?,?,?)");
            ps.setString(1, entryList[0]);
            ps.setString(2, entryList[1]);
            ps.setString(3, entryList[2]);
            if (entryList[3] == null) {
                ps.setNull(3, Types.INTEGER);
            } else {
                ps.setInt(4, Integer.parseInt(entryList[3]));
            }
            ps.setString(5, entryList[4]);
            ps.setString(6, entryList[5]);
            ps.setString(7, entryList[6]);
            ps.setString(8, entryList[7]);

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        } catch (NumberFormatException e) {
            new ErrorWindow("expected integer but got other types.");
            rollbackConnection();
        }

    }


    public void insertOlympicEvent(String[] entryList) {
        //    ObservableList<String> olympicEventAttributes = FXCollections.observableArrayList("Country",
        //            "Year", "Season", "City");
        try {

            PreparedStatement ps = connection.prepareStatement("INSERT INTO OlympicEvent VALUES (?,?,?,?)");

            if (entryList[1] == null) {
                ps.setNull(1, Types.INTEGER);

            } else {
                ps.setInt(1, Integer.parseInt(entryList[1]));
            }
            ps.setString(2, entryList[2]);
            ps.setString(3, entryList[0]);
            ps.setString(4, entryList[3]);

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        } catch (NumberFormatException e) {
            new ErrorWindow("expected integer but got other types.");
            rollbackConnection();
        }

    }

    public void insertJudge(String[] entryList) {
        //    ObservableList<String> judgeAttributes = FXCollections.observableArrayList("Employee ID","Name");
        try {

            PreparedStatement ps = connection.prepareStatement("INSERT INTO OlympicJudges VALUES (?,?)");

            ps.setString(1, entryList[0]);
            ps.setString(2, entryList[1]);


            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }


    }

    public void insertVolunteer(String[] entryList) {
        try {

            PreparedStatement ps = connection.prepareStatement("INSERT INTO Volunteers VALUES (?,?)");
            ps.setString(1, entryList[0]);
            ps.setString(2, entryList[1]);


            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }
//
//    ObservableList<String> privateList = FXCollections.observableArrayList("Athletes", "Audiences",
//            "Events", "Games", "Medals Offered Records", "Olympic Events", "Olympic Judges", "Volunteers", "Volunteer Records",
//            "Participation Records", "Medals Received Records", "Watch Records");

    public void insertMedalsOfferedRecord(String[] entryList) {
        try {

            PreparedStatement ps = connection.prepareStatement("INSERT INTO OffersMedal VALUES (?,?,?,?,?)");
            ps.setString(1, entryList[0]);
            ps.setString(2, entryList[1]);
            ps.setString(3, entryList[2]);
            if (entryList[3] == null) {
                ps.setNull(4,Types.INTEGER);
            } else {
                ps.setString(4, entryList[3]);
            }
            ps.setString(5, entryList[4]);


            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }


    public void insertVolunteerRecord(String[] entryList) {
        try {

            PreparedStatement ps = connection.prepareStatement("INSERT INTO Helps VALUES (?,?,?,?,?,?,?)");
            ps.setString(1, entryList[0]);
            ps.setString(2, entryList[1]);
            ps.setString(3, entryList[2]);
            ps.setString(4, entryList[3]);
            if (entryList[4] == null) {
                ps.setNull(5,Types.INTEGER);
            } else {
                ps.setString(5, entryList[4]);
            }
            ps.setString(6, entryList[5]);
            ps.setString(7, entryList[6]);


            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void insertParticipationRecord(String[] entryList) {
        try {

            PreparedStatement ps = connection.prepareStatement("INSERT INTO Participates VALUES (?,?,?,?,?,?)");
            ps.setString(1, entryList[0]);
            ps.setString(2, entryList[1]);
            ps.setString(3, entryList[2]);
            ps.setString(4, entryList[3]);
            if (entryList[4] == null) {
            ps.setNull(5,Types.INTEGER);
            } else {
                ps.setString(5, entryList[4]);
            }
            ps.setString(6, entryList[5]);


            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void insertMedalsReceivedRecord(String[] entryList) {
        try {

            PreparedStatement ps = connection.prepareStatement("INSERT INTO Receives VALUES (?,?,?,?,?,?)");
            ps.setString(1, entryList[0]);
            ps.setString(2, entryList[1]);
            ps.setString(3, entryList[2]);
            ps.setString(4, entryList[3]);
            if (entryList[4] == null) {
                ps.setNull(5,Types.INTEGER);
            } else {
                ps.setString(5, entryList[4]);
            }
            ps.setString(6, entryList[5]);


            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void insertWatchRecord(String[] entryList) {
        try {

            PreparedStatement ps = connection.prepareStatement("INSERT INTO Volunteers VALUES (?,?,?,?,?,?)");
            ps.setString(1, entryList[0]);
            ps.setString(2, entryList[1]);
            ps.setString(3, entryList[2]);
            if (entryList[3] == null) {
                ps.setNull(4,Types.INTEGER);
            } else {
                ps.setString(4, entryList[3]);
            }
            ps.setString(5, entryList[4]);
            ps.setString(6, entryList[5]);


            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }




    ///////////////////////////////////////////// LOOKUP ////////////////////////////////////////////////////

    public String combineProjection(String[] projectionList) {
        String resultString = "";
        for (int i = 0; i < projectionList.length; i++) {
            String currProjection = projectionList[i];
            if (resultString.length() == 0) {
                resultString = currProjection;
            } else {
                resultString += ", " + currProjection;
            }
        }

        return resultString;
    }

    public ObservableList<HashMap> getAthlete(String[] projectionStringList, String filterString) {
        try {
            String projectionString = combineProjection(projectionStringList);
            Statement stmt = connection.createStatement();
            ResultSet rs;
            String sqlQuery;
            if (filterString.length() == 0) {
                // case no WHERE
                sqlQuery = "SELECT " + projectionString + " FROM Athlete";
                rs = stmt.executeQuery(sqlQuery);

            } else {
                // case has WHERE
                sqlQuery = "SELECT " + projectionString + " FROM Athlete WHERE " + filterString;
                rs = stmt.executeQuery(sqlQuery);
            }

            ObservableList<HashMap> allData = FXCollections.observableArrayList();

            while (rs.next()) {
                HashMap<String, String> hm = new LinkedHashMap<>();
                for (int i = 0; i < projectionStringList.length; i++) {
                    hm.put(projectionStringList[i], rs.getString(projectionStringList[i]));
                }
                allData.add(hm);
            }
            rs.close();
            stmt.close();

            return allData;

        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
        }

        return null;
    }


    public ObservableList<HashMap> getAudience(String[] projectionStringList, String filterString) {
        try {
            String projectionString = combineProjection(projectionStringList);
            Statement stmt = connection.createStatement();
            ResultSet rs;
            String sqlQuery;
            if (filterString.length() == 0) {
                // case no WHERE
                sqlQuery = "SELECT " + projectionString + " FROM Audience";
                rs = stmt.executeQuery(sqlQuery);

            } else {
                // case has WHERE
                sqlQuery = "SELECT " + projectionString + " FROM Audience WHERE " + filterString;
                rs = stmt.executeQuery(sqlQuery);
            }

            ObservableList<HashMap> allData = FXCollections.observableArrayList();

            while (rs.next()) {
                HashMap<String, String> hm = new LinkedHashMap<>();
                for (int i = 0; i < projectionStringList.length; i++) {
                    hm.put(projectionStringList[i], rs.getString(projectionStringList[i]));
                }
                allData.add(hm);
            }
            rs.close();
            stmt.close();

            return allData;

        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
        }

        return null;
    }


    public ObservableList<HashMap> getEvent(String[] projectionStringList, String filterString) {

        try {
            String projectionString = combineProjection(projectionStringList);
            Statement stmt = connection.createStatement();
            ResultSet rs;
            String sqlQuery;
            if (filterString.length() == 0) {
                // case no WHERE
                sqlQuery = "SELECT " + projectionString + " FROM Event";
                rs = stmt.executeQuery(sqlQuery);

            } else {
                // case has WHERE
                sqlQuery = "SELECT " + projectionString + " FROM Event WHERE " + filterString;
                rs = stmt.executeQuery(sqlQuery);
            }

            ObservableList<HashMap> allData = FXCollections.observableArrayList();

            while (rs.next()) {
                HashMap<String, String> hm = new LinkedHashMap<>();
                for (int i = 0; i < projectionStringList.length; i++) {
                    hm.put(projectionStringList[i], rs.getString(projectionStringList[i]));
                }
                allData.add(hm);
            }
            rs.close();
            stmt.close();

            return allData;

        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
        }

        return null;
    }

    public ObservableList<HashMap> getGames(String[] projectionStringList, String filterString) {
        try {
            String projectionString = combineProjection(projectionStringList);
            Statement stmt = connection.createStatement();
            ResultSet rs;
            String sqlQuery;
            if (filterString.length() == 0) {
                // case no WHERE
                sqlQuery = "SELECT " + projectionString + " FROM Game";
                rs = stmt.executeQuery(sqlQuery);

            } else {
                // case has WHERE
                sqlQuery = "SELECT " + projectionString + " FROM Game WHERE " + filterString;
                rs = stmt.executeQuery(sqlQuery);
            }
//            create table Game
//                    (game_type varchar(20) not null,
//                    sport_name varchar(30) not null,
//                    sport_category varchar(40) not null,
//                    year int not null,
//                    season varchar(6) not null,
//                    date_ varchar(10),
//                    time_ varchar(5),
//                    building_name varchar(30),
//                    primary key (game_type, sport_name,  sport_category, year, season),
//                    foreign key (sport_name, sport_category, year, season) references Event ON DELETE CASCADE
//    );

            //    ObservableList<String> gameAttributes = FXCollections.observableArrayList("Game Type","Sport Name",
            //            "Sport Category", "Year", "Season", "Date", "Time", "Building Name");

            ObservableList<HashMap> allData = FXCollections.observableArrayList();

            while (rs.next()) {
                HashMap<String, String> hm = new LinkedHashMap<>();
                for (int i = 0; i < projectionStringList.length; i++) {
                    hm.put(projectionStringList[i], rs.getString(projectionStringList[i]));
                }
                allData.add(hm);
            }
            rs.close();
            stmt.close();

            return allData;

        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
        }

        return null;
    }

    public ObservableList<HashMap> getOlympicEvent(String[] projectionStringList, String filterString) {
        String projectionString = combineProjection(projectionStringList);
        String sqlQuery;
        if (filterString.length() == 0) {
            // case no WHERE
            sqlQuery = "SELECT " + projectionString + " FROM OlympicEvent";

        } else {
            // case has WHERE
            sqlQuery = "SELECT " + projectionString + " FROM OlympicEvent WHERE " + filterString;
        }


        try{
            Statement stmt = connection.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery(sqlQuery);
            ObservableList<HashMap> allData = FXCollections.observableArrayList();

            while (rs.next()) {
                HashMap<String, String> hm = new LinkedHashMap<>();
                for (int i = 0; i < projectionStringList.length; i++) {
                    hm.put(projectionStringList[i], rs.getString(projectionStringList[i]));
                }
                allData.add(hm);
            }
            rs.close();
            stmt.close();

            return allData;
        }catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
        }

        return null;
    }

    public ObservableList<HashMap> getOlympicJudges(String[] projectionStringList, String filterString) {
        try {
            String projectionString = combineProjection(projectionStringList);
            Statement stmt = connection.createStatement();
            ResultSet rs;
            String sqlQuery;
            if (filterString.length() == 0) {
                // case no WHERE
                sqlQuery = "SELECT " + projectionString + " FROM OlympicJudges";
                rs = stmt.executeQuery(sqlQuery);

            } else {
                // case has WHERE
                sqlQuery = "SELECT " + projectionString + " FROM OlympicJudges WHERE " + filterString;
                rs = stmt.executeQuery(sqlQuery);
            }


            ObservableList<HashMap> allData = FXCollections.observableArrayList();

            while (rs.next()) {
                HashMap<String, String> hm = new LinkedHashMap<>();
                for (int i = 0; i < projectionStringList.length; i++) {
                    hm.put(projectionStringList[i], rs.getString(projectionStringList[i]));
                }
                allData.add(hm);
            }
            rs.close();
            stmt.close();

            return allData;

        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
        }

        return null;
    }

    public ObservableList<HashMap> getVolunteers(String[] projectionStringList, String filterString) {
        try {
            String projectionString = combineProjection(projectionStringList);
            Statement stmt = connection.createStatement();
            ResultSet rs;
            String sqlQuery;
            if (filterString.length() == 0) {
                // case no WHERE
                sqlQuery = "SELECT " + projectionString + " FROM Volunteers ";
                rs = stmt.executeQuery(sqlQuery);

            } else {
                // case has WHERE
                sqlQuery = "SELECT " + projectionString + " FROM Volunteers WHERE " + filterString;
                rs = stmt.executeQuery(sqlQuery);
            }


            ObservableList<HashMap> allData = FXCollections.observableArrayList();

            while (rs.next()) {
                HashMap<String, String> hm = new LinkedHashMap<>();
                for (int i = 0; i < projectionStringList.length; i++) {
                    hm.put(projectionStringList[i], rs.getString(projectionStringList[i]));
                }
                allData.add(hm);
            }
            rs.close();
            stmt.close();

            return allData;

        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
        }

        return null;
    }

    public ObservableList<HashMap> getVolunteerRecords(String[] projectionStringList, String filterString) {
        try {
            String projectionString = combineProjection(projectionStringList);
            Statement stmt = connection.createStatement();
            ResultSet rs;
            String sqlQuery;
            if (filterString.length() == 0) {
                // case no WHERE
                sqlQuery = "SELECT " + projectionString + " FROM Helps ";
                rs = stmt.executeQuery(sqlQuery);

            } else {
                // case has WHERE
                sqlQuery = "SELECT " + projectionString + " FROM Helps WHERE " + filterString;
                rs = stmt.executeQuery(sqlQuery);
            }


            ObservableList<HashMap> allData = FXCollections.observableArrayList();

            while (rs.next()) {
                HashMap<String, String> hm = new LinkedHashMap<>();
                for (int i = 0; i < projectionStringList.length; i++) {
                    hm.put(projectionStringList[i], rs.getString(projectionStringList[i]));
                }
                allData.add(hm);
            }
            rs.close();
            stmt.close();

            return allData;

        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
        }

        return null;
    }

    public ObservableList<HashMap> getParticipationRecords(String[] projectionStringList, String filterString) {
        try {
            String projectionString = combineProjection(projectionStringList);
            Statement stmt = connection.createStatement();
            ResultSet rs;
            String sqlQuery;
            if (filterString.length() == 0) {
                // case no WHERE
                sqlQuery = "SELECT " + projectionString + " FROM Participates ";
                rs = stmt.executeQuery(sqlQuery);

            } else {
                // case has WHERE
                sqlQuery = "SELECT " + projectionString + " FROM Participates WHERE " + filterString;
                rs = stmt.executeQuery(sqlQuery);
            }


            ObservableList<HashMap> allData = FXCollections.observableArrayList();

            while (rs.next()) {
                HashMap<String, String> hm = new LinkedHashMap<>();
                for (int i = 0; i < projectionStringList.length; i++) {
                    hm.put(projectionStringList[i], rs.getString(projectionStringList[i]));
                }
                allData.add(hm);
            }
            rs.close();
            stmt.close();

            return allData;

        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
        }

        return null;
    }

    public ObservableList<HashMap> getMedalsReceivedRecords(String[] projectionStringList, String filterString) {
        try {
            String projectionString = combineProjection(projectionStringList);
            Statement stmt = connection.createStatement();
            ResultSet rs;
            String sqlQuery;
            if (filterString.length() == 0) {
                // case no WHERE
                sqlQuery = "SELECT " + projectionString + " FROM Receives ";
                rs = stmt.executeQuery(sqlQuery);

            } else {
                // case has WHERE
                sqlQuery = "SELECT " + projectionString + " FROM Receives WHERE " + filterString;
                rs = stmt.executeQuery(sqlQuery);
            }


            ObservableList<HashMap> allData = FXCollections.observableArrayList();

            while (rs.next()) {
                HashMap<String, String> hm = new LinkedHashMap<>();
                for (int i = 0; i < projectionStringList.length; i++) {
                    hm.put(projectionStringList[i], rs.getString(projectionStringList[i]));
                }
                allData.add(hm);
            }
            rs.close();
            stmt.close();

            return allData;

        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
        }

        return null;
    }

    public ObservableList<HashMap> getMedalsOfferedRecords(String[] projectionStringList, String filterString) {
        try {
            String projectionString = combineProjection(projectionStringList);
            Statement stmt = connection.createStatement();
            ResultSet rs;
            String sqlQuery;
            if (filterString.length() == 0) {
                // case no WHERE
                sqlQuery = "SELECT " + projectionString + " FROM OffersMedal ";
                rs = stmt.executeQuery(sqlQuery);

            } else {
                // case has WHERE
                sqlQuery = "SELECT " + projectionString + " FROM OffersMedal WHERE " + filterString;
                rs = stmt.executeQuery(sqlQuery);
            }


            ObservableList<HashMap> allData = FXCollections.observableArrayList();

            while (rs.next()) {
                HashMap<String, String> hm = new LinkedHashMap<>();
                for (int i = 0; i < projectionStringList.length; i++) {
                    hm.put(projectionStringList[i], rs.getString(projectionStringList[i]));
                }
                allData.add(hm);
            }
            rs.close();
            stmt.close();

            return allData;

        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
        }

        return null;
    }


    public ObservableList<HashMap> getWatchRecords(String[] projectionStringList, String filterString) {
        try {
            String projectionString = combineProjection(projectionStringList);
            Statement stmt = connection.createStatement();
            ResultSet rs;
            String sqlQuery;
            if (filterString.length() == 0) {
                // case no WHERE
                sqlQuery = "SELECT " + projectionString + " FROM Watches ";
                rs = stmt.executeQuery(sqlQuery);

            } else {
                // case has WHERE
                sqlQuery = "SELECT " + projectionString + " FROM Watches WHERE " + filterString;
                rs = stmt.executeQuery(sqlQuery);
            }


            ObservableList<HashMap> allData = FXCollections.observableArrayList();

            while (rs.next()) {
                HashMap<String, String> hm = new LinkedHashMap<>();
                for (int i = 0; i < projectionStringList.length; i++) {
                    hm.put(projectionStringList[i], rs.getString(projectionStringList[i]));
                }
                allData.add(hm);
            }
            rs.close();
            stmt.close();

            return allData;

        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
        }

        return null;
    }




    //////////////////////////////////////////// DELETE //////////////////////////////////////////////////

    public void deleteAthlete(String filterString) {
        try {
            String sqlQuery;
            if (filterString.length() == 0) {
                sqlQuery = "DELETE FROM Athlete";
            } else {
                sqlQuery = "DELETE FROM Athlete WHERE " + filterString;
            }
            PreparedStatement ps = connection.prepareStatement(sqlQuery);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                new ErrorWindow("No such Athlete");
            }
            connection.commit();
            ps.close();


        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }

    }

    public void deleteAudience(String filterString) {
        try {
            String sqlQuery;
            if (filterString.length() == 0) {
                sqlQuery = "DELETE FROM Audience";
            } else {
                sqlQuery = "DELETE FROM Audience WHERE " + filterString;
            }
            PreparedStatement ps = connection.prepareStatement(sqlQuery);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                new ErrorWindow("no such audience");
            }
            connection.commit();
            ps.close();


        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }

    }

    public void deleteEvent(String filterString) {
        try {
            String sqlQuery;
            if (filterString.length() == 0) {
                sqlQuery = "DELETE FROM Event";
            } else {
                sqlQuery = "DELETE FROM Event WHERE " + filterString;
            }
            PreparedStatement ps = connection.prepareStatement(sqlQuery);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                new ErrorWindow("No such Event");
            }
            connection.commit();
            ps.close();


        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }

    }

    public void deleteGame(String filterString) {

        try {
            String sqlQuery;
            if (filterString.length() == 0) {
                sqlQuery = "DELETE FROM Game";
            } else {
                sqlQuery = "DELETE FROM Game WHERE " + filterString;
            }
            PreparedStatement ps = connection.prepareStatement(sqlQuery);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                new ErrorWindow("No such Game");
            }
            connection.commit();
            ps.close();


        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void deleteOlympicEvent(String filterString) {
        try {
            String sqlQuery;
            if (filterString.length() == 0) {
                sqlQuery = "DELETE FROM OlympicEvent";
            } else {
                sqlQuery = "DELETE FROM OlympicEvent WHERE " + filterString;
            }
            PreparedStatement ps = connection.prepareStatement(sqlQuery);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                new ErrorWindow("No such Olympic Event");
            }
            connection.commit();
            ps.close();


        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }

    }

    public void deleteOlympicJudges(String filterString) {
        try {
            String sqlQuery;
            if (filterString.length() == 0) {
                sqlQuery = "DELETE FROM OlympicJudges";
            } else {
                sqlQuery = "DELETE FROM OlympicJudges WHERE " + filterString;
            }
            PreparedStatement ps = connection.prepareStatement(sqlQuery);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                new ErrorWindow("No such Judge");
            }
            connection.commit();
            ps.close();


        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }

    }

    public void deleteVolunteers(String filterString) {
        try {
            String sqlQuery;
            if (filterString.length() == 0) {
                sqlQuery = "DELETE FROM Volunteers";
            } else {
                sqlQuery = "DELETE FROM Volunteers WHERE " + filterString;
            }
            PreparedStatement ps = connection.prepareStatement(sqlQuery);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                new ErrorWindow("No such Volunteers");
            }
            connection.commit();
            ps.close();


        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }

    }

    public void deleteMedalsOfferedRecord(String filterString) {

        try {
            String sqlQuery;
            if (filterString.length() == 0) {
                sqlQuery = "DELETE FROM OffersMedal";
            } else {
                sqlQuery = "DELETE FROM OffersMedal WHERE " + filterString;
            }
            PreparedStatement ps = connection.prepareStatement(sqlQuery);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                new ErrorWindow("No such Medals Offered Record");
            }
            connection.commit();
            ps.close();


        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void deleteVolunteerRecord(String filterString) {

        try {
            String sqlQuery;
            if (filterString.length() == 0) {
                sqlQuery = "DELETE FROM Helps";
            } else {
                sqlQuery = "DELETE FROM Helps WHERE " + filterString;
            }
            PreparedStatement ps = connection.prepareStatement(sqlQuery);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                new ErrorWindow("No such Volunteer Record");
            }
            connection.commit();
            ps.close();


        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void deleteParticipationRecord(String filterString) {

        try {
            String sqlQuery;
            if (filterString.length() == 0) {
                sqlQuery = "DELETE FROM Participates";
            } else {
                sqlQuery = "DELETE FROM Participates WHERE " + filterString;
            }
            PreparedStatement ps = connection.prepareStatement(sqlQuery);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                new ErrorWindow("No such Participation Record");
            }
            connection.commit();
            ps.close();


        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void deleteWatchRecord(String filterString) {

        try {
            String sqlQuery;
            if (filterString.length() == 0) {
                sqlQuery = "DELETE FROM Watches";
            } else {
                sqlQuery = "DELETE FROM Watches WHERE " + filterString;
            }
            PreparedStatement ps = connection.prepareStatement(sqlQuery);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                new ErrorWindow("No such Watch Record");
            }
            connection.commit();
            ps.close();


        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void deleteMedalReceivedRecord(String filterString) {

        try {
            String sqlQuery;
            if (filterString.length() == 0) {
                sqlQuery = "DELETE FROM Receives";
            } else {
                sqlQuery = "DELETE FROM Receives WHERE " + filterString;
            }
            PreparedStatement ps = connection.prepareStatement(sqlQuery);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                new ErrorWindow("No such Medal Received Record");
            }
            connection.commit();
            ps.close();


        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }




    //////////////////////////////////// UPDATE ///////////////////////////////////////////////////////////////////////

    public void updateAthlete(String updateString, String filterString) {
        String sqlQuery;
        try {
//            String projectionString = combineProjection(projectionStringList);
            Statement stmt = connection.createStatement();
            if (filterString.length() == 0) {
                // case no WHERE
                sqlQuery = "UPDATE Athlete SET " + updateString;
                stmt.executeQuery(sqlQuery);

            } else {
                // case has WHERE
                sqlQuery = "UPDATE Athlete SET " + updateString + " WHERE " + filterString;
                stmt.executeQuery(sqlQuery);
            }
            stmt.close();
        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
        }

    }

    public void updateAudience(String updateString, String filterString) {
        String sqlQuery;
//        if (filterString.length() == 0) {
//            // case no WHERE
//            sqlQuery = "UPDATE Audience SET " + updateString;
//
//        } else {
//            // case has WHERE
//            sqlQuery = "UPDATE Audience SET " + updateString + " WHERE " + filterString;
//        }
        try {
//            String projectionString = combineProjection(projectionStringList);
            Statement stmt = connection.createStatement();
            if (filterString.length() == 0) {
                // case no WHERE
                sqlQuery = "UPDATE Audience SET " + updateString;
                stmt.executeQuery(sqlQuery);

            } else {
                // case has WHERE
                sqlQuery = "UPDATE Audience SET " + updateString + " WHERE " + filterString;
                stmt.executeQuery(sqlQuery);
            }
            stmt.close();

        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
        }

    }

    public void updateEvent(String updateString, String filterString) {
        String sqlQuery;
//        if (filterString.length() == 0) {
//            // case no WHERE
//            sqlQuery = "UPDATE Event SET " + updateString;
//
//        } else {
//            // case has WHERE
//            sqlQuery = "UPDATE Event SET " + updateString + " WHERE " + filterString;
//        }
        try {
//            String projectionString = combineProjection(projectionStringList);
            Statement stmt = connection.createStatement();
            if (filterString.length() == 0) {
                // case no WHERE
                sqlQuery = "UPDATE Event SET " + updateString;
                stmt.executeQuery(sqlQuery);

            } else {
                // case has WHERE
                sqlQuery = "UPDATE Event SET " + updateString + " WHERE " + filterString;
                stmt.executeQuery(sqlQuery);
            }
            stmt.close();

        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void updateGame(String updateString, String filterString) {
        String sqlQuery;
//        if (filterString.length() == 0) {
//            // case no WHERE
//            sqlQuery = "UPDATE Game SET " + updateString;
//
//        } else {
//            // case has WHERE
//            sqlQuery = "UPDATE Game SET " + updateString + " WHERE " + filterString;
//        }
//
//        System.out.println(sqlQuery);
        try {
//            String projectionString = combineProjection(projectionStringList);
            Statement stmt = connection.createStatement();
            if (filterString.length() == 0) {
                // case no WHERE
                sqlQuery = "UPDATE Game SET " + updateString;
                stmt.executeQuery(sqlQuery);

            } else {
                // case has WHERE
                sqlQuery = "UPDATE Game SET " + updateString + " WHERE " + filterString;
                stmt.executeQuery(sqlQuery);
            }
            stmt.close();

        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
        }
    }
//TODO olympicEvent1 vs olympicEvent2...
    public void updateOlympicEvent(String updateString, String filterString) {
        String sqlQuery;
//        if (filterString.length() == 0) {
//            // case no WHERE
//            sqlQuery = "UPDATE OlympicEvent SET " + updateString;
//
//        } else {
//            // case has WHERE
//            sqlQuery = "UPDATE OlympicEvent SET " + updateString + " WHERE " + filterString;
//        }
        try {
            Statement stmt = connection.createStatement();
            if (filterString.length() == 0) {
                // case no WHERE
                sqlQuery = "UPDATE OlympicEvent SET " + updateString;
                stmt.executeQuery(sqlQuery);

            } else {
                // case has WHERE
                sqlQuery = "UPDATE OlympicEvent SET " + updateString + " WHERE " + filterString;
                stmt.executeQuery(sqlQuery);
            }
            stmt.close();

        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void updateJudge(String updateString, String filterString) {
        String sqlQuery;
//        if (filterString.length() == 0) {
//            // case no WHERE
//            sqlQuery = "UPDATE OlympicJudges SET " + updateString;
//
//        } else {
//            // case has WHERE
//            sqlQuery = "UPDATE OlympicJudges SET " + updateString + " WHERE " + filterString;
//        }
        try {
//            String projectionString = combineProjection(projectionStringList);
            Statement stmt = connection.createStatement();
            ResultSet rs;
            if (filterString.length() == 0) {
                // case no WHERE
                sqlQuery = "UPDATE OlympicJudges SET " + updateString;
                stmt.executeQuery(sqlQuery);

            } else {
                // case has WHERE
                sqlQuery = "UPDATE OlympicJudges SET " + updateString + " WHERE " + filterString;
                stmt.executeQuery(sqlQuery);
            }
            stmt.close();

        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
        }
    }
    public void updateVolunteers(String updateString, String filterString) {
        String sqlQuery;
        try {
            Statement stmt = connection.createStatement();
            if (filterString.length() == 0) {
                // case no WHERE
                sqlQuery = "UPDATE Volunteers SET " + updateString;
                stmt.executeQuery(sqlQuery);

            } else {
                // case has WHERE
                sqlQuery = "UPDATE Volunteers SET " + updateString + " WHERE " + filterString;
                stmt.executeQuery(sqlQuery);
            }
            stmt.close();
        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void updateMedalReceivedRecord(String updateString, String filterString) {
        String sqlQuery;
        try {
            Statement stmt = connection.createStatement();
            if (filterString.length() == 0) {
                // case no WHERE
                sqlQuery = "UPDATE Receives SET " + updateString;
                stmt.executeQuery(sqlQuery);

            } else {
                // case has WHERE
                sqlQuery = "UPDATE Receives SET " + updateString + " WHERE " + filterString;
                stmt.executeQuery(sqlQuery);
            }
            stmt.close();

        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
        }
    }
    public void updateMedalOfferedRecord(String updateString, String filterString) {
        String sqlQuery;
        try {
            Statement stmt = connection.createStatement();
            if (filterString.length() == 0) {
                // case no WHERE
                sqlQuery = "UPDATE OffersMedal SET " + updateString;
                stmt.executeQuery(sqlQuery);

            } else {
                // case has WHERE
                sqlQuery = "UPDATE OffersMedal SET " + updateString + " WHERE " + filterString;
                stmt.executeQuery(sqlQuery);
            }
            stmt.close();

        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
        }
    }
    public void updateVolunteerRecord(String updateString, String filterString) {
        String sqlQuery;
        try {
            Statement stmt = connection.createStatement();
            if (filterString.length() == 0) {
                // case no WHERE
                sqlQuery = "UPDATE Helps SET " + updateString;
                stmt.executeQuery(sqlQuery);

            } else {
                // case has WHERE
                sqlQuery = "UPDATE Helps SET " + updateString + " WHERE " + filterString;
                stmt.executeQuery(sqlQuery);
            }
            stmt.close();

        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
        }
    }
    public void updateParticipationRecord(String updateString, String filterString) {
        String sqlQuery;
        try {
            Statement stmt = connection.createStatement();
            if (filterString.length() == 0) {
                // case no WHERE
                sqlQuery = "UPDATE Participates SET " + updateString;
                stmt.executeQuery(sqlQuery);

            } else {
                // case has WHERE
                sqlQuery = "UPDATE Participates SET " + updateString + " WHERE " + filterString;
                stmt.executeQuery(sqlQuery);
            }
            stmt.close();

        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
        }
    }
    public void updateWatchesRecord(String updateString, String filterString) {
        String sqlQuery;
        try {
            Statement stmt = connection.createStatement();
            if (filterString.length() == 0) {
                // case no WHERE
                sqlQuery = "UPDATE Watches SET " + updateString;
                stmt.executeQuery(sqlQuery);

            } else {
                // case has WHERE
                sqlQuery = "UPDATE Watches SET " + updateString + " WHERE " + filterString;
                stmt.executeQuery(sqlQuery);
            }
            stmt.close();

        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
        }
    }






    ////////////////////////////////////////////// JOIN ///////////////////////////////////////////////////

    public String combineProjectionWithTable(String[] projectionList, String tableName) {
        String resultString = "";
        for (int i = 0; i < projectionList.length; i++) {
            String currProjection = projectionList[i];
            if (resultString.length() == 0) {
                resultString = tableName + "." + currProjection;
            } else {
                resultString += ", " + tableName + "." + currProjection;
            }
        }
        return resultString;
    }
        public ObservableList<HashMap> joinAthleteMedal(String[] projectionStringList, String filterString) {

            try {
                String tableName = "Athlete";
                String tableName2 = "Receives";
                String[] joining = {"medal_type", "sport_name", "sport_category","year", "season"};

                String projectionString = combineProjectionWithTable(projectionStringList, tableName);
                String projectionStringOfJoin = combineProjectionWithTable(joining, tableName2);
                Statement stmt = connection.createStatement();
                ResultSet rs;
                String sqlQuery;
                if (filterString.length() == 0) {
                    // case no WHERE
                    sqlQuery = "SELECT " + projectionString + ", " + projectionStringOfJoin + " FROM Athlete INNER JOIN Receives ON Receives.participantID=Athlete.participantID";
                    rs = stmt.executeQuery(sqlQuery);

                } else {
                    // case has WHERE
                    sqlQuery = "SELECT " + projectionString + ", " + projectionStringOfJoin + " FROM Athlete INNER JOIN Receives ON Receives.participantID=Athlete.participantID WHERE " + filterString;
                    rs = stmt.executeQuery(sqlQuery);
                }


                ObservableList<HashMap> allData = FXCollections.observableArrayList();

                while (rs.next()) {
                    HashMap<String, String> hm = new LinkedHashMap<>();
                    for (int i = 0; i < projectionStringList.length; i++) {
                        hm.put(projectionStringList[i], rs.getString(projectionStringList[i]));
                    }
                    for (int i = 0; i < joining.length; i++) {
                        hm.put(joining[i], rs.getString(joining[i]));
                    }
                    allData.add(hm);
                }
                rs.close();
                stmt.close();

                return allData;

            } catch (SQLException e) {
                new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
            }

            return null;

        }


    public ObservableList<HashMap> joinVolunteersHelps(String[] projectionStringList, String filterString) {

        try {
            String tableName = "Volunteers";
            String tableName2 = "Helps";
            String[] joining = {"game_type", "sport_name", "sport_category","year", "season"};

            String projectionString = combineProjectionWithTable(projectionStringList, tableName);
            String projectionStringOfJoin = combineProjectionWithTable(joining, tableName2);
            Statement stmt = connection.createStatement();
            ResultSet rs;
            String sqlQuery;
            if (filterString.length() == 0) {
                // case no WHERE
                sqlQuery = "SELECT " + projectionString + ", " + projectionStringOfJoin + " FROM Volunteers INNER JOIN Helps ON Helps.employeeID=Volunteers.employeeID";
                rs = stmt.executeQuery(sqlQuery);

            } else {
                // case has WHERE
                sqlQuery = "SELECT " + projectionString + ", " + projectionStringOfJoin + " FROM Volunteers INNER JOIN Helps ON Helps.employeeID=Volunteers.employeeID WHERE " + "Volunteers." + filterString;
                rs = stmt.executeQuery(sqlQuery);
            }


            ObservableList<HashMap> allData = FXCollections.observableArrayList();

            while (rs.next()) {
                HashMap<String, String> hm = new LinkedHashMap<>();
                for (int i = 0; i < projectionStringList.length; i++) {
                    hm.put(projectionStringList[i], rs.getString(projectionStringList[i]));
                }
                for (int i = 0; i < joining.length; i++) {
                    hm.put(joining[i], rs.getString(joining[i]));
                }
                allData.add(hm);
            }
            rs.close();
            stmt.close();

            return allData;

        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
        }

        return null;
    }

    public ObservableList<HashMap> joinOlympicJudgesJudges(String[] projectionStringList, String filterString) {

        try {
            String tableName = "OlympicJudges";
            String tableName2 = "Judges";
            String[] joining = {"game_type", "sport_name", "sport_category", "year", "season"};

            String projectionString = combineProjectionWithTable(projectionStringList, tableName);
            String projectionStringOfJoin = combineProjectionWithTable(joining, tableName2);
            Statement stmt = connection.createStatement();
            ResultSet rs;
            String sqlQuery;
            if (filterString.length() == 0) {
                // case no WHERE
                sqlQuery = "SELECT " + projectionString + ", " + projectionStringOfJoin + " FROM OlympicJudges INNER JOIN Judges ON Judges.employeeID=OlympicJudges.employeeID";
                rs = stmt.executeQuery(sqlQuery);

            } else {
                // case has WHERE
                sqlQuery = "SELECT " + projectionString + ", " + projectionStringOfJoin + " FROM OlympicJudges INNER JOIN Judges ON Judges.employeeID=OlympicJudges.employeeID WHERE " + "OlympicJudges." + filterString;
                rs = stmt.executeQuery(sqlQuery);
            }


            ObservableList<HashMap> allData = FXCollections.observableArrayList();

            while (rs.next()) {
                HashMap<String, String> hm = new LinkedHashMap<>();
                for (int i = 0; i < projectionStringList.length; i++) {
                    hm.put(projectionStringList[i], rs.getString(projectionStringList[i]));
                }
                for (int i = 0; i < joining.length; i++) {
                    hm.put(joining[i], rs.getString(joining[i]));
                }
                allData.add(hm);
            }
            rs.close();
            stmt.close();

            return allData;

        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
        }

        return null;

    }

    //aggregation count (city) with groupby country

    public ObservableList<HashMap> aggregationGroupByCountries() {

        try {
            Statement stmt = connection.createStatement();
            String[] projectionStringList = {"country","City_Count"};
            ResultSet rs;
            String sqlQuery;
            sqlQuery = "SELECT country, COUNT(DISTINCT city) AS City_Count FROM OlympicEvent GROUP BY country ORDER BY country";

            rs = stmt.executeQuery(sqlQuery);

            ObservableList<HashMap> allData = FXCollections.observableArrayList();

            while (rs.next()) {
                HashMap<String, String> hm = new LinkedHashMap<>();
                for (int i = 0; i < projectionStringList.length; i++) {
                    hm.put(projectionStringList[i], rs.getString(projectionStringList[i]));
                }
                allData.add(hm);
            }

            rs.close();
            stmt.close();

            return allData;

        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
        }

        return null;

    }

    public ObservableList<HashMap> wellPerformingCountriesNestedAggregationGroupBy() {
        try {

            Statement stmt = connection.createStatement();
            ResultSet rs;
            String sqlQuery;

            sqlQuery = "SELECT a.country AS country, AVG(a.medal_count) AS avg_medal_count " +
                    "FROM Athlete a " +
                    "GROUP BY country " +
                    "HAVING min(a.medal_count) > (SELECT avg(b.medal_count) FROM Athlete b)";

            rs = stmt.executeQuery(sqlQuery);

            ObservableList<HashMap> allData = FXCollections.observableArrayList();

            while (rs.next()) {
                HashMap<String, String> hm = new LinkedHashMap<>();

                hm.put("country", rs.getString("country"));
                hm.put("avg_medal_count", rs.getString("avg_medal_count"));
                allData.add(hm);
            }
            rs.close();
            stmt.close();

            return allData;

        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
        }


        return null;
    }


    public ObservableList<HashMap> divisionVolunteersWhoHelpedAllGames() {
        try {

            Statement stmt = connection.createStatement();
            ResultSet rs;
            String sqlQuery;

            sqlQuery = "SELECT Volunteers.employeeID AS employeeID, Volunteers.name AS name FROM Volunteers WHERE NOT EXISTS " +
                    "(SELECT Game.game_type, Game.sport_name, Game.sport_category, Game.year, Game.season FROM Game MINUS " +
                    "(SELECT Helps.game_type, Helps.sport_name, Helps.sport_category, Helps.year, Helps.season FROM Helps " +
                    "WHERE Helps.employeeID = Volunteers.employeeID))";

            rs = stmt.executeQuery(sqlQuery);

            ObservableList<HashMap> allData = FXCollections.observableArrayList();

            while (rs.next()) {
                HashMap<String, String> hm = new LinkedHashMap<>();

                hm.put("employeeID", rs.getString("employeeID"));
                hm.put("name", rs.getString("name"));
                allData.add(hm);
            }
            rs.close();
            stmt.close();

            return allData;

        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
        }


        return null;

    }




    //////////////////////////////////// HAVING ///////////////////////////////////////////

    public ObservableList<HashMap> aggregationWithHaving() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs;
            String sqlQuery = "SELECT min(medal_count), country FROM Athlete GROUP BY country HAVING COUNT(*)>1";
            rs = stmt.executeQuery(sqlQuery);
            String[] projectionStringList = { "min(medal_count)", "country"};

            ObservableList<HashMap> allData = FXCollections.observableArrayList();

            while (rs.next()) {
                HashMap<String, String> hm = new LinkedHashMap<>();
                for (int i = 0; i < projectionStringList.length; i++) {
                    hm.put(projectionStringList[i], rs.getString(projectionStringList[i]));
                }
                allData.add(hm);
            }

            rs.close();
            stmt.close();

            return allData;

        } catch (SQLException e) {
            new ErrorWindow(EXCEPTION_TAG + " " + e.getMessage());
        }
        return null;

    }



    public void close() {
        try {
            if (connection != null) {
                System.out.println("Closing connection to oracle!");
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    //called when operation(insert, remove, lookup etc.) produces an error
    private void rollbackConnection() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }
}
