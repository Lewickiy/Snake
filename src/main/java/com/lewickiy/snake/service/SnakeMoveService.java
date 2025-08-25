package com.lewickiy.snake.service;

import com.lewickiy.snake.entity.Corner;
import com.lewickiy.snake.enumeration.Direction;

import java.util.List;

/**
 * Сервис движения змеи
 */
public class SnakeMoveService {

    /**
     * Обеспечивает изменение направления движения Змеи, в зависимости от полученного значения {@link Direction}
     * @param snake змея, для которой происходит изменение направления движения
     * @param direction обновлённое направление движения
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
