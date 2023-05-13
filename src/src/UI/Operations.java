package UI;

import Database.DatabaseConnectionHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.PasswordField;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Operations {
    DatabaseConnectionHandler db;

    public Operations(DatabaseConnectionHandler db) {
        this.db = db;
    }

    public void handleInsert(String table, String[] entryList) {
        // length of entryList is guaranteed to match the number of attributes of table
            if (table.equals("Athletes")) {
                db.insertAthlete(entryList);

            } else if (table.equals("Audiences")) {

                db.insertAudience(entryList);

            } else if (table.equals("Events")) {
                db.insertEvent(entryList);

            } else if (table.equals("Games")) {

                db.insertGame(entryList);

            } else if (table.equals("Medals Offered Records")) {

                db.insertMedalsOfferedRecord(entryList);

            } else if (table.equals("Olympic Events")) {

                db.insertOlympicEvent(entryList);

            } else if (table.equals("Olympic Judges")) {

                db.insertJudge(entryList);
            } else if (table.equals("Volunteers")){
                db.insertVolunteer(entryList);
            } else if (table.equals("Volunteer Records")) {
                db.insertVolunteerRecord(entryList);

            } else if (table.equals("Participation Records")) {
                db.insertParticipationRecord(entryList);

            } else if (table.equals("Medals Received Records")) {
                db.insertMedalsReceivedRecord(entryList);

            } else if (table.equals("Watch Records")) {
                db.insertWatchRecord(entryList);

            }
    }

    public void handleRemove(String table, ArrayList<String> filter) {
        try {
            String filterString = translateFilter(filter);

            if (table.equals("Athletes")) {
                db.deleteAthlete(filterString);

            } else if (table.equals("Audiences")) {
                db.deleteAudience(filterString);

            } else if (table.equals("Events")) {
                db.deleteEvent(filterString);

            } else if (table.equals("Games")) {
                db.deleteGame(filterString);

            } else if (table.equals("Medals Offered Records")) {
                db.deleteMedalsOfferedRecord(filterString);

            } else if (table.equals("Olympic Events")) {
                db.deleteOlympicEvent(filterString);

            } else if (table.equals("Olympic Judges")) {
                db.deleteOlympicJudges(filterString);

            } else if (table.equals("Volunteers")){
                db.deleteVolunteers(filterString);
            } else if (table.equals("Volunteer Records")) {
                db.deleteVolunteerRecord(filterString);

            } else if (table.equals("Participation Records")) {
                db.deleteParticipationRecord(filterString);

            } else if (table.equals("Medals Received Records")) {
                db.deleteMedalReceivedRecord(filterString);

            } else if (table.equals("Watch Records")) {
                db.deleteWatchRecord(filterString);

            }
        } catch (NumberFormatException e) {
            new ErrorWindow("Expected integer but got other types");
        }

    }

    public void handleUpdate(String table, String[] entryList, ObservableList<String> attributeList, ArrayList<String> filter) {
        try {
            String updateString = translateUpdates(entryList, attributeList);
            String filterString = translateFilter(filter);
            if (table.equals("Athletes")) {
                db.updateAthlete(updateString, filterString);

            } else if (table.equals("Audiences")) {
                db.updateAudience(updateString, filterString);

            } else if (table.equals("Events")) {
                db.updateEvent(updateString, filterString);

            } else if (table.equals("Games")) {
                db.updateGame(updateString, filterString);

            } else if (table.equals("Medals Offered Records")) {
                db.updateMedalOfferedRecord(updateString, filterString);

            } else if (table.equals("Olympic Events")) {
                db.updateOlympicEvent(updateString, filterString);

            } else if (table.equals("Olympic Judges")) {
                db.updateJudge(updateString, filterString);

            } else if (table.equals("Volunteers")) {
                db.updateVolunteers(updateString, filterString);
            } else if (table.equals("Volunteer Records")) {

                db.updateVolunteerRecord(updateString, filterString);

            } else if (table.equals("Participation Records")) {
                db.updateParticipationRecord(updateString, filterString);

            } else if (table.equals("Medals Received Records")) {
                db.updateMedalReceivedRecord(updateString, filterString);

            } else if (table.equals("Watch Records")) {
                db.updateWatchesRecord(updateString, filterString);

            }
        } catch (NumberFormatException e) {
            new ErrorWindow("Expected integer but got other types");
        }

    }

    public String[] getProjectionList(ObservableList<String> projectionList) {
        return translateProjections(projectionList);

    }

    public ObservableList<HashMap> handleLookUp(String table, ObservableList<String> projectionList, ArrayList<String> filter) {
        try {
            String[] projectionStringList = translateProjections(projectionList);
            String filterString = translateFilter(filter);

            if (table.equals("Athletes")) {
                return db.getAthlete(projectionStringList, filterString);

            } else if (table.equals("Audiences")) {
                return db.getAudience(projectionStringList,filterString);

            } else if (table.equals("Events")) {
                return db.getEvent(projectionStringList,filterString);

            } else if (table.equals("Games")) {
                return db.getGames(projectionStringList,filterString);

            } else if (table.equals("Medals Offered Records")) {
                return db.getMedalsOfferedRecords(projectionStringList,filterString);

            } else if (table.equals("Olympic Events")) {
                return db.getOlympicEvent(projectionStringList,filterString);

            } else if (table.equals("Olympic Judges")) {
                return db.getOlympicJudges(projectionStringList,filterString);

            } else if (table.equals("Volunteers")){
                return db.getVolunteers(projectionStringList,filterString);

            } else if (table.equals("Volunteer Records")) {

                return db.getVolunteerRecords(projectionStringList,filterString);

            } else if (table.equals("Participation Records")) {
                return db.getParticipationRecords(projectionStringList,filterString);

            } else if (table.equals("Medals Received Records")) {
                return db.getMedalsReceivedRecords(projectionStringList,filterString);

            } else if (table.equals("Watch Records")) {
                return db.getWatchRecords(projectionStringList,filterString);

            }
        } catch (NumberFormatException e) {
            new ErrorWindow("Expected integer but got other types");
        }
        return null;

    }

    public ObservableList<HashMap> handleLookUpJoin(String table, ObservableList<String> projectionList, ArrayList<String> filter, String filterBelong) {
        try {
            String[] projectionStringList = translateProjections(projectionList);
            String filterString = translateFilter(filter);
            if (table.equals("Athletes")) {
                String combinedFilter = filterBelong + filterString;
                return db.joinAthleteMedal(projectionStringList, combinedFilter);
            } else if (table.equals("Olympic Judges")) {
                return db.joinOlympicJudgesJudges(projectionStringList, filterString);
            } else {
                return db.joinVolunteersHelps(projectionStringList, filterString);
            }

        } catch (NumberFormatException e) {
            new ErrorWindow("Expected integer but got other types");
        }
        return null;

    }


    public String translateUpdates(String[] changes, ObservableList<String> attributeList) throws NumberFormatException{
        // changes is an array that has the same order and number of elements as attributeList
        String changesString = "";
        for (int i = 0; i < changes.length; i++) {
            if (changes[i] != null) {
                String currChange = translateColumn(attributeList.get(i)) + " = ";

                if (attributeList.get(i).equals("Age") || attributeList.get(i).equals("Medal Count")
                        || attributeList.get(i).equals("Year")) {
                    // case numeric value
                    Integer.parseInt(changes[i]); // tests value for int type
                    currChange += changes[i];
                } else {
                    // case string
                    currChange += "'" + changes[i] + "'";
                }
                if (changesString.length() == 0) {
                    // if no changes are logged yet
                    changesString += currChange;
                } else {
                    changesString += ", " + currChange;
                }

//                if (i != (changes.length - 1)) {
//                    // if i is not the last element index
//                    changesString += ", ";
//                }
            }
        }

        return changesString;
    }

    public String[] translateProjections(ObservableList<String> projectionList) {
        String[] resultStringList = new String[projectionList.size()];
        for (int i = 0; i < projectionList.size(); i++) {
            String currProjection = translateColumn(projectionList.get(i));
            resultStringList[i] = currProjection;

        }

        return resultStringList;
    }

    public String[] translateProjectionsJoin(ObservableList<String> projectionList, String joiningTable) {
        String[] resultStringList;
        resultStringList = new String[projectionList.size() + 5];
        for (int i = 0; i < projectionList.size(); i++) {
            String currProjection = translateColumn(projectionList.get(i));
            resultStringList[i] = currProjection;
        }
        resultStringList[projectionList.size()+1] = "sport_name";
        resultStringList[projectionList.size()+2] = "sport_category";
        resultStringList[projectionList.size()+3] = "year";
        resultStringList[projectionList.size()+4] = "season";
        if (joiningTable == "Receives") {
            resultStringList[projectionList.size()] = "medal_type";
        } else {
            resultStringList[projectionList.size()] = "game_type";
        }

        return resultStringList;
    }
    public String[] translateProjectionsAggregation(ObservableList<String> projectionList, String cc) {
        String[] resultStringList;
        resultStringList = new String[projectionList.size() + 1];
        for (int i = 0; i < projectionList.size(); i++) {
            String currProjection = translateColumn(projectionList.get(i));
            resultStringList[i] = currProjection;
        }
        resultStringList[projectionList.size()] = cc;

        return resultStringList;
    }

    public String translateFilter(ArrayList<String> filter) throws NumberFormatException{
        // filter[0] = column
        // filter[1] = operator
        // filter[2] = value
        // Note: Strings need to be wrapped with ' '
        String columnString;
        String operatorString;
        String valueString;
        boolean isNumeric = false;

        if (filter.size() == 0) {
            // case no filters
            return "";
        }

        columnString = translateColumn(filter.get(0));

        //    ObservableList<String> numericOperators = FXCollections.observableArrayList("Equal", "Less Than",
        //            "Greater Than", "Not Equal");
        //
        //    ObservableList<String> stringOperators = FXCollections.observableArrayList("Equal To", "Not Equal To", "Contains");

        switch (filter.get(1)) {
            case "Equal":
                operatorString = "=";
                isNumeric = true;
                break;
            case "Less Than":
                operatorString = "<";
                isNumeric = true;
                break;
            case "Greater Than":
                operatorString = ">";
                isNumeric = true;
                break;
            case "Not Equal":
                operatorString = "<>";
                isNumeric = true;
                break;
            case "Equal To":
                operatorString = "=";
                break;
            case "Not Equal To":
                operatorString = "<>";
                break;
            default:
                // case "Contains"
                operatorString = "LIKE";
        }

        if (isNumeric) {
            Integer.parseInt(filter.get(2)); // this line should throw NumberFormatException if value is not integer
            valueString = filter.get(2);

        } else {
            if (operatorString.equals("LIKE")) {
                valueString = "'%" + filter.get(2) + "%'";
            } else {
                valueString = "'" + filter.get(2) + "'";
            }
        }

        return columnString + " " + operatorString + " " + valueString;

    }

    public String translateColumn(String column) {
        // returns the SQL version of the column

        switch (column) {
            case "Age": return "age";
            case "Participant ID": return "participantID";
            case "Name": return "name";
            case "Country": return "country";
            case "Medal Count": return "medal_count";
            case "Birthday": return "DOB";
            case "Gender": return "gender";
            case "Sport Name": return "sport_name";
            case "Sport Category": return "sport_category";
            case "Year": return "year";
            case "Season": return "season";
            case "Date": return "date_";
            case "Time": return "time_";
            case "Building Name": return "building_name";
            case "Ticket ID": return "ticketID";
            case "Medal Type": return "medal_type";
            case "Continent": return "continent";
            case "City": return "city";
            case "Employee ID": return "employeeID";
            case "Game Type": return "game_type";
            case "Task": return "task";
            default: return "";
        }


    }
}
