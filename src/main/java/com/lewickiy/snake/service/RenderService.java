package com.lewickiy.snake.service;

import com.lewickiy.snake.Game;
import com.lewickiy.snake.PlayingField;
import com.lewickiy.snake.entity.Corner;
import com.lewickiy.snake.entity.Food;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.List;

import static com.lewickiy.snake.configuration.ProjectConstants.CORNER_SIZE;
import static com.lewickiy.snake.configuration.ProjectConstants.FONT_TYPE;
import static com.lewickiy.snake.entity.Food.foodCount;

public class RenderService {

    private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final Color GAME_INFO_TEXT_COLOR = Color.WHITE;
    private static final Color GAME_OVER_TEXT_COLOR = Color.RED;
    private static final int GAME_OVER_TEXT_SIZE = 50;

    public void render(GraphicsContext gc, List<Corner> snake, Food food, PlayingField field, Game game) {

        renderBackground(gc, field);

        // score
        gc.setFill(GAME_INFO_TEXT_COLOR);
        gc.setFont(new Font(FONT_TYPE, 30));
        gc.fillText("SCORE: " + (foodCount * 5), 10, 35);

        // speed
        String speedText = "SPEED: " + game.getSpeed();
        double textWidth = gc.getFont().getSize() * speedText.length() * 0.6;
        gc.fillText(speedText, field.getWidth() * CORNER_SIZE - textWidth - 10, 35);

        // food
        gc.setFill(Color.DARKOLIVEGREEN);
        gc.fillRect(food.getFoodX() * CORNER_SIZE, food.getFoodY() * CORNER_SIZE, CORNER_SIZE - 1, CORNER_SIZE - 1);
        gc.setFill(Color.GREEN);
        gc.fillRect(food.getFoodX() * CORNER_SIZE, food.getFoodY() * CORNER_SIZE, CORNER_SIZE - 2, CORNER_SIZE - 2);

        // snake
        for (int i = 0; i < snake.size(); i++) {
            Corner c = snake.get(i);
            if (i == 0) {
                gc.setFill(Color.DARKOLIVEGREEN);
                gc.fillRect(c.x * CORNER_SIZE, c.y * CORNER_SIZE, CORNER_SIZE - 1, CORNER_SIZE - 1);
                gc.setFill(Color.GREEN);
                gc.fillRect(c.x * CORNER_SIZE, c.y * CORNER_SIZE, CORNER_SIZE - 2, CORNER_SIZE - 2);
            } else {
                gc.setFill(Color.GREEN);
                gc.fillRect(c.x * CORNER_SIZE, c.y * CORNER_SIZE, CORNER_SIZE - 1, CORNER_SIZE - 1);
                gc.setFill(Color.DARKGREEN);
                gc.fillRect(c.x * CORNER_SIZE, c.y * CORNER_SIZE, CORNER_SIZE - 2, CORNER_SIZE - 2);
            }
        }
    }

    public void renderGameOver(GraphicsContext gc, PlayingField field) {
        gc.setFill(GAME_OVER_TEXT_COLOR);
        gc.setFont(new Font(FONT_TYPE, GAME_OVER_TEXT_SIZE));
        gc.fillText(
                "GAME OVER",
                (double) (field.getWidth() * CORNER_SIZE) / 3,
                (double) (field.getHeight() * CORNER_SIZE) / 2
        );
    }

    private void renderBackground(GraphicsContext gc, PlayingField field) {
        gc.setFill(BACKGROUND_COLOR);
        gc.fillRect(0, 0, field.getWidth() * CORNER_SIZE, field.getHeight() * CORNER_SIZE);
    }
}
