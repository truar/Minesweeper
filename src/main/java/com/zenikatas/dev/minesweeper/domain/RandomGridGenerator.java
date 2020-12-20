package com.zenikatas.dev.minesweeper.domain;

import java.util.Random;

public class RandomGridGenerator {

    public static String[][] generate(int rowLength, int colLength, int mineNumber) {
        String[][] grid = new String[rowLength][colLength];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = "";
            }
        }

        Random random = new Random();
        for(int i = 1; i <= mineNumber; i++) {
            int randomRow = random.nextInt(rowLength);
            int randomCol = random.nextInt(colLength);

            grid[randomRow][randomCol] = "X";
        }

        return grid;
    }
}
