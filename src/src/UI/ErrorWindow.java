package UI;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Objects;

public class ErrorWindow {
    private final double windowX = 400;
    private final double windowY = 200;

    public ErrorWindow(String msg) {

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Error");

        Image angry = new Image(Objects.requireNonNull(getClass().getResourceAsStream("angry.png")));
        ImageView angryImageView = new ImageView();
        angryImageView.setImage(angry);
        angryImageView.setLayoutX(150);
        angryImageView.setLayoutY(80);
        angryImageView.setFitWidth(100);
        angryImageView.setFitHeight(100);


        Label errorLabel = new Label(msg);
        errorLabel.setLayoutX(windowX / 6);
        errorLabel.setLayoutY(20);
        errorLabel.setFont(new Font(20));
        Pane layout = new Pane();
        layout.getChildren().addAll(errorLabel,angryImageView);
        Scene scene = new Scene(layout, windowX, windowY);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.showAndWait();


    }
}