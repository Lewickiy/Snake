package com.lewickiy.snake;

import lombok.Getter;

import lombok.Setter;

import java.util.Random;

import static com.lewickiy.snake.SnakeApplication.*;

@Getter
@Setter
public class Food {
    static Random rand = new Random();
    static int foodCount = -1;
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
            if (foodCount%5 == 0 && game.getSpeed() > 1) {
                game.setSpeed(game.getSpeed() + 1);
            }
            foodCount++;
            break;
        }
    }
}
