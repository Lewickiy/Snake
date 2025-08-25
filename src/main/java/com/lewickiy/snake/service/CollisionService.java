package com.lewickiy.snake.service;

import com.lewickiy.snake.entity.Corner;
import com.lewickiy.snake.entity.Food;
import com.lewickiy.snake.entity.PlayingField;

import java.util.List;

public class CollisionService {
    private final PlayingField field;

    public CollisionService(PlayingField field) {
        this.field = field;
    }

    public boolean checkWallCollision(Corner head) {
        return head.x < 0 || head.y < 0 || head.x >= field.getWidth() || head.y >= field.getHeight();
    }

    public boolean checkSelfCollision(List<Corner> snake) {
        Corner head = snake.getFirst();
        for (int i = 1; i < snake.size(); i++) {
            if (head.x == snake.get(i).x && head.y == snake.get(i).y) return true;
        }
        return false;
    }

    public boolean checkFoodCollision(Corner head, Food food) {
        return head.x == food.getFoodX() && head.y == food.getFoodY();
    }
}
