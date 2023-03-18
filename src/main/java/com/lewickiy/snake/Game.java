package com.lewickiy.snake;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Game {
    private int speed = 1;

    public Game(int speed) {
        if (speed < 1) {
            this.speed = 1;
        } else {
            this.speed = speed;
        }
    }

    public void setSpeed(int speed) {
        if (speed < 1) {
            this.speed = 1;
        } else {
            this.speed = speed;
        }
    }

}
