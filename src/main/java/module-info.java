module com.lewickiy.snake {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires static lombok;

    requires junit;
    requires java.logging;

    opens com.lewickiy.snake to javafx.fxml;
    exports com.lewickiy.snake;
    exports com.lewickiy.snake.service;
    opens com.lewickiy.snake.service to javafx.fxml;
    exports com.lewickiy.snake.enumeration;
    opens com.lewickiy.snake.enumeration to javafx.fxml;
    exports com.lewickiy.snake.entity;
    opens com.lewickiy.snake.entity to javafx.fxml;
}