package UI;

import Database.DatabaseConnectionHandler;
import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class UI extends Application implements EventHandler<ActionEvent> {

    public static final double APPLICATION_WIDTH = 1000;
    public static final double APPLICATION_HEIGHT = 600;
    Stage stage;
    DatabaseConnectionHandler db;
    Operations operations;

    // FXMLLoader loader = new FXMLLoader();

    Scene mainMenuScene;
    Scene guestScene;
    Scene staffLoginScene;
    Scene privateScene;
    Scene privateLookUpScene;
    Scene privateEditScene;
    Scene addDataScene;
    Scene removeDataScene;
    Scene updateDataScene;
    Scene removeFilterScene;
    Scene lookupFilterScene;
    Scene updateFilterScene;
    Scene displayDataScene;

    Button useAsGuest;
    Button staffLogin;
    Button returnToMainMenu;
    Button loginButton;
    Button submitButton;
    Button lookUpDatabaseButton;
    Button editDatabaseButton;
    Button backToLoginButton;
    Button backToPrivateExplorerButton;
    Button addActionButton;
    Button updateActionButton;
    Button removeActionButton;
    Button logChangesButton;
    Button addEntryButton;
    Button updateEntryButton;
    Button backtoPrivateEditButton;
    Button submitRemoveButton;
    Button submitFilterRemoveDataButton;
    Button submitFilterLookupDataButton;
    Button submitFilterUpdateDataButton;
    Button skipFilterRemoveDataButton;
    Button skipFilterLookupDataButton;
    Button skipFilterUpdateDataButton;
    Button agWithHavingButton;
    Button findVolunteersWhoHelpedEveryGame;
    Button findWellPerformingCountries;
    Button agOlympicEvent;

    CheckBox cbAthlete = new CheckBox("show info on medals\nreceived by athletes");;
    CheckBox cbHelps = new CheckBox("show more info");;
    CheckBox cbJudges = new CheckBox("show more info");;

    TextField valueTF;
    TextField attributeTF;
    TextField usernameTF;
    PasswordField passwordTF;

    ComboBox<String> editEntityMenu = new ComboBox<>();
    ComboBox<String> attributeMenu = new ComboBox<String>();
    ListView<String> myListView = new ListView<String>();
    ListView<String> currentChanges = new ListView<>();
    ComboBox<String> operators = new ComboBox<>();
    ComboBox<String> removeEntityMenu;
    ComboBox<String> lookUpEntityMenu;
    ComboBox<String> privateEditAttributeMenu;

    //Array<String> changesList = new ArrayList<String>();
    String[] changesList;

    ObservableList<String> numericOperators = FXCollections.observableArrayList("Equal", "Less Than",
            "Greater Than", "Not Equal");

    ObservableList<String> stringOperators = FXCollections.observableArrayList("Equal To", "Not Equal To", "Contains");

    ObservableList<String> publicList = FXCollections.observableArrayList("Athletes","Events",
            "Games", "Medals Offered Records","Olympic Events","Participation Records","Medals Received Records");

    ObservableList<String> privateList = FXCollections.observableArrayList("Athletes", "Audiences",
            "Events", "Games", "Medals Offered Records", "Olympic Events", "Olympic Judges", "Volunteers", "Volunteer Records",
            "Participation Records", "Medals Received Records", "Watch Records");


    ObservableList<String> medalsOfferedAttributes = FXCollections.observableArrayList("Medal Type","Sport Name",
            "Sport Category", "Year", "Season");


    ObservableList<String> volunteerRecordAttributes = FXCollections.observableArrayList("Employee ID","Game Type",
            "Sport Name", "Sport Category", "Year", "Season", "Task");

    ObservableList<String> participationRecordAttributes = FXCollections.observableArrayList("Participant ID",
            "Game Type", "Sport Name", "Sport Category", "Year", "Season");

    ObservableList<String> medalsReceivedAttributes = FXCollections.observableArrayList("Participant ID",
            "Medal Type", "Sport Name", "Sport Category", "Year", "Season");

    //    ObservableList<String> athleteAttributes = FXCollections.observableArrayList("Participant ID",
    //            "Name", "Country", "Medal Count", "Birthday", "Gender");

    ObservableList<String> watchRecordsAttributes = FXCollections.observableArrayList("Game Type","Sport Name",
            "Sport Category", "Year", "Season", "Ticket ID");

    ObservableList<String> athleteAttributes = FXCollections.observableArrayList("Participant ID",
            "Name", "Country", "Medal Count", "Birthday", "Gender");

    ObservableList<String> eventAttributes = FXCollections.observableArrayList("Sport Name",
            "Sport Category", "Year", "Season");

    ObservableList<String> gameAttributes = FXCollections.observableArrayList("Game Type","Sport Name",
            "Sport Category", "Year", "Season", "Date", "Time", "Building Name");

    ObservableList<String> audienceAttributes = FXCollections.observableArrayList("Ticket ID","Name");

    ObservableList<String> medalAttributes = FXCollections.observableArrayList("Medal Type", "Sport Name",
            "Sport Category", "Year", "Season");

    ObservableList<String> olympicEventAttributes = FXCollections.observableArrayList("Country",
            "Year", "Season", "City");

    ObservableList<String> judgeAttributes = FXCollections.observableArrayList("Employee ID","Name");

    ObservableList<String> volunteerAttributes = FXCollections.observableArrayList("Employee ID","Name");

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainMenu.fxml")));
//        stage = primaryStage;
//        Scene scene = new Scene(root, APPLICATION_WIDTH, APPLICATION_HEIGHT);
//        stage.setScene(scene);
//        stage.setTitle("Olympic Explorer");
//        stage.setResizable(false);
//        stage.show();
//
//    }

        // start database handler here
        db = new DatabaseConnectionHandler();
        boolean connected = db.login();
        db.databaseSetup();
        operations = new Operations(db);
        if (connected) {
            stage = primaryStage;
            setUpMainMenuScene();
            stage.setScene(mainMenuScene);
            stage.show();
        } else {
            System.out.println("not connected");
        }

//        db = new DatabaseConnectionHandler();
//        operations = new Operations(db);
//
//
//        stage = primaryStage;
//        setUpMainMenuScene();
//        stage.setScene(mainMenuScene);
//        stage.show();
    }


    @Override
    public void stop(){
        db.close();
        db = null;
        // close database handler here
    }

    public void setUpMainMenuScene() {
        returnToMainMenu = MakeComponent.makeButton(
                "Main Menu", 150, 50, 0, 0, new Font(20));
        returnToMainMenu.setOnAction(this);

        stage.setTitle("Olympic Explorer");

        useAsGuest = MakeComponent.makeButton("For Public", 200, 100,
                (APPLICATION_WIDTH - (APPLICATION_WIDTH / 3.5)),(APPLICATION_HEIGHT / 2),new Font(20) );
        useAsGuest.setOnAction(this);


        staffLogin = MakeComponent.makeButton("For Olympic Staff", 200, 100,
                (APPLICATION_WIDTH / 12), (APPLICATION_HEIGHT / 2), new Font(20) );
        staffLogin.setOnAction(this);


        Label mainMenuTitle = MakeComponent.makeLabel("Olympic Explorer", 600, 100,
                APPLICATION_WIDTH / 3.8,
                APPLICATION_HEIGHT / 15, Font.font(null, FontWeight.BOLD,60));


        Image hamster = new Image(Objects.requireNonNull(getClass().getResourceAsStream("hamster.jpg")));

        ImageView hamsterImageView = MakeComponent.makeImage(hamster, 350, 150);

        Image bigOlympics = new Image(Objects.requireNonNull(getClass().getResourceAsStream("olympics.png")));

        ImageView olympicsImageView = MakeComponent.makeImage(bigOlympics, 0, 0);
        olympicsImageView.setFitWidth(APPLICATION_WIDTH);
        olympicsImageView.setFitHeight(APPLICATION_HEIGHT);
        olympicsImageView.setOpacity(0.2);

        Pane layout = new Pane();
        layout.getChildren().addAll(olympicsImageView,useAsGuest,staffLogin,mainMenuTitle,hamsterImageView);

        mainMenuScene = new Scene(layout, APPLICATION_WIDTH, APPLICATION_HEIGHT);
    }

    public void setUpGuestScene() {
        stage.setTitle("Public Olympic Explorer");
        lookUpEntityMenu = new ComboBox<String>();


        Label guestSceneTitle = MakeComponent.makeLabel("Public Olympic Explorer",800,100,
                APPLICATION_WIDTH / 6.5,
                APPLICATION_HEIGHT / 9,Font.font(null,FontWeight.BOLD, 60));

        Label questionLabel = MakeComponent.makeLabel("What are you looking for?", 400, 50,
                APPLICATION_WIDTH / 3, APPLICATION_HEIGHT / 4, new Font(30));

        Label hintLabel = MakeComponent.makeLabel("Hold Ctrl to \nselect multiple fields.", 300, 100,
                APPLICATION_WIDTH / 1.5, APPLICATION_HEIGHT / 1.5, new Font(20));


        // ComboBox<String> publicExplorerMenu = new ComboBox<String>();

        lookUpEntityMenu.setPrefSize(250,50);
        lookUpEntityMenu.setLayoutX(APPLICATION_WIDTH / 2.7);
        lookUpEntityMenu.setLayoutY(APPLICATION_HEIGHT / 2.5);
        lookUpEntityMenu.setValue("Athletes");
        lookUpEntityMenu.setItems(publicList);
        lookUpEntityMenu.setOnAction(this);

        myListView = new ListView<String>();
        myListView.setPrefSize(250,150);
        myListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        myListView.setLayoutX(APPLICATION_WIDTH / 2.7);
        myListView.setLayoutY(APPLICATION_HEIGHT / 1.8);
        //myListView.getItems().addAll(athleteAttributes);
        //myListView.getSelectionModel().getSelectedItems();
        myListView.setItems(athleteAttributes);

        cbAthlete = new CheckBox("show info on medals\nreceived by athletes");
        cbAthlete.setLayoutX(APPLICATION_WIDTH / 4.5);
        cbAthlete.setLayoutY(APPLICATION_HEIGHT / 1.5);
        cbAthlete.setOnAction(this);
        cbAthlete.setVisible(true);

        cbHelps = new CheckBox("show more info");

        cbJudges = new CheckBox("show more info");

        agOlympicEvent = MakeComponent.makeButton("Find the count of cites \ngroup by countries \n(aggregation with group by)", 200,100, APPLICATION_WIDTH / 4.5,APPLICATION_WIDTH / 2,new Font(14));
        agOlympicEvent.setOnAction(this);
        agOlympicEvent.setVisible(false);


        submitButton = MakeComponent.makeButton("Submit", 100, 50,
                APPLICATION_WIDTH / 2.25, APPLICATION_HEIGHT / 1.2, new Font(20));
        submitButton.setOnAction(this);



        agWithHavingButton = MakeComponent.makeButton("Find the lowest Athlete Medal Count\n in Countries that have \nmore than 1 athlete \n(aggregation with having)", 250, 100,
                APPLICATION_WIDTH / 50, APPLICATION_HEIGHT/3 , new Font(14));
        agWithHavingButton.setOnAction(this);


        findVolunteersWhoHelpedEveryGame = MakeComponent.makeButton("Find the Volunteers\nthat helped\nin every Game \n(nested aggregation with group by)",
                250, 150, 50, APPLICATION_HEIGHT / 3, new Font(15));
        findVolunteersWhoHelpedEveryGame.setOnAction(this);
        findVolunteersWhoHelpedEveryGame.setVisible(false);


        findWellPerformingCountries = MakeComponent.makeButton("Find Countries where every athlete \nhas an above average medal count \n(nested aggregation with group by)",
                275, 100, APPLICATION_WIDTH/1.5, APPLICATION_HEIGHT/ 3, new Font(12));
        findWellPerformingCountries.setOnAction(this);
        findWellPerformingCountries.setVisible(true);



        // ComboBox<String> attributeMenu = new ComboBox<String>();


        Pane layout = new Pane();
        layout.getChildren().addAll(returnToMainMenu,guestSceneTitle,
                lookUpEntityMenu, myListView, questionLabel, submitButton, hintLabel, agOlympicEvent,cbAthlete,
                agWithHavingButton, findVolunteersWhoHelpedEveryGame,findWellPerformingCountries);
        guestScene = new Scene(layout, APPLICATION_WIDTH, APPLICATION_HEIGHT);
    }
    public void setUpStaffLoginScene() {
        stage.setTitle("Staff Login");

        Label staffLoginLabel = MakeComponent.makeLabel("Staff Authentication", 800, 100,
                APPLICATION_WIDTH / 4.2,
                APPLICATION_HEIGHT / 5, Font.font(null,FontWeight.BOLD,60));

        Label loginDescription = MakeComponent.makeLabel("Login to continue to the Private Olympic Explorer",
                450, 100,
                APPLICATION_WIDTH / 3.35, APPLICATION_HEIGHT / 3.7, new Font(20));

        Image olympicsLogo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("olympics.png")));
        ImageView olympicsImageView = MakeComponent.makeImage(olympicsLogo,APPLICATION_WIDTH / 2.4, 20);
        olympicsImageView.setFitWidth(175);
        olympicsImageView.setFitHeight(100);

        Image lockedLogo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("locked.png")));
        ImageView lockedImageView = MakeComponent.makeImage(lockedLogo,
                APPLICATION_WIDTH / 1.6, APPLICATION_HEIGHT / 2.45);
        lockedImageView.setFitWidth(300);
        lockedImageView.setFitHeight(300);

        Rectangle rectangle = new Rectangle(APPLICATION_WIDTH / 6, APPLICATION_HEIGHT/2.17, 400, 150);
        rectangle.setFill(Color.LAVENDER);
        // rectangle.setStroke(Color.LIGHTBLUE);

        usernameTF = new TextField();
        usernameTF.setPrefWidth(363);
        usernameTF.setLayoutX(APPLICATION_WIDTH / 5.5);
        usernameTF.setLayoutY(APPLICATION_HEIGHT/1.9);
        usernameTF.setFont(new Font(15));
        usernameTF.setPromptText("Type 'grilled' here");

        passwordTF = new PasswordField();
        passwordTF.setPrefWidth(363);
        passwordTF.setLayoutX(APPLICATION_WIDTH / 5.5);
        passwordTF.setLayoutY(APPLICATION_HEIGHT/1.55);
        passwordTF.setFont(new Font(15));
        passwordTF.setPromptText("Type 'cheese' here");

        Label usernameLabel = MakeComponent.makeLabel("Username", 150, 30,
                APPLICATION_WIDTH / 5.5,APPLICATION_HEIGHT/2.15, new Font(20));

        Label passwordLabel = MakeComponent.makeLabel("Password", 150, 30,
                APPLICATION_WIDTH / 5.5, APPLICATION_HEIGHT / 1.7,new Font(20));

        loginButton = MakeComponent.makeButton("Login", 120, 60,
                APPLICATION_WIDTH / 5.5,APPLICATION_HEIGHT/1.35, new Font(20));
        loginButton.setOnAction(this);


        Pane layout = new Pane();
        layout.getChildren().addAll(returnToMainMenu,staffLoginLabel,loginDescription, olympicsImageView,
                lockedImageView, rectangle, usernameTF,passwordTF,usernameLabel,passwordLabel, loginButton);
        staffLoginScene = new Scene(layout, APPLICATION_WIDTH, APPLICATION_HEIGHT);
    }

    public void setUpPrivateExplorerScene() {
        stage.setTitle("Private Olympic Explorer");

        Label privateSceneTitle = MakeComponent.makeLabel("Private Olympic Explorer",800,100,
                APPLICATION_WIDTH / 6.5,
                APPLICATION_HEIGHT / 9,Font.font(null,FontWeight.BOLD,60));

        Image cute = new Image(Objects.requireNonNull(getClass().getResourceAsStream("blanket.png")));
        ImageView cuteImageView = MakeComponent.makeImage(
                cute, APPLICATION_WIDTH / 2.7,APPLICATION_HEIGHT / 3);
        cuteImageView.setFitWidth(250);
        cuteImageView.setFitHeight(300);

        lookUpDatabaseButton = MakeComponent.makeButton("Look up \nDatabase", 200, 100,
                APPLICATION_WIDTH / 1.45, APPLICATION_HEIGHT / 2, new Font(25));
        lookUpDatabaseButton.setTextAlignment(TextAlignment.CENTER);
        lookUpDatabaseButton.setOnAction(this);

        editDatabaseButton = MakeComponent.makeButton("Edit \nDatabase", 200, 100,
                APPLICATION_WIDTH / 10, APPLICATION_HEIGHT / 2, new Font(25));
        editDatabaseButton.setTextAlignment(TextAlignment.CENTER);
        editDatabaseButton.setOnAction(this);

        backToLoginButton = MakeComponent.makeButton(
                "Logout", 150, 50, 0, 0, new Font(20));
        backToLoginButton.setOnAction(this);



        Pane layout = new Pane();
        layout.getChildren().addAll(privateSceneTitle, lookUpDatabaseButton,
                editDatabaseButton,cuteImageView, backToLoginButton);
        privateScene = new Scene(layout, APPLICATION_WIDTH, APPLICATION_HEIGHT);

    }

    public void setUpPrivateLookUpScene() {
        // stage.setTitle("Private Olympic Explorer");
        lookUpEntityMenu = new ComboBox<String>();
        myListView = new ListView<String>();



        Label guestSceneTitle = MakeComponent.makeLabel("Private Database Lookup",800,100,
                APPLICATION_WIDTH / 6.5,
                APPLICATION_HEIGHT / 9,Font.font(null,FontWeight.BOLD, 60));

        Label questionLabel = MakeComponent.makeLabel("What are you looking for?", 400, 50,
                APPLICATION_WIDTH / 3, APPLICATION_HEIGHT / 4, new Font(30));

        Label hintLabel = MakeComponent.makeLabel("Hold Ctrl to \nselect multiple fields.", 300, 100,
                APPLICATION_WIDTH / 1.5, APPLICATION_HEIGHT / 1.5, new Font(20));


        // ComboBox<String> publicExplorerMenu = new ComboBox<String>();

        lookUpEntityMenu.setPrefSize(250,50);
        lookUpEntityMenu.setLayoutX(APPLICATION_WIDTH / 2.7);
        lookUpEntityMenu.setLayoutY(APPLICATION_HEIGHT / 2.5);
        lookUpEntityMenu.setValue("Athletes");
        lookUpEntityMenu.setItems(privateList);
        lookUpEntityMenu.setOnAction(this);

        myListView.setPrefSize(250,150);
        myListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        myListView.setLayoutX(APPLICATION_WIDTH / 2.7);
        myListView.setLayoutY(APPLICATION_HEIGHT / 1.8);
        myListView.setItems(athleteAttributes);


        cbAthlete = new CheckBox("show info on medals\nreceived by athletes");
        cbAthlete.setLayoutX(APPLICATION_WIDTH / 4.5);
        cbAthlete.setLayoutY(APPLICATION_HEIGHT / 1.5);
        cbAthlete.setOnAction(this);
        //cbAthlete.setSelected(false);
        cbAthlete.setVisible(true);

        cbHelps = new CheckBox("show more info");
        cbHelps.setLayoutX(APPLICATION_WIDTH / 4.5);
        cbHelps.setLayoutY(APPLICATION_HEIGHT / 1.5);
        cbHelps.setOnAction(this);
        cbHelps.setVisible(false);

        cbJudges = new CheckBox("show more info");
        cbJudges.setLayoutX(APPLICATION_WIDTH / 4.5);
        cbJudges.setLayoutY(APPLICATION_HEIGHT / 1.5);
        cbJudges.setOnAction(this);
        cbJudges.setVisible(false);

        agOlympicEvent = MakeComponent.makeButton("Find the count of cites \ngroup by countries \n(aggregation with group by)", 200,100, APPLICATION_WIDTH / 4.5,APPLICATION_WIDTH / 2,new Font(14));
        agOlympicEvent.setOnAction(this);
        agOlympicEvent.setVisible(false);



        submitButton = MakeComponent.makeButton("Submit", 100, 50,
                APPLICATION_WIDTH / 2.25, APPLICATION_HEIGHT / 1.2, new Font(20));
        submitButton.setOnAction(this);

        backToPrivateExplorerButton = MakeComponent.makeButton(
                "Back", 150, 50, 175, 0, new Font(20));
        backToPrivateExplorerButton.setOnAction(this);

        agWithHavingButton = MakeComponent.makeButton("Find the lowest Athlete Medal Count\n in Countries that have \nmore than 1 athlete\n(aggregation with having)", 250, 100,
                APPLICATION_WIDTH / 50, APPLICATION_HEIGHT/3 , new Font(14));
        agWithHavingButton.setOnAction(this);


        findVolunteersWhoHelpedEveryGame = MakeComponent.makeButton("Find the Volunteers\nthat helped\nin every Game\n(Division)",
                250, 150, 50, APPLICATION_HEIGHT / 3, new Font(15));
        findVolunteersWhoHelpedEveryGame.setOnAction(this);
        findVolunteersWhoHelpedEveryGame.setVisible(false);


        findWellPerformingCountries = MakeComponent.makeButton("Find Countries where every athlete \nhas an above average medal count \n(Nested Aggregation with group by)",
                275, 100, APPLICATION_WIDTH/1.5, APPLICATION_HEIGHT/ 3, new Font(12));
        findWellPerformingCountries.setOnAction(this);
        findWellPerformingCountries.setVisible(true);







        Pane layout = new Pane();
        layout.getChildren().addAll(backToLoginButton,backToPrivateExplorerButton,
                guestSceneTitle, lookUpEntityMenu, myListView, questionLabel, submitButton, hintLabel, cbAthlete,
                cbHelps, cbJudges, agOlympicEvent, agWithHavingButton,
                findVolunteersWhoHelpedEveryGame, findWellPerformingCountries);
        privateLookUpScene = new Scene(layout, APPLICATION_WIDTH, APPLICATION_HEIGHT);

    }

    public void setUpPrivateEditScene() {
        backToPrivateExplorerButton = MakeComponent.makeButton(
                "Back", 150, 50, 175, 0, new Font(20));
        backToPrivateExplorerButton.setOnAction(this);

        Label guestSceneTitle = MakeComponent.makeLabel("Private Database Editor",800,100,
                APPLICATION_WIDTH / 6.5,
                APPLICATION_HEIGHT / 9,Font.font(null,FontWeight.BOLD, 60));

        Label questionLabel = MakeComponent.makeLabel("What do you want to do?", 400, 50,
                APPLICATION_WIDTH / 3, APPLICATION_HEIGHT / 4, new Font(30));

        addActionButton = MakeComponent.makeButton("Add Data", 300, 100,
                APPLICATION_WIDTH / 2.8, APPLICATION_HEIGHT / 3, new Font(30));
        addActionButton.setOnAction(this);

        removeActionButton = MakeComponent.makeButton("Remove Existing \nData", 300, 100,
                APPLICATION_WIDTH / 2.8, APPLICATION_HEIGHT / 1.8, new Font(30));
        removeActionButton.setTextAlignment(TextAlignment.CENTER);
        removeActionButton.setOnAction(this);

        updateActionButton = MakeComponent.makeButton("Update Existing \nData", 300, 100,
                APPLICATION_WIDTH / 2.8, APPLICATION_HEIGHT / 1.3, new Font(30));
        updateActionButton.setTextAlignment(TextAlignment.CENTER);
        updateActionButton.setOnAction(this);

        Pane layout = new Pane();
        layout.getChildren().addAll(guestSceneTitle,questionLabel,backToPrivateExplorerButton,
                backToLoginButton,addActionButton,removeActionButton,updateActionButton);
        privateEditScene = new Scene(layout, APPLICATION_WIDTH, APPLICATION_HEIGHT);


    }

    public void setUpAddDataScene() {

        Label addDataTitle = MakeComponent.makeLabel("Add Data",800,100,
                APPLICATION_WIDTH / 2.8,
                APPLICATION_HEIGHT / 9,Font.font(null,FontWeight.BOLD, 60));

        editEntityMenu = new ComboBox<String>();
        privateEditAttributeMenu = new ComboBox<String>();
        currentChanges = new ListView<String>();
        attributeTF = new TextField();
        changesList = new String[athleteAttributes.size()];

        logChangesButton = MakeComponent.makeButton("Log Change", 100, 25, 50,
                APPLICATION_HEIGHT / 1.3, new Font(15));
        logChangesButton.setOnAction(this);

        addEntryButton = MakeComponent.makeButton("Add Entry", 150, 100,
                APPLICATION_WIDTH/ 1.5 , APPLICATION_HEIGHT / 1.3, new Font(25));
        addEntryButton.setOnAction(this);

        backtoPrivateEditButton = MakeComponent.makeButton(
                "Back", 150, 50, 175, 0, new Font(20));
        backtoPrivateEditButton.setOnAction(this);


        attributeTF.setPrefWidth(250);
        attributeTF.setLayoutX(50);
        attributeTF.setLayoutY(APPLICATION_HEIGHT/1.5);
        attributeTF.setFont(new Font(15));

        editEntityMenu.setPrefSize(250,50);
        editEntityMenu.setLayoutX(50);
        editEntityMenu.setLayoutY(APPLICATION_HEIGHT / 2.5);
        editEntityMenu.setValue("Athletes");
        editEntityMenu.setItems(privateList);
        editEntityMenu.setOnAction(this);


        privateEditAttributeMenu.setPrefSize(250,50);
        privateEditAttributeMenu.setLayoutX(50);
        privateEditAttributeMenu.setLayoutY(APPLICATION_HEIGHT / 1.8);
        privateEditAttributeMenu.setValue(athleteAttributes.get(0));
        privateEditAttributeMenu.setItems(athleteAttributes);

        currentChanges.setItems(athleteAttributes);
        currentChanges.setPrefSize(400,200);
        currentChanges.setLayoutX(APPLICATION_WIDTH/ 2);
        currentChanges.setLayoutY(APPLICATION_HEIGHT / 2.5);

        Pane layout = new Pane();
        layout.getChildren().addAll(addDataTitle, editEntityMenu, privateEditAttributeMenu, currentChanges,
                backToLoginButton, backtoPrivateEditButton, attributeTF, logChangesButton, addEntryButton);
        addDataScene = new Scene(layout, APPLICATION_WIDTH, APPLICATION_HEIGHT);




    }

    public void setUpRemoveDataScene() {

        removeEntityMenu = new ComboBox<String>();

        Label removeDataTitle = MakeComponent.makeLabel("Remove An Existing Entry",800,100,
                APPLICATION_WIDTH / 6,
                APPLICATION_HEIGHT / 9,Font.font(null,FontWeight.BOLD, 60));

        Label descriptionLabel = MakeComponent.makeLabel("Where would you like to remove from?", 400,
                50, APPLICATION_WIDTH / 3, APPLICATION_HEIGHT / 4, new Font(20));


        backtoPrivateEditButton = MakeComponent.makeButton(
                "Back", 150, 50, 175, 0, new Font(20));
        backtoPrivateEditButton.setOnAction(this);

        submitRemoveButton = MakeComponent.makeButton("Submit", 250, 100,
                APPLICATION_WIDTH / 2.7, APPLICATION_HEIGHT / 1.5, new Font(25));

        submitRemoveButton.setOnAction(this);

        removeEntityMenu.setPrefSize(250,50);
        removeEntityMenu.setLayoutX(APPLICATION_WIDTH / 2.7);
        removeEntityMenu.setLayoutY(APPLICATION_HEIGHT / 2.5);
        removeEntityMenu.setValue("Athletes");
        removeEntityMenu.setItems(privateList);
        //removeEntityMenu.setOnAction(this);

        Pane layout = new Pane();
        layout.getChildren().addAll(removeDataTitle,descriptionLabel,backtoPrivateEditButton,backToLoginButton,removeEntityMenu, submitRemoveButton);
        removeDataScene = new Scene(layout, APPLICATION_WIDTH, APPLICATION_HEIGHT);


    }

    public void setUpUpdateDataScene() {
        Label addDataTitle = MakeComponent.makeLabel("Update Existing Entries",800,100,
                APPLICATION_WIDTH / 6,
                APPLICATION_HEIGHT / 9,Font.font(null,FontWeight.BOLD, 60));

        editEntityMenu = new ComboBox<String>();
        privateEditAttributeMenu = new ComboBox<String>();
        currentChanges = new ListView<String>();
        attributeTF = new TextField();
        changesList = new String[athleteAttributes.size()];

        logChangesButton = MakeComponent.makeButton("Log Change", 100, 25, 50, APPLICATION_HEIGHT / 1.3, new Font(15));
        logChangesButton.setOnAction(this);

        updateEntryButton = MakeComponent.makeButton("Next", 150, 100,
                APPLICATION_WIDTH/ 1.5 , APPLICATION_HEIGHT / 1.3, new Font(25));
        updateEntryButton.setOnAction(this);

        backtoPrivateEditButton = MakeComponent.makeButton(
                "Back", 150, 50, 175, 0, new Font(20));
        backtoPrivateEditButton.setOnAction(this);


        attributeTF.setPrefWidth(250);
        attributeTF.setLayoutX(50);
        attributeTF.setLayoutY(APPLICATION_HEIGHT/1.5);
        attributeTF.setFont(new Font(15));

        editEntityMenu.setPrefSize(250,50);
        editEntityMenu.setLayoutX(50);
        editEntityMenu.setLayoutY(APPLICATION_HEIGHT / 2.5);
        editEntityMenu.setValue("Athletes");
        editEntityMenu.setItems(privateList);
        editEntityMenu.setOnAction(this);


        privateEditAttributeMenu.setPrefSize(250,50);
        privateEditAttributeMenu.setLayoutX(50);
        privateEditAttributeMenu.setLayoutY(APPLICATION_HEIGHT / 1.8);
        privateEditAttributeMenu.setValue(athleteAttributes.get(0));
        privateEditAttributeMenu.setItems(athleteAttributes);

        currentChanges.setItems(athleteAttributes);
        currentChanges.setPrefSize(400,200);
        currentChanges.setLayoutX(APPLICATION_WIDTH/ 2);
        currentChanges.setLayoutY(APPLICATION_HEIGHT / 2.5);

        Pane layout = new Pane();
        layout.getChildren().addAll(addDataTitle, editEntityMenu, privateEditAttributeMenu, currentChanges,
                backToLoginButton, backtoPrivateEditButton, attributeTF, logChangesButton, updateEntryButton);
        updateDataScene = new Scene(layout, APPLICATION_WIDTH, APPLICATION_HEIGHT);

    }

    public void setUpLookUpFilterScene(String table) {
        attributeMenu = new ComboBox<String>();
        operators = new ComboBox<String>();
        valueTF = new TextField();

        Label filterTitle = MakeComponent.makeLabel("Filter for Lookup",800,100,
                APPLICATION_WIDTH / 4,
                APPLICATION_HEIGHT / 9,Font.font(null,FontWeight.BOLD, 60));

        submitFilterLookupDataButton = MakeComponent.makeButton("Lookup Data", 250, 100,
                APPLICATION_WIDTH /2.75, APPLICATION_HEIGHT / 1.3, new Font(25));
        submitFilterLookupDataButton.setOnAction(this);

        skipFilterLookupDataButton = MakeComponent.makeButton("No Filter", 250, 100,
                APPLICATION_WIDTH - 250, APPLICATION_HEIGHT - 100, new Font(25));
        skipFilterLookupDataButton.setOnAction(this);

        valueTF.setPrefWidth(250);
        valueTF.setLayoutX(APPLICATION_WIDTH / 2.75);
        valueTF.setLayoutY(APPLICATION_HEIGHT/1.45);
        valueTF.setFont(new Font(15));

        attributeMenu.setPrefSize(250,50);
        attributeMenu.setLayoutX(APPLICATION_WIDTH / 2.75);
        attributeMenu.setLayoutY(APPLICATION_HEIGHT / 2.3);
        attributeMenu.setOnAction(this);

        operators.setPrefSize(250, 50);
        operators.setLayoutX(APPLICATION_WIDTH / 2.75);
        operators.setLayoutY(APPLICATION_HEIGHT / 1.8);

        changeAttributes(table);

        changeOperator();



        Pane layout = new Pane();
        layout.getChildren().addAll(attributeMenu,operators,valueTF,filterTitle,returnToMainMenu, submitFilterLookupDataButton, skipFilterLookupDataButton);
        lookupFilterScene = new Scene(layout, APPLICATION_WIDTH, APPLICATION_HEIGHT);


    }
    //    ObservableList<String> privateList = FXCollections.observableArrayList("Athletes", "Audiences",
