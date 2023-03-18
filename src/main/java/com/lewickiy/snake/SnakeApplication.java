package com.lewickiy.snake;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static com.lewickiy.snake.Corner.CORNER_SIZE;
import static com.lewickiy.snake.Food.foodCount;
import static com.lewickiy.snake.Food.newFood;

public class SnakeApplication extends Application {
    static PlayingField playingField = new PlayingField(30);
    static Food food = new Food(20);
    static Game game = new Game(6);
    static List<Corner> snake = new ArrayList<>();
    static Dir direction = Dir.left;
    static boolean gameOver = false;

    @Override
    public void start(Stage stage) {
        try {
            newFood();
            VBox root = new VBox();

            Canvas c = new Canvas(
                    playingField.getWidth() * CORNER_SIZE,
                    playingField.getHeight() * CORNER_SIZE
            );

            GraphicsContext gc = c.getGraphicsContext2D();
            root.getChildren().add(c);

            new AnimationTimer() {
                long lastTick = 0;

                public void handle(long now) {
                    if(lastTick == 0) {
                        lastTick = now;
                        tick(gc);
                        return;
                    }
                    if(now - lastTick > 1000000000 / game.getSpeed()) {
                        lastTick = now;
                        tick(gc);
                    }
                }
            }.start();

            Scene scene = new Scene(
                    root,
                    playingField.getWidth() * CORNER_SIZE,
                    playingField.getHeight() * CORNER_SIZE
            );

            //control
            scene.addEventFilter(KeyEvent.KEY_PRESSED, key ->{
                if(key.getCode() == KeyCode.UP) {
                    direction = Dir.up;
                }
                if(key.getCode() == KeyCode.DOWN) {
                    direction = Dir.down;
                }
                if(key.getCode() == KeyCode.RIGHT) {
                    direction = Dir.right;
                }
                if(key.getCode() == KeyCode.LEFT) {
                    direction = Dir.left;
                }
                if(key.getCode() == KeyCode.ESCAPE) {
                    stage.close();

                }
            });

            //add start snake parts
            for (int i = 0; i < 3; i++) {
                snake.add(
                        new Corner(
                                playingField.getWidth() / 2,
                                playingField.getHeight() / 2
                        )
                );
            }

            stage.setScene(scene);
            stage.setTitle("SNAKE GAME");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //tick

    public static void tick(GraphicsContext gc) {
        if(gameOver) {
            gc.setFill(Color.RED);
            gc.setFont(new Font("COURIER", 50));

            gc.fillText(
                    "GAME OVER",
                    (playingField.getWidth() * CORNER_SIZE) / 3,
                    (playingField.getHeight() * CORNER_SIZE) / 2

            );
            return;
        }
        for(int i = snake.size() - 1; i >= 1; i--) {
            snake.get(i).x = snake.get(i-1).x;
            snake.get(i).y = snake.get(i-1).y;
        }
        switch (direction) {
            case up -> {
                snake.get(0).y--;
                if (snake.get(0).y < 0) {
                    gameOver = true;
                }
            }
            case down -> {
                snake.get(0).y++;
                if (snake.get(0).y > playingField.getHeight()) {
                    gameOver = true;
                }
            }
            case left -> {
                snake.get(0).x--;
                if (snake.get(0).x < 0) {
                    gameOver = true;
                }
            }
            case right -> {
                snake.get(0).x++;
                if (snake.get(0).x > playingField.getWidth()) {
                    gameOver = true;
                }
            }
        }

        //eat food
        if (food.getFoodX() == snake.get(0).x &&
                food.getFoodY() == snake.get(0).y) {
            snake.add(new Corner(-1, -1));
            newFood();
        }

        //self destroy
        for (int i = 1; i < snake.size(); i++) {
            if (snake.get(0).x == snake.get(i).x &&
                    snake.get(0).y == snake.get(i).y) {
                gameOver = true;
                break;
            }
        }

        //background
        gc.setFill(Color.BLACK);

        gc.fillRect(
                0,
                0,
                playingField.getWidth() * CORNER_SIZE,
                playingField.getHeight() * CORNER_SIZE
        );

        //score
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("MONOSPACED", 30));
        gc.fillText("Score: " + (foodCount * 5), 10, 35);

        //food
        gc.setFill(Color.DARKOLIVEGREEN);
        gc.fillRect(
                food.getFoodX() * CORNER_SIZE,
                food.getFoodY() * CORNER_SIZE,
                CORNER_SIZE - 1, CORNER_SIZE - 1
        );
        gc.setFill(Color.GREEN);

        gc.fillRect(
                food.getFoodX() * CORNER_SIZE,
                food.getFoodY() * CORNER_SIZE,
                CORNER_SIZE - 2,
                CORNER_SIZE - 2
        );

        //snake
        for(Corner c : snake) {
            gc.setFill(Color.DARKOLIVEGREEN);

            gc.fillRect(
                    c.x * CORNER_SIZE,
                    c.y * CORNER_SIZE,
                    CORNER_SIZE - 1,
                    CORNER_SIZE - 1
            );

            gc.setFill(Color.GREEN);

            gc.fillRect(
                    c.x * CORNER_SIZE,
                    c.y * CORNER_SIZE,
                    CORNER_SIZE - 2,
                    CORNER_SIZE - 2
            );
        }
        for (int i = 0; i < snake.size(); i++) {
            Corner c = snake.get(i);
            if (i > 0) {
                gc.setFill(Color.GREEN);

                gc.fillRect(
                        c.x * CORNER_SIZE,
                        c.y * CORNER_SIZE,
                        CORNER_SIZE - 1,
                        CORNER_SIZE - 1
                );

                gc.setFill(Color.DARKGREEN);

                gc.fillRect(
                        c.x * CORNER_SIZE,
                        c.y * CORNER_SIZE,
                        CORNER_SIZE - 2,
                        CORNER_SIZE - 2
                );

            } else {
                gc.setFill(Color.DARKOLIVEGREEN);

                gc.fillRect(
                        c.x * CORNER_SIZE,
                        c.y * CORNER_SIZE,
                        CORNER_SIZE - 1,
                        CORNER_SIZE - 1
                );

                gc.setFill(Color.GREEN);

                gc.fillRect(
                        c.x * CORNER_SIZE,
                        c.y * CORNER_SIZE,
                        CORNER_SIZE - 2,
                        CORNER_SIZE - 2
                );
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}