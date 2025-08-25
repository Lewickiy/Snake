package com.lewickiy.snake;

import com.lewickiy.snake.enumeration.Direction;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import static com.lewickiy.snake.configuration.ProjectConstants.direction;

public class SnakeKeyHandler implements EventHandler<KeyEvent> {
    private final Stage stage;

    public SnakeKeyHandler(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        KeyCode code = keyEvent.getCode();
        switch (code) {
            case UP -> direction = Direction.UP;
            case DOWN -> direction = Direction.DOWN;
            case LEFT -> direction = Direction.LEFT;
            case RIGHT -> direction = Direction.RIGHT;
            case ESCAPE -> stage.close();
        }
    }
}