//            "Events", "Games", "Medals", "Olympic Events", "Judges", "Volunteers");
    public void setUpRemoveFilterScene(String table) {
        attributeMenu = new ComboBox<String>();
        operators = new ComboBox<String>();
        valueTF = new TextField();
        submitFilterRemoveDataButton = MakeComponent.makeButton("Remove Data", 250, 100,
                APPLICATION_WIDTH /2.75, APPLICATION_HEIGHT / 1.3, new Font(25));
        submitFilterRemoveDataButton.setOnAction(this);

        skipFilterRemoveDataButton = MakeComponent.makeButton("No Filter", 250, 100,
                APPLICATION_WIDTH - 250, APPLICATION_HEIGHT - 100, new Font(25));
        skipFilterRemoveDataButton.setOnAction(this);

        backtoPrivateEditButton = MakeComponent.makeButton(
                "Back", 150, 50, 175, 0, new Font(20));
        backtoPrivateEditButton.setOnAction(this);

        valueTF.setPrefWidth(250);
        valueTF.setLayoutX(APPLICATION_WIDTH / 2.75);
        valueTF.setLayoutY(APPLICATION_HEIGHT/1.45);
        valueTF.setFont(new Font(15));

        Label filterTitle = MakeComponent.makeLabel("Filter for Removal",800,100,
                APPLICATION_WIDTH / 4,
                APPLICATION_HEIGHT / 9,Font.font(null,FontWeight.BOLD, 60));

        attributeMenu.setPrefSize(250,50);
        attributeMenu.setLayoutX(APPLICATION_WIDTH / 2.75);
        attributeMenu.setLayoutY(APPLICATION_HEIGHT / 2.3);
        attributeMenu.setOnAction(this);

        operators.setPrefSize(250, 50);
        operators.setLayoutX(APPLICATION_WIDTH / 2.75);
        operators.setLayoutY(APPLICATION_HEIGHT / 1.8);


        changeAttributes(table);

        changeOperator();


        Pane layout = new Pane();
        layout.getChildren().addAll(filterTitle,backToLoginButton, attributeMenu,operators,
                valueTF, submitFilterRemoveDataButton, backtoPrivateEditButton, skipFilterRemoveDataButton);
        removeFilterScene = new Scene(layout, APPLICATION_WIDTH, APPLICATION_HEIGHT);

    }

    public void setUpUpdateFilterScene(String table) {
        attributeMenu = new ComboBox<String>();
        operators = new ComboBox<String>();
        valueTF = new TextField();

        Label filterTitle = MakeComponent.makeLabel("Filter for Update",800,100,
                APPLICATION_WIDTH / 4,
                APPLICATION_HEIGHT / 9,Font.font(null,FontWeight.BOLD, 60));

        submitFilterUpdateDataButton = MakeComponent.makeButton("Update Data", 250, 100,
                APPLICATION_WIDTH /2.75, APPLICATION_HEIGHT / 1.3, new Font(25));
        submitFilterUpdateDataButton.setOnAction(this);

        skipFilterUpdateDataButton = MakeComponent.makeButton("No Filter", 250, 100,
                APPLICATION_WIDTH - 250, APPLICATION_HEIGHT - 100, new Font(25));
        skipFilterUpdateDataButton.setOnAction(this);

        valueTF.setPrefWidth(250);
        valueTF.setLayoutX(APPLICATION_WIDTH / 2.75);
        valueTF.setLayoutY(APPLICATION_HEIGHT/1.45);
        valueTF.setFont(new Font(15));

        attributeMenu.setPrefSize(250,50);
        attributeMenu.setLayoutX(APPLICATION_WIDTH / 2.75);
        attributeMenu.setLayoutY(APPLICATION_HEIGHT / 2.3);
        attributeMenu.setOnAction(this);

        operators.setPrefSize(250, 50);
        operators.setLayoutX(APPLICATION_WIDTH / 2.75);
        operators.setLayoutY(APPLICATION_HEIGHT / 1.8);

        changeAttributes(table);

        changeOperator();

        Pane layout = new Pane();
        layout.getChildren().addAll(submitFilterUpdateDataButton,attributeMenu,operators,filterTitle,valueTF,backtoPrivateEditButton,backToLoginButton,skipFilterUpdateDataButton);
        updateFilterScene = new Scene(layout, APPLICATION_WIDTH, APPLICATION_HEIGHT);

    }

    public void changeAttributes(String table) {
        if (table.equals("Athletes")) {
            if (cbAthlete.isSelected()) {
                //    ObservableList<String> medalsReceivedAttributes = FXCollections.observableArrayList("Participant ID",
                //            "Medal Type", "Sport Name", "Sport Category", "Year", "Season");
                //
                //    //    ObservableList<String> athleteAttributes = FXCollections.observableArrayList("Participant ID",
                //    //            "Name", "Country", "Medal Count", "Birthday", "Gender");
                ObservableList<String> joinedAttributes = FXCollections.observableArrayList("Participant ID", "Name",
                        "Country", "Medal Count", "Birthday", "Gender","Medal Type", "Sport Name", "Sport Category", "Year", "Season");
                attributeMenu.setValue(joinedAttributes.get(0));
                attributeMenu.setItems(joinedAttributes);


            } else {
                attributeMenu.setValue(athleteAttributes.get(0));
                attributeMenu.setItems(athleteAttributes);
            }

        } else if (table.equals("Audiences")) {
            attributeMenu.setValue(audienceAttributes.get(0));
            attributeMenu.setItems(audienceAttributes);
        } else if (table.equals("Events")) {
            attributeMenu.setValue(eventAttributes.get(0));
            attributeMenu.setItems(eventAttributes);
        } else if (table.equals("Games")) {
            attributeMenu.setValue(gameAttributes.get(0));
            attributeMenu.setItems(gameAttributes);
        } else if (table.equals("Medals Offered Records")) {
            attributeMenu.setValue(medalsOfferedAttributes.get(0));
            attributeMenu.setItems(medalsOfferedAttributes);
        } else if (table.equals("Olympic Events")) {
            attributeMenu.setValue(olympicEventAttributes.get(0));
            attributeMenu.setItems(olympicEventAttributes);
        } else if (table.equals("Olympic Judges")) {
            attributeMenu.setValue(judgeAttributes.get(0));
            attributeMenu.setItems(judgeAttributes);
        } else if (table.equals("Volunteers")) {
            // case "Volunteers"
            attributeMenu.setValue(volunteerAttributes.get(0));
            attributeMenu.setItems(volunteerAttributes);
            //    ObservableList<String> privateList = FXCollections.observableArrayList("Athletes", "Audiences",
            //            "Events", "Games", "Medals Offered Records", "Olympic Events", "Olympic Judges", "Volunteers", "Volunteer Records",
            //            "Participation Records", "Medals Received Records", "Watch Records");
        } else if (table.equals("Volunteer Records")) {
            attributeMenu.setValue(volunteerRecordAttributes.get(0));
            attributeMenu.setItems(volunteerRecordAttributes);

        } else if (table.equals("Participation Records")) {
            attributeMenu.setValue(participationRecordAttributes.get(0));
            attributeMenu.setItems(participationRecordAttributes);

        } else if (table.equals("Medals Received Records")) {
            attributeMenu.setValue(medalsReceivedAttributes.get(0));
            attributeMenu.setItems(medalsReceivedAttributes);

        } else if (table.equals("Watch Records")) {
            attributeMenu.setValue(watchRecordsAttributes.get(0));
            attributeMenu.setItems(watchRecordsAttributes);

        }

    }

    public void changeOperator() {
        String currValue = attributeMenu.getValue();

        if (currValue.equals("Medal Count") || currValue.equals("Year")) {
            operators.setValue(numericOperators.get(0));
            operators.setItems(numericOperators);

        } else {
            operators.setValue(stringOperators.get(0));
            operators.setItems(stringOperators);
        }

    }


    public void displayData(ObservableList<HashMap> dataMatrix, String[] projectionList, String tableName, ObservableList<String> columnNames) {
        Label header = MakeComponent.makeLabel(tableName, APPLICATION_WIDTH,
                100, 0, 50, new Font(30));


        header.setAlignment(Pos.CENTER);


        TableView table_view = new TableView<HashMap>(dataMatrix);
        table_view.getSelectionModel().setCellSelectionEnabled(true);
        table_view.setPrefSize(450,400);
        table_view.setLayoutX(APPLICATION_WIDTH / 3.6);
        table_view.setLayoutY( APPLICATION_HEIGHT / 3.5);

        // This snippet of code was written with the help from https://docs.oracle.com/javafx/2/ui_controls/table-view.htm
        Callback<TableColumn<HashMap, String>, TableCell<HashMap, String>>
                cellFactoryForMap = new Callback<TableColumn<HashMap, String>,
                TableCell<HashMap, String>>() {
            @Override
            public TableCell call(TableColumn p) {
                return new TextFieldTableCell(new StringConverter() {
                    @Override
                    public String toString(Object t) {
                        if (t != null) {
                            return t.toString();
                        } else return "";
                    }
                    @Override
                    public Object fromString(String string) {
                        return string;
                    }
                });
            }
        };


        for (int i = 0; i < projectionList.length; i++) {

            TableColumn<HashMap, String> firstDataColumn = new TableColumn<>(columnNames.get(i));

            firstDataColumn.setCellValueFactory(new MapValueFactory(projectionList[i]));



            firstDataColumn.setMinWidth(200);

            table_view.getColumns().add(firstDataColumn);
            firstDataColumn.setCellFactory(cellFactoryForMap);


        }

        Pane layout = new Pane();
        layout.getChildren().addAll(table_view,header,returnToMainMenu);

        displayDataScene = new Scene(layout,APPLICATION_WIDTH,APPLICATION_HEIGHT);

    }


    // TODO: NOT FINAL, JUST PUTTING RANDOM LOGIN AS STUB
    public boolean loginIsValid() {
        // only valid login right now
        // username: grilled
        // password: cheese
        return ((usernameTF.getText().toLowerCase().trim().equals("grilled")) &&
                (passwordTF.getText().toLowerCase().trim().equals("cheese")));

    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == useAsGuest) {
            setUpGuestScene();
            stage.setScene(guestScene);

        } else if (event.getSource() == returnToMainMenu) {
            setUpMainMenuScene();
            stage.setScene(mainMenuScene);
        } else if (event.getSource() == staffLogin) {
            setUpStaffLoginScene();
            stage.setScene(staffLoginScene);
        } else if (event.getSource() == loginButton) {
            if (loginIsValid()) {
                setUpPrivateExplorerScene();
                stage.setScene(privateScene);
            } else {
                new ErrorWindow("Incorrect Login Credentials.");
                setUpStaffLoginScene();
                stage.setScene(staffLoginScene);
            }

        } else if (event.getSource() == editEntityMenu) {
            if (editEntityMenu.getValue().equals("Athletes")) {
                myListView.setItems(athleteAttributes);
                myListView.getSelectionModel().clearSelection();

                privateEditAttributeMenu.setValue(athleteAttributes.get(0));
                privateEditAttributeMenu.setItems(athleteAttributes);

                currentChanges.setItems(athleteAttributes);
                changesList = new String[athleteAttributes.size()];

            } else if (editEntityMenu.getValue().equals("Events")) {
                myListView.setItems(eventAttributes);
                myListView.getSelectionModel().clearSelection();

                privateEditAttributeMenu.setValue(eventAttributes.get(0));
                privateEditAttributeMenu.setItems(eventAttributes);

                currentChanges.setItems(eventAttributes);
                changesList = new String[eventAttributes.size()];



            } else if (editEntityMenu.getValue().equals("Games")) {
                myListView.setItems(gameAttributes);
                myListView.getSelectionModel().clearSelection();

                privateEditAttributeMenu.setValue(gameAttributes.get(0));
                privateEditAttributeMenu.setItems(gameAttributes);

                currentChanges.setItems(gameAttributes);
                changesList = new String[gameAttributes.size()];

            } else if (editEntityMenu.getValue().equals("Audiences")) {
                myListView.setItems(audienceAttributes);
                myListView.getSelectionModel().clearSelection();

                privateEditAttributeMenu.setValue(audienceAttributes.get(0));
                privateEditAttributeMenu.setItems(audienceAttributes);

                currentChanges.setItems(audienceAttributes);
                changesList = new String[audienceAttributes.size()];

            } else if (editEntityMenu.getValue().equals("Medals Offered Records")) {
                myListView.setItems(medalsOfferedAttributes);
                myListView.getSelectionModel().clearSelection();

                privateEditAttributeMenu.setValue(medalsOfferedAttributes.get(0));
                privateEditAttributeMenu.setItems(medalsOfferedAttributes);

                currentChanges.setItems(medalsOfferedAttributes);
                changesList = new String[medalsOfferedAttributes.size()];

            } else if (editEntityMenu.getValue().equals("Olympic Events")) {
                myListView.setItems(olympicEventAttributes);
                myListView.getSelectionModel().clearSelection();

                privateEditAttributeMenu.setValue(olympicEventAttributes.get(0));
                privateEditAttributeMenu.setItems(olympicEventAttributes);

                currentChanges.setItems(olympicEventAttributes);
                changesList = new String[olympicEventAttributes.size()];

            } else if (editEntityMenu.getValue().equals("Olympic Judges")) {
                myListView.setItems(judgeAttributes);
                myListView.getSelectionModel().clearSelection();

                privateEditAttributeMenu.setValue(judgeAttributes.get(0));
                privateEditAttributeMenu.setItems(judgeAttributes);

                currentChanges.setItems(judgeAttributes);
                changesList = new String[judgeAttributes.size()];

            } else if (editEntityMenu.getValue().equals("Volunteers")) {
                myListView.setItems(volunteerAttributes);
                myListView.getSelectionModel().clearSelection();

                privateEditAttributeMenu.setValue(volunteerAttributes.get(0));
                privateEditAttributeMenu.setItems(volunteerAttributes);

                currentChanges.setItems(volunteerAttributes);
                changesList = new String[volunteerAttributes.size()];

            } else if (editEntityMenu.getValue().equals("Volunteer Records")) {

                myListView.setItems(volunteerRecordAttributes);
                myListView.getSelectionModel().clearSelection();

                privateEditAttributeMenu.setValue(volunteerRecordAttributes.get(0));
                privateEditAttributeMenu.setItems(volunteerRecordAttributes);

                currentChanges.setItems(volunteerRecordAttributes);
                changesList = new String[volunteerRecordAttributes.size()];

            } else if (editEntityMenu.getValue().equals("Participation Records")) {

                myListView.setItems(participationRecordAttributes);
                myListView.getSelectionModel().clearSelection();

                privateEditAttributeMenu.setValue(participationRecordAttributes.get(0));
                privateEditAttributeMenu.setItems(participationRecordAttributes);

                currentChanges.setItems(participationRecordAttributes);
                changesList = new String[participationRecordAttributes.size()];

            } else if (editEntityMenu.getValue().equals("Medals Received Records")) {
                myListView.setItems(medalsReceivedAttributes);
                myListView.getSelectionModel().clearSelection();

                privateEditAttributeMenu.setValue(medalsReceivedAttributes.get(0));
                privateEditAttributeMenu.setItems(medalsReceivedAttributes);

                currentChanges.setItems(medalsReceivedAttributes);
                changesList = new String[medalsReceivedAttributes.size()];

            } else if (editEntityMenu.getValue().equals("Watch Records")) {
                myListView.setItems(watchRecordsAttributes);
                myListView.getSelectionModel().clearSelection();

                privateEditAttributeMenu.setValue(watchRecordsAttributes.get(0));
                privateEditAttributeMenu.setItems(watchRecordsAttributes);

                currentChanges.setItems(watchRecordsAttributes);
                changesList = new String[watchRecordsAttributes.size()];

            }
        } else if (event.getSource() == submitButton) {
            if (myListView.getSelectionModel().getSelectedItems().size() == 0) {
                new ErrorWindow("Select at least 1 attribute.");

            } else {
                setUpLookUpFilterScene(lookUpEntityMenu.getValue());
                stage.setScene(lookupFilterScene);
            }
        } else if (event.getSource() == lookUpDatabaseButton) {
            setUpPrivateLookUpScene();
            stage.setScene(privateLookUpScene);


        } else if (event.getSource() == editDatabaseButton) {
            setUpPrivateEditScene();
            stage.setScene(privateEditScene);

        } else if (event.getSource() == backToLoginButton) {
            setUpStaffLoginScene();
            stage.setScene(staffLoginScene);
        } else if (event.getSource() == backToPrivateExplorerButton) {
            setUpPrivateExplorerScene();
            stage.setScene(privateScene);

        } else if (event.getSource() == addActionButton) {
            setUpAddDataScene();
            stage.setScene(addDataScene);

        } else if (event.getSource() == removeActionButton) {
            setUpRemoveDataScene();
            stage.setScene(removeDataScene);

        } else if (event.getSource() == updateActionButton) {
            setUpUpdateDataScene();
            stage.setScene(updateDataScene);

        } else if (event.getSource() == logChangesButton) {
            int changeIndex = privateEditAttributeMenu.getItems().indexOf(privateEditAttributeMenu.getValue());
            changesList[changeIndex] = attributeTF.getText();

            ObservableList<String> displayList = FXCollections.observableArrayList();
            for (int i = 0; i < privateEditAttributeMenu.getItems().size(); i++) {
                displayList.add(i, privateEditAttributeMenu.getItems().get(i) + ": " + changesList[i]);
            }
            currentChanges.setItems(displayList);
            attributeTF.clear();

        } else if (event.getSource() == addEntryButton) {
//            System.out.println("ADD");
//            System.out.println(editEntityMenu.getValue());
//            System.out.println(Arrays.toString(changesList));
            operations.handleInsert(editEntityMenu.getValue(), changesList);
        } else if (event.getSource() == backtoPrivateEditButton) {
            setUpPrivateEditScene();
            stage.setScene(privateEditScene);
        } else if (event.getSource() == submitRemoveButton) {
            setUpRemoveFilterScene(removeEntityMenu.getValue());
            stage.setScene(removeFilterScene);
            // System.out.println(entityMenu.getValue());
        } else if (event.getSource() == attributeMenu) {
            changeOperator();

        } else if (event.getSource() == submitFilterRemoveDataButton) {
            ArrayList<String> filterList = new ArrayList<String>();
            filterList.add(attributeMenu.getValue());
            filterList.add(operators.getValue());
            filterList.add(valueTF.getText());
            operations.handleRemove(removeEntityMenu.getValue(), filterList);
//            System.out.println("REMOVE");
//            System.out.println(removeEntityMenu.getValue());
//            System.out.println(attributeMenu.getValue());
//            System.out.println(operators.getValue());
//            System.out.println(valueTF.getText());
        } else if (event.getSource() == lookUpEntityMenu) {
            if (lookUpEntityMenu.getValue().equals("Athletes")) {
                myListView.setItems(athleteAttributes);
                myListView.getSelectionModel().clearSelection();
                cbAthlete.setSelected(false);
                cbAthlete.setVisible(true);
                cbHelps.setVisible(false);
                cbJudges.setVisible(false);
                agOlympicEvent.setVisible(false);

                findVolunteersWhoHelpedEveryGame.setVisible(false);
                agWithHavingButton.setVisible(true);
                findWellPerformingCountries.setVisible(true);


            } else if (lookUpEntityMenu.getValue().equals("Events")) {
                myListView.setItems(eventAttributes);
                myListView.getSelectionModel().clearSelection();
                cbAthlete.setVisible(false);
                cbHelps.setVisible(false);
                cbJudges.setVisible(false);
                agOlympicEvent.setVisible(false);

                findVolunteersWhoHelpedEveryGame.setVisible(false);
                agWithHavingButton.setVisible(false);
                findWellPerformingCountries.setVisible(false);


            } else if (lookUpEntityMenu.getValue().equals("Games")) {
                myListView.setItems(gameAttributes);
                myListView.getSelectionModel().clearSelection();
                cbAthlete.setVisible(false);
                cbHelps.setVisible(false);
                cbJudges.setVisible(false);
                agOlympicEvent.setVisible(false);

                findVolunteersWhoHelpedEveryGame.setVisible(false);
                agWithHavingButton.setVisible(false);
                findWellPerformingCountries.setVisible(false);



            } else if (lookUpEntityMenu.getValue().equals("Audiences")) {
                myListView.setItems(audienceAttributes);
                myListView.getSelectionModel().clearSelection();
                cbAthlete.setVisible(false);
                cbHelps.setVisible(false);
                cbJudges.setVisible(false);
                agOlympicEvent.setVisible(false);

                findVolunteersWhoHelpedEveryGame.setVisible(false);
                agWithHavingButton.setVisible(false);
                findWellPerformingCountries.setVisible(false);

            } else if (lookUpEntityMenu.getValue().equals("Medals Offered Records")) {
                myListView.setItems(medalsOfferedAttributes);
                myListView.getSelectionModel().clearSelection();
                cbAthlete.setVisible(false);
                cbHelps.setVisible(false);
                cbJudges.setVisible(false);
                agOlympicEvent.setVisible(false);

                findVolunteersWhoHelpedEveryGame.setVisible(false);
                agWithHavingButton.setVisible(false);
                findWellPerformingCountries.setVisible(false);


            } else if (lookUpEntityMenu.getValue().equals("Olympic Events")) {
                myListView.setItems(olympicEventAttributes);
                myListView.getSelectionModel().clearSelection();
                cbAthlete.setVisible(false);
                cbHelps.setVisible(false);
                cbJudges.setVisible(false);
                agOlympicEvent.setVisible(true);

                findVolunteersWhoHelpedEveryGame.setVisible(false);
                agWithHavingButton.setVisible(false);
                findWellPerformingCountries.setVisible(false);



            } else if (lookUpEntityMenu.getValue().equals("Olympic Judges")) {
                myListView.setItems(judgeAttributes);
                myListView.getSelectionModel().clearSelection();
                cbJudges.setSelected(false);
                cbJudges.setVisible(true);
                cbAthlete.setVisible(false);
                cbHelps.setVisible(false);
                agOlympicEvent.setVisible(false);

                findVolunteersWhoHelpedEveryGame.setVisible(false);
                agWithHavingButton.setVisible(false);
                findWellPerformingCountries.setVisible(false);


            } else if (lookUpEntityMenu.getValue().equals("Volunteers")) {
                myListView.setItems(volunteerAttributes);
                myListView.getSelectionModel().clearSelection();
                cbHelps.setSelected(false);
                cbHelps.setVisible(true);
                cbAthlete.setVisible(false);
                cbJudges.setVisible(false);
                agOlympicEvent.setVisible(false);

                findVolunteersWhoHelpedEveryGame.setVisible(true);
                agWithHavingButton.setVisible(false);
                findWellPerformingCountries.setVisible(false);

                //    ObservableList<String> privateList = FXCollections.observableArrayList("Athletes", "Audiences",
                //            "Events", "Games", "Medals Offered Records", "Olympic Events", "Olympic Judges", "Volunteers", "Volunteer Records",
                //            "Participation Records", "Medals Received Records", "Watch Records");

            } else if (lookUpEntityMenu.getValue().equals("Volunteer Records")) {

                myListView.setItems(volunteerRecordAttributes);
                myListView.getSelectionModel().clearSelection();
                cbAthlete.setVisible(false);
                cbHelps.setVisible(false);
                cbJudges.setVisible(false);
                agOlympicEvent.setVisible(false);

                findVolunteersWhoHelpedEveryGame.setVisible(true);
                agWithHavingButton.setVisible(false);
                findWellPerformingCountries.setVisible(false);

            } else if (lookUpEntityMenu.getValue().equals("Participation Records")) {

                myListView.setItems(participationRecordAttributes);
                myListView.getSelectionModel().clearSelection();
                cbAthlete.setVisible(false);
                cbHelps.setVisible(false);
                cbJudges.setVisible(false);
                agOlympicEvent.setVisible(false);

                findVolunteersWhoHelpedEveryGame.setVisible(false);
                agWithHavingButton.setVisible(false);
                findWellPerformingCountries.setVisible(false);

            } else if (lookUpEntityMenu.getValue().equals("Medals Received Records")) {

                myListView.setItems(medalsReceivedAttributes);
                myListView.getSelectionModel().clearSelection();
                cbAthlete.setVisible(false);
                cbHelps.setVisible(false);
                cbJudges.setVisible(false);
                agOlympicEvent.setVisible(false);

                findVolunteersWhoHelpedEveryGame.setVisible(false);
                agWithHavingButton.setVisible(false);
                findWellPerformingCountries.setVisible(false);

            } else if (lookUpEntityMenu.getValue().equals("Watch Records")) {

                myListView.setItems(watchRecordsAttributes);
                myListView.getSelectionModel().clearSelection();
                cbAthlete.setVisible(false);
                cbHelps.setVisible(false);
                cbJudges.setVisible(false);
                agOlympicEvent.setVisible(false);

                findVolunteersWhoHelpedEveryGame.setVisible(false);
                agWithHavingButton.setVisible(false);
                findWellPerformingCountries.setVisible(false);
            }
        } else if (event.getSource() == submitFilterLookupDataButton) {
            ArrayList<String> filterList = new ArrayList<String>();
            filterList.add(attributeMenu.getValue());
            filterList.add(operators.getValue());
            filterList.add(valueTF.getText());
//            ObservableList<HashMap> resultToBeDisplayed = operations.handleLookUp(lookUpEntityMenu.getValue(),
//                    myListView.getSelectionModel().getSelectedItems(),filterList);
//
//            String[] projectionList = operations.getProjectionList(myListView.getSelectionModel().getSelectedItems());
//            displayData(resultToBeDisplayed, projectionList,lookUpEntityMenu.getValue(), myListView.getSelectionModel().getSelectedItems());
//            stage.setScene(displayDataScene);
            ObservableList<HashMap> resultToBeDisplayed;
            String[] projectionList;
            if (cbAthlete.isSelected() && lookUpEntityMenu.getValue().equals("Athletes")) {
                if (medalsReceivedAttributes.contains(filterList.get(0))) {
                    resultToBeDisplayed = operations.handleLookUpJoin(lookUpEntityMenu.getValue(),
                            myListView.getSelectionModel().getSelectedItems(),filterList, "Receives.");
                } else {
                    resultToBeDisplayed = operations.handleLookUpJoin(lookUpEntityMenu.getValue(),
                            myListView.getSelectionModel().getSelectedItems(),filterList, "Athlete.");
                }
//                resultToBeDisplayed = operations.handleLookUpJoin(lookUpEntityMenu.getValue(),
//                        myListView.getSelectionModel().getSelectedItems(),filterList);
//                List<String> joinColumn = new ArrayList<String>(resultToBeDisplayed.get(0).keySet());
//                ObservableList<String> joinColumnName = FXCollections.observableArrayList(joinColumn);
                ObservableList<String> joinColumnName;
                if (resultToBeDisplayed.size() == 0) {
                    joinColumnName = myListView.getSelectionModel().getSelectedItems();
                } else {
                    List<String> columnName = new ArrayList<String>(resultToBeDisplayed.get(0).keySet());
                    joinColumnName = FXCollections.observableArrayList(columnName);
                }
                String str = "Receives";
                projectionList = operations.translateProjectionsJoin(myListView.getSelectionModel().getSelectedItems(), str);
                displayData(resultToBeDisplayed, projectionList,lookUpEntityMenu.getValue(), joinColumnName);
                stage.setScene(displayDataScene);
            } else if (cbJudges.isSelected() && lookUpEntityMenu.getValue().equals("Olympic Judges")) {
                resultToBeDisplayed = operations.handleLookUpJoin(lookUpEntityMenu.getValue(),
                        myListView.getSelectionModel().getSelectedItems(),filterList, "");
//                List<String> joinColumn = new ArrayList<String>(resultToBeDisplayed.get(0).keySet());
//                ObservableList<String> joinColumnName = FXCollections.observableArrayList(joinColumn);
                ObservableList<String> joinColumnName;
                if (resultToBeDisplayed.size() == 0) {
                    joinColumnName = myListView.getSelectionModel().getSelectedItems();
                } else {
                    List<String> columnName = new ArrayList<String>(resultToBeDisplayed.get(0).keySet());
                    joinColumnName = FXCollections.observableArrayList(columnName);
                }
                String str = "Judges";
                projectionList = operations.translateProjectionsJoin(myListView.getSelectionModel().getSelectedItems(), str);
                displayData(resultToBeDisplayed, projectionList,lookUpEntityMenu.getValue(), joinColumnName);
                stage.setScene(displayDataScene);

            } else if (cbHelps.isSelected() && lookUpEntityMenu.getValue().equals("Volunteers")) {
                resultToBeDisplayed = operations.handleLookUpJoin(lookUpEntityMenu.getValue(),
                        myListView.getSelectionModel().getSelectedItems(),filterList, "");
//                List<String> joinColumn = new ArrayList<String>(resultToBeDisplayed.get(0).keySet());
//                ObservableList<String> joinColumnName = FXCollections.observableArrayList(joinColumn);
                ObservableList<String> joinColumnName;
                if (resultToBeDisplayed.size() == 0) {
                    joinColumnName = myListView.getSelectionModel().getSelectedItems();
                } else {
                    List<String> columnName = new ArrayList<String>(resultToBeDisplayed.get(0).keySet());
                    joinColumnName = FXCollections.observableArrayList(columnName);
                }
                String str = "Helps";
                projectionList = operations.translateProjectionsJoin(myListView.getSelectionModel().getSelectedItems(), str);
                displayData(resultToBeDisplayed, projectionList,lookUpEntityMenu.getValue(), joinColumnName);
                stage.setScene(displayDataScene);

            } else {
                resultToBeDisplayed = operations.handleLookUp(lookUpEntityMenu.getValue(),
                        myListView.getSelectionModel().getSelectedItems(),filterList);
                projectionList = operations.getProjectionList(myListView.getSelectionModel().getSelectedItems());
                displayData(resultToBeDisplayed, projectionList,lookUpEntityMenu.getValue(), myListView.getSelectionModel().getSelectedItems());
                stage.setScene(displayDataScene);
            }
            cbAthlete.setSelected(false);
        } else if (event.getSource() == updateEntryButton) {
            boolean containsChange = false;
            for (int i = 0; i < changesList.length; i++) {
                if (changesList[i] != null) {
                    containsChange = true;
                    break;
                }
            }

            if (!containsChange) {
                new ErrorWindow("Make at least one change.");
            } else {
                setUpUpdateFilterScene(editEntityMenu.getValue());
                stage.setScene(updateFilterScene);


            }

        } else if (event.getSource() == submitFilterUpdateDataButton) {
            ArrayList<String> filterList = new ArrayList<String>();
            filterList.add(attributeMenu.getValue());
            filterList.add(operators.getValue());
            filterList.add(valueTF.getText());

            operations.handleUpdate(editEntityMenu.getValue(), changesList,attributeMenu.getItems(), filterList);

        } else if (event.getSource() == skipFilterLookupDataButton) {
            ArrayList<String> filterList = new ArrayList<String>();

            ObservableList<HashMap> resultToBeDisplayed;
            String[] projectionList;
//            System.out.println(cbAthlete.isSelected());
            if (cbAthlete.isSelected() && lookUpEntityMenu.getValue().equals("Athletes")) {
                resultToBeDisplayed = operations.handleLookUpJoin(lookUpEntityMenu.getValue(),
                        myListView.getSelectionModel().getSelectedItems(),filterList,"");
//                List<String> joinColumn = new ArrayList<String>(resultToBeDisplayed.get(0).keySet());
//                ObservableList<String> joinColumnName = FXCollections.observableArrayList(joinColumn);
                ObservableList<String> joinColumnName;
                if (resultToBeDisplayed.size() == 0) {
                    joinColumnName = myListView.getSelectionModel().getSelectedItems();
                } else {
                    List<String> columnName = new ArrayList<String>(resultToBeDisplayed.get(0).keySet());
                    joinColumnName = FXCollections.observableArrayList(columnName);
                }
                String str = "Receives";
                projectionList = operations.translateProjectionsJoin(myListView.getSelectionModel().getSelectedItems(), str);
                displayData(resultToBeDisplayed, projectionList,lookUpEntityMenu.getValue(), joinColumnName);
                stage.setScene(displayDataScene);

            } else if (cbJudges.isSelected() && lookUpEntityMenu.getValue().equals("Olympic Judges")) {
                resultToBeDisplayed = operations.handleLookUpJoin(lookUpEntityMenu.getValue(),
                        myListView.getSelectionModel().getSelectedItems(),filterList,"");
//                List<String> joinColumn = new ArrayList<String>(resultToBeDisplayed.get(0).keySet());
//                ObservableList<String> joinColumnName = FXCollections.observableArrayList(joinColumn);
                ObservableList<String> joinColumnName;
                if (resultToBeDisplayed.size() == 0) {
                    joinColumnName = myListView.getSelectionModel().getSelectedItems();
                } else {
                    List<String> columnName = new ArrayList<String>(resultToBeDisplayed.get(0).keySet());
                    joinColumnName = FXCollections.observableArrayList(columnName);
                }
                String str = "Judges";
                projectionList = operations.translateProjectionsJoin(myListView.getSelectionModel().getSelectedItems(), str);
                displayData(resultToBeDisplayed, projectionList,lookUpEntityMenu.getValue(), joinColumnName);
                stage.setScene(displayDataScene);

            } else if (cbHelps.isSelected() &&lookUpEntityMenu.getValue().equals("Volunteers")) {
                resultToBeDisplayed = operations.handleLookUpJoin(lookUpEntityMenu.getValue(),
                        myListView.getSelectionModel().getSelectedItems(),filterList,"");
//                List<String> joinColumn = new ArrayList<String>(resultToBeDisplayed.get(0).keySet());
//                ObservableList<String> joinColumnName = FXCollections.observableArrayList(joinColumn);

                ObservableList<String> joinColumnName;
                if (resultToBeDisplayed.size() == 0) {
                    joinColumnName = myListView.getSelectionModel().getSelectedItems();
                } else {
                    List<String> columnName = new ArrayList<String>(resultToBeDisplayed.get(0).keySet());
                    joinColumnName = FXCollections.observableArrayList(columnName);
                }

                String str = "Helps";
                projectionList = operations.translateProjectionsJoin(myListView.getSelectionModel().getSelectedItems(), str);
                displayData(resultToBeDisplayed, projectionList,lookUpEntityMenu.getValue(), joinColumnName);
                stage.setScene(displayDataScene);

            } else {
                resultToBeDisplayed = operations.handleLookUp(lookUpEntityMenu.getValue(),
                        myListView.getSelectionModel().getSelectedItems(),filterList);
                projectionList = operations.getProjectionList(myListView.getSelectionModel().getSelectedItems());
                displayData(resultToBeDisplayed, projectionList,lookUpEntityMenu.getValue(), myListView.getSelectionModel().getSelectedItems());
                stage.setScene(displayDataScene);
            }

        } else if (event.getSource() == agOlympicEvent){
            ObservableList<HashMap> resultToBeDisplayed = db.aggregationGroupByCountries();
            List<String> columnName = new ArrayList<String>();
            columnName.add("country");
            columnName.add("City_Count");
            ObservableList<String> agColumnName = FXCollections.observableArrayList(columnName);
            String[] projectionList = {"country", "City_Count"};
            String tableName = "The city count group by each country for hosting Olympic Events";
            displayData(resultToBeDisplayed, projectionList, tableName, agColumnName);
            stage.setScene(displayDataScene);
        } else if (event.getSource() == skipFilterRemoveDataButton) {
            ArrayList<String> filterList = new ArrayList<String>();
            operations.handleRemove(removeEntityMenu.getValue(), filterList);

        } else if (event.getSource() == skipFilterUpdateDataButton) {
            ArrayList<String> filterList = new ArrayList<String>();
            operations.handleUpdate(editEntityMenu.getValue(), changesList,attributeMenu.getItems(), filterList);

        } else if (event.getSource() == agWithHavingButton) {
            ObservableList<HashMap> resultToBeDisplayed = db.aggregationWithHaving();
            ObservableList agColumnName = FXCollections.observableArrayList();;
//            if (resultToBeDisplayed.size() == 0) {
//                agColumnName = myListView.getSelectionModel().getSelectedItems();
//            } else {
//                List<String> columnName = new ArrayList<String>(resultToBeDisplayed.get(0).keySet());
//                agColumnName = FXCollections.observableArrayList(columnName);
//            }
            agColumnName.add("country");
            agColumnName.add("Minimum Medal Count");

            String[] projectionList = {"min(medal_count)", "country"};
            String tableName = "The lowest athlete medal count in countries that\n have more than 1 athlete";
            displayData(resultToBeDisplayed, projectionList, tableName, agColumnName);
            stage.setScene(displayDataScene);
        } else if (event.getSource() == findVolunteersWhoHelpedEveryGame) {
            ObservableList<HashMap> resultList = db.divisionVolunteersWhoHelpedAllGames();
            String[] projectionList = new String[]{"employeeID","name"};
            ObservableList columns = FXCollections.observableArrayList();
            columns.add("Employee ID");
            columns.add("Name");
            displayData(resultList,projectionList, "Volunteers that helped in all Games", columns);
            stage.setScene(displayDataScene);

        } else if (event.getSource() == findWellPerformingCountries) {

            ObservableList<HashMap> resultList = db.wellPerformingCountriesNestedAggregationGroupBy();
            String[] projectionList = new String[]{"country","avg_medal_count"};
            ObservableList columns = FXCollections.observableArrayList();
            columns.add("Country");
            columns.add("Average Medal Count");
            displayData(resultList,projectionList, "Countries where every athlete has above average medal counts", columns);
            stage.setScene(displayDataScene);

        }
    }
}
