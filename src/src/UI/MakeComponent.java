package UI;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Objects;

public class MakeComponent {


    public static Button makeButton(String text, double sizeX, double sizeY, double layoutX, double layoutY, Font font) {

        Button resultButton = new Button(text);
        resultButton.setPrefSize(sizeX,sizeY);
        resultButton.setLayoutX(layoutX);
        resultButton.setLayoutY(layoutY);
        resultButton.setFont(font);

        return resultButton;
    }

    public static Label makeLabel(String text, double sizeX, double sizeY, double layoutX, double layoutY, Font font) {

        Label resultLabel = new Label(text);
        resultLabel.setPrefSize(sizeX,sizeY);
        resultLabel.setFont(font);
        resultLabel.setLayoutX(layoutX);
        resultLabel.setLayoutY(layoutY);

        return resultLabel;

    }

    public static ImageView makeImage(Image image, double layoutX, double layoutY) {
        ImageView resultImageView = new ImageView();
        resultImageView.setImage(image);
        resultImageView.setLayoutX(layoutX);
        resultImageView.setLayoutY(layoutY);

        return resultImageView;
    }
}
