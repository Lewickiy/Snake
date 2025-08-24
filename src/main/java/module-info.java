module com.lewickiy.snake {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires static lombok;

    requires junit;

    opens com.lewickiy.snake to javafx.fxml;
    exports com.lewickiy.snake;
}