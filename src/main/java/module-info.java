module com.lewickiy.snake {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    opens com.lewickiy.snake to javafx.fxml;
    exports com.lewickiy.snake;
}