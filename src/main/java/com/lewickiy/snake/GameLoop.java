package com.lewickiy.snake;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

import static com.lewickiy.snake.SnakeApplication.game;

public class GameLoop extends AnimationTimer {

    private final GraphicsContext gc;
    private long lastTick = 0;

    public GameLoop(GraphicsContext gc) {
        this.gc = gc;
    }

    @Override
    public void handle(long now) {
        if (lastTick == 0) {
            lastTick = now;
            SnakeApplication.tick(gc);
            return;
        }
        if (now - lastTick > 1000000000 / game.getSpeed()) {
            lastTick = now;
            SnakeApplication.tick(gc);
        }
    }
}
