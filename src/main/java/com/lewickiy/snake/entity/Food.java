package com.lewickiy.snake.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

import static com.lewickiy.snake.SnakeApplication.food;
import static com.lewickiy.snake.SnakeApplication.game;
import static com.lewickiy.snake.SnakeApplication.playingField;
import static com.lewickiy.snake.SnakeApplication.snake;

@Getter
@Setter
public class Food {
    public static Random rand = new Random();
    public static int foodCount = -1;
    private int foodX;
    private int foodY;

    public Food(int startPosition) {
        this.foodX = startPosition;
        this.foodY = startPosition;
    }

    public static void newFood() {
        start:while (true) {
            food.setFoodX(rand.nextInt(playingField.getWidth()));
            food.setFoodY(rand.nextInt(playingField.getHeight()));
            for(Corner c:snake) {
                if(c.x == food.getFoodX() && c.y == food.getFoodY()) {
                    continue start;
                }
            }
            if (foodCount % 5 == 0 && game.getSpeed() > 1) {
                game.setSpeed(game.getSpeed() + 1);
            }
            foodCount++;
            break;
        }
    }
}
