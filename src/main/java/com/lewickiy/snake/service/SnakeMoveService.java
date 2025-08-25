package com.lewickiy.snake.service;

import com.lewickiy.snake.entity.Corner;
import com.lewickiy.snake.enumeration.Direction;

import java.util.List;
/**
 * Service responsible for handling snake movement.
 */
public class SnakeMoveService {

    /**
     * Updates the positions of the snake segments according to the given {@link Direction}.
     * The head moves in the specified direction, and each subsequent segment
     * follows the previous segment's position.
     *
     * @param snake the list of {@link Corner} objects representing the snake
     * @param direction the new direction for the snake's head
     */
    public static void moveSnake(List<Corner> snake, Direction direction) {
        for (int i = snake.size() - 1; i >= 1; i--) {
            snake.get(i).x = snake.get(i - 1).x;
            snake.get(i).y = snake.get(i - 1).y;
        }
        switch (direction) {
            case UP -> snake.getFirst().y--;
            case DOWN -> snake.getFirst().y++;
            case LEFT -> snake.getFirst().x--;
            case RIGHT -> snake.getFirst().x++;
        }
    }
}
