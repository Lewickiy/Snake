package com.lewickiy.snake;

import lombok.Getter;
@Getter
public class Game {
    private int speed = 1;

    public Game(int speed) {
        setSpeed(speed);
    }

    public void setSpeed(int speed) {
        this.speed = Math.max(speed, 1);
    }
}
