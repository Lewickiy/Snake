package com.lewickiy.snake;

import com.lewickiy.snake.entity.Corner;
import com.lewickiy.snake.entity.Food;
import com.lewickiy.snake.entity.Game;
import com.lewickiy.snake.entity.PlayingField;
import com.lewickiy.snake.service.CollisionService;
import com.lewickiy.snake.service.RenderService;
import com.lewickiy.snake.service.SnakeMoveService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.lewickiy.snake.configuration.ProjectConstants.APPLICATION_TITLE;
import static com.lewickiy.snake.configuration.ProjectConstants.CORNER_SIZE;
import static com.lewickiy.snake.configuration.ProjectConstants.FIELD_SIZE;
import static com.lewickiy.snake.configuration.ProjectConstants.INITIAL_SNAKE_LENGTH;
import static com.lewickiy.snake.configuration.ProjectConstants.INITIAL_SPEED;
import static com.lewickiy.snake.configuration.ProjectConstants.direction;
import static com.lewickiy.snake.entity.Food.newFood;

public class SnakeApplication extends Application {

    public static PlayingField playingField = new PlayingField(FIELD_SIZE);
    public static Food food = new Food(0, 0);
    public static List<Corner> snake = new ArrayList<>();
    public static Game game = new Game(INITIAL_SPEED);

    private static final CollisionService collisionService = new CollisionService(playingField);
    private static final Logger logger = Logger.getLogger(SnakeApplication.class.getName());
    private static final RenderService renderService = new RenderService();
    static boolean gameOver = false;

    public static void tick(GraphicsContext gc) {
        if (gameOver) {
            renderService.renderGameOver(gc, playingField);
            return;
        }

        SnakeMoveService.moveSnake(snake, direction);

        // проверка столкновений
        if (collisionService.checkWallCollision(snake.getFirst()) ||
            collisionService.checkSelfCollision(snake)) {
            gameOver = true;
        }

        if (collisionService.checkFoodCollision(snake.getFirst(), food)) {
            snake.add(new Corner(-1, -1));
            newFood();
        }

        renderService.render(gc, snake, food, playingField, game);
    }

    @Override
    public void start(Stage stage) {
        try {
            newFood();
            VBox root = new VBox();

            Canvas c = createCanvas();

            GraphicsContext gc = c.getGraphicsContext2D();
            root.getChildren().add(c);

            // запуск игрового цикла
            new GameLoop(gc).start();

            Scene scene = createScene(c, stage);
            initSnake();

            stage.setScene(scene);
            stage.setTitle(APPLICATION_TITLE);
            stage.show();
        } catch (Exception e) {
            logger.warning(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }

    private void initSnake() {
        for (int i = 0; i < INITIAL_SNAKE_LENGTH; i++) {
            snake.add(new Corner(
                    playingField.getWidth() / 2,
                    playingField.getHeight() / 2
            ));
        }
    }

    private Canvas createCanvas() {
        return new Canvas(
                playingField.getWidth() * CORNER_SIZE,
                playingField.getHeight() * CORNER_SIZE
        );
    }

    private Scene createScene(Canvas canvas, Stage stage) {
        VBox root = new VBox(canvas);
        Scene scene = new Scene(root,
                playingField.getWidth() * CORNER_SIZE,
                playingField.getHeight() * CORNER_SIZE
        );
        scene.addEventFilter(KeyEvent.KEY_PRESSED, new SnakeKeyHandler(stage));
        logger.info("Scene created");
        return scene;
    }
}
