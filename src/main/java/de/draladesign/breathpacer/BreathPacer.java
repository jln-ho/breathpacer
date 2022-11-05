package de.draladesign.breathpacer;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.*;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class BreathPacer extends HBox {

    public BreathPacer() {
        final var stripWidth = 60;

        final var x = new SimpleIntegerProperty(0);
        final var xS = x.asString();

        final var y = new SimpleIntegerProperty(stripWidth);
        final var yS = y.asString();

        final var timeline = new Timeline(new KeyFrame(Duration.millis(18), e -> {
            x.set(x.get() + 1);
            y.set(y.get() + 1);
            if (x.get() >= stripWidth * 2) {
                x.set(0);
                y.set(stripWidth);
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();

        final var style = new SimpleStringProperty("-fx-background-color: linear-gradient(from ")
                .concat(xS).concat("px ").concat(xS).concat("px to ").concat(yS).concat("px ").concat(xS).concat("px")
                .concat(", repeat, blue, derive(blue, 95%));");

        styleProperty().bind(style);
        getStyleClass().addAll("breathpacer");
    }

}