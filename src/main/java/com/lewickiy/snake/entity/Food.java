package com.lewickiy.snake.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

import static com.lewickiy.snake.SnakeApplication.food;
import static com.lewickiy.snake.SnakeApplication.game;
import static com.lewickiy.snake.SnakeApplication.playingField;
import static com.lewickiy.snake.SnakeApplication.snake;

/**
 * Class representing Food for the Snake on the Game Field.
 * Responsible for storing food coordinates and generating new positions,
 * as well as managing game speed increase when food is eaten.
 */
@Getter
@Setter
public class Food {

    public static Random rand = new Random();

    public static int foodCount = -1;
    private int foodX;
    private int foodY;

    /**
     * Constructor that creates food at the specified starting position.
     *
     * @param foodX initial X position of the food
     * @param foodY initial Y position of the food
     */
    public Food(int foodX, int foodY) {
        this.foodX = foodX;
        this.foodY = foodY;
    }

    /**
     * Generates a new position for the food on the game field.
     * Ensures that the food does not appear on the snake.
     * If the number of eaten food items is a multiple of 5, increases the game speed.
     */
    public static void newFood() {
        start: while (true) {
            food.setFoodX(rand.nextInt(playingField.getWidth()));
            food.setFoodY(rand.nextInt(playingField.getHeight()));
            for (Corner c : snake) {
                if (c.x == food.getFoodX() && c.y == food.getFoodY()) {
                    continue start;
                }
            }
            if (foodCount % 5 == 0 && game.getSpeed() > 1) {
                int newSpeed = game.incrementSpeed();
                game.setSpeed(newSpeed);
            }
            foodCount++;

            break;
        }
    }
}
