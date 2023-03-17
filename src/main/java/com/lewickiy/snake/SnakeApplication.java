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
import java.util.Random;

import static com.lewickiy.snake.Corner.CORNER_SIZE;

public class SnakeApplication extends Application {

    static int speed = 5;
    static int foodColor = 0;
    static int width = 20;
    static int height = 20;
    static int foodX = 0;
    static int foodY = 0;
    static List<Corner> snake = new ArrayList<>();
    static Dir direction = Dir.left;
    static boolean gameOver = false;
    static Random rand = new Random();

    @Override
    public void start(Stage stage) {
        try {
            newFood();

            VBox root = new VBox();
            Canvas c = new Canvas(width * CORNER_SIZE, height * CORNER_SIZE);
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
                    if(now - lastTick > 1000000000 / speed) {
                        lastTick = now;
                        tick(gc);
                    }
                }
            }.start();

            Scene scene = new Scene(root, width * CORNER_SIZE, height * CORNER_SIZE);

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
            });

            //add start snake parts
            snake.add(new Corner(width / 2, height / 2));
            snake.add(new Corner(width / 2, height / 2));
            snake.add(new Corner(width / 2, height / 2));

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
            gc.setFont(new Font("", 50));
            gc.fillText("GAME OVER", 100, 250);
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
                if (snake.get(0).y > height) {
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
                if (snake.get(0).x > width) {
                    gameOver = true;
                }
            }
        }

        //eat food
        if (foodX == snake.get(0).x && foodY == snake.get(0).y) {
            snake.add(new Corner(-1, -1));
            newFood();
        }

        //self destroy
        for (int i = 1; i < snake.size(); i++) {
            if (snake.get(0).x == snake.get(i).x && snake.get(0).y == snake.get(i).y) {
                gameOver = true;
                break;
            }
        }
        //fill
        //background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width * CORNER_SIZE, height * CORNER_SIZE);

        //score
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("", 30));
        gc.fillText("Score: " + (speed - 6), 10, 30);

        //random food color
        Color cc = switch (foodColor) {
            case 0 -> Color.PURPLE;
            case 1 -> Color.LIGHTBLUE;
            case 2 -> Color.YELLOW;
            case 3 -> Color.PINK;
            case 4 -> Color.ORANGE;
            default -> Color.WHITE;
        };

        gc.setFill(cc);
        gc.fillOval(foodX * CORNER_SIZE, foodY * CORNER_SIZE, CORNER_SIZE, CORNER_SIZE);

        //snake
        for(Corner c : snake) {
            gc.setFill(Color.LIGHTGREEN);
            gc.fillRect(c.x * CORNER_SIZE, c.y * CORNER_SIZE, CORNER_SIZE - 1, CORNER_SIZE - 1);
            gc.setFill(Color.GREEN);
            gc.fillRect(c.x * CORNER_SIZE, c.y * CORNER_SIZE, CORNER_SIZE - 2, CORNER_SIZE - 2);
        }
    }

    //food
    public static void newFood() {
        start:while (true) {
            foodX = rand.nextInt(width);
            foodY = rand.nextInt(height);

            for(Corner c:snake) {
                if(c.x == foodX && c.y == foodY) {
                    continue start;
                }
            }
            foodColor = rand.nextInt(5);
            speed++;
            break;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}