package com.lewickiy.snake;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayingField {
    private final int width;
    private final int height;

    public PlayingField(int size) {
        if (size < 20 || size > 30) {
            this.width = 20;
            this.height = 20;
        } else {
            this.width = size;
            this.height = size;
        }
    }
}
