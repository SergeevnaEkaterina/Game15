package application;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Message {



    //добавить картинки



    public void lose()  {
        Image img = null;
        try {
            img = new Image(new FileInputStream("resources/lose.jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert img != null;
        BackgroundImage backgroundImage = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, false));
        Pane root = new Pane();
        root.setBackground(new Background(backgroundImage));
        Scene secondScene = new Scene(root, 800, 800);
        Stage secondStage = new Stage();
        secondStage.setTitle("You lost! Try again!");
        secondStage.setScene(secondScene);
        secondStage.initStyle(StageStyle.DECORATED);
        secondStage.initModality(Modality.APPLICATION_MODAL);
        secondStage.show();
    }

    public void win() {
        Image img = null;
        try {
            img = new Image(new FileInputStream("resources/victory.jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert img != null;
        BackgroundImage backgroundImage = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, false));
        Pane root = new Pane();
        root.setBackground(new Background(backgroundImage));
        Scene secondScene = new Scene(root, 800, 800);
        Stage secondStage = new Stage();
        secondStage.setTitle("You won! Congratulations!");
        secondStage.setScene(secondScene);
        secondStage.initStyle(StageStyle.DECORATED);
        secondStage.initModality(Modality.APPLICATION_MODAL);
        secondStage.show();
    }
    public void unsolvableCombination()  {
        Image img = null;
        try {
            img = new Image(new FileInputStream("resources/lose.jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert img != null;
        BackgroundImage backgroundImage = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, false));
        Pane root = new Pane();
        root.setBackground(new Background(backgroundImage));
        Scene secondScene = new Scene(root, 800, 800);
        Stage secondStage = new Stage();
        secondStage.setTitle("Unsolvable combination!");
        secondStage.setScene(secondScene);
        secondStage.initStyle(StageStyle.DECORATED);
        secondStage.initModality(Modality.APPLICATION_MODAL);
        secondStage.show();
    }
}
