package com.lewickiy.snake;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Corner {
    static final int CORNER_SIZE = 25;
    int x;
    int y;
}
