import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BreathPacerApplication {

    public static void main(String[] args) {
        Application.launch(JFXApp.class, args);
    }

    public static class JFXApp extends Application {
        private int stageWidth = 600;
        private int stageHeight = 100;

        @Override
        public void start(Stage stage) throws Exception {

            // Set stage dimensions
            stage.setWidth(stageWidth);
            stage.setHeight(stageHeight);

            // Set stage position
            final Screen screen = Screen.getPrimary();
            final int screenWidth = (int) screen.getBounds().getWidth();
            final int screenWeight = (int) screen.getBounds().getHeight();
            final var windowUpperLeftCornerPositionX = (screenWidth / 2) - (stageWidth / 2);
            final var windowUpperLeftCornerPositionY = 0;
            stage.setX(windowUpperLeftCornerPositionX);
            stage.setY(windowUpperLeftCornerPositionY);

            // Set stage style
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setAlwaysOnTop(true);

            // Create content
            final var label = new Label("???????");
            label.setTextFill(Color.WHITE);

            // Create scene
            final var scene = new Scene(label);
            scene.setFill(Color.TRANSPARENT);
            scene.getStylesheets().add("/style.css");

            // Display stage with scene
            stage.setScene(scene);
            stage.show();
        }
    }

}
