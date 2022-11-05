package de.draladesign.breathpacer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
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
        private int stageHeight = 20;

        @Override
        public void start(Stage stage) {
            // Set stage dimensions
            stage.setWidth(stageWidth);
            stage.setHeight(stageHeight);

            // Set stage position
            final var screen = Screen.getPrimary();
            final var screenWidth = (int) screen.getBounds().getWidth();
            final var screenHeight = (int) screen.getBounds().getHeight();
            //final var windowUpperLeftCornerPositionX = (screenWidth / 2) - (stageWidth / 2);
            final var windowUpperLeftCornerPositionX = screenWidth - stageWidth - 100;
            final var windowUpperLeftCornerPositionY = 1;
            stage.setX(windowUpperLeftCornerPositionX);
            stage.setY(windowUpperLeftCornerPositionY);

            // Set stage style
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setAlwaysOnTop(true);

            // Create content
            final var root = new StackPane();

            final var breathPacer = new BreathPacer();
            root.getChildren().add(breathPacer);

            // Create scene
            final var scene = new Scene(root, stageWidth, stageHeight);
            scene.setFill(Color.TRANSPARENT);
            scene.getStylesheets().add("/style.css");

            // Display stage with scene
            stage.setScene(scene);
            stage.show();
        }
    }

}
