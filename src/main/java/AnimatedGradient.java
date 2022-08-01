import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;



public class AnimatedGradient {

    public static void main(String[] args) {
        Application.launch(AnimatedGradient.JFXApp.class, args);
    }

    public static class JFXApp extends Application {

        @Override
        public void start(Stage primaryStage) {

            HBox triangle = new HBox();

            DoubleProperty progressProperty = new SimpleDoubleProperty(0);

            StringProperty styleProperty = new SimpleStringProperty();

            int stripWidth = 30;
            IntegerProperty x = new SimpleIntegerProperty(0);
            StringBinding xS = x.asString();
            IntegerProperty y = new SimpleIntegerProperty(stripWidth);
            StringBinding yS = y.asString();

            styleProperty.bind(new SimpleStringProperty("-fx-background-color: linear-gradient(from ")
                    .concat(xS)
                    .concat("px ")
                    .concat(xS)
                    .concat("px to ")
                    .concat(yS)
                    .concat("px ")
                    .concat(xS).concat("px, repeat, blue 50%, derive(blue, 70%) 50%);"));

            triangle.styleProperty().bind(styleProperty);

            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(15), e -> {
                x.set(x.get() + 1);
                y.set(y.get() + 1);
                if (x.get() >= stripWidth * 2) {
                    x.set(0);
                    y.set(stripWidth);
                }
            }));
            timeline.setCycleCount(Animation.INDEFINITE);
            progressProperty.addListener((obs, old, val) -> {
                if (old.doubleValue() <= 0) {
                    timeline.playFromStart();
                }
            });

            Timeline task = new Timeline(
                    new KeyFrame(
                            Duration.ZERO,
                            new KeyValue(progressProperty, 0)
                    ),
                    new KeyFrame(
                            Duration.seconds(10),
                            new KeyValue(progressProperty, 1)
                    )
            );

            task.playFromStart();

            StackPane root = new StackPane();
            root.setPadding(new Insets(50, 50, 50, 50));

            triangle.setPrefSize(2000, 100);
            root.getChildren().add(triangle);
            triangle.getStyleClass().addAll("polygon-pane");
            Scene scene = new Scene(root,400,400);
            triangle.prefWidthProperty().bind(scene.widthProperty());
            triangle.prefHeightProperty().bind(scene.heightProperty());
            scene.getStylesheets().add(getClass().getResource("polygon.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        }

    }

}