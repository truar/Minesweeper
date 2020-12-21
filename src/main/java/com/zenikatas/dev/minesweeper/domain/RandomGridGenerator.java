package com.zenikatas.dev.minesweeper.domain;

import java.util.*;

public class RandomGridGenerator {

    private Map<Position, Boolean> positionCouple = new HashMap<>();

    public String[][] generate(int rowLength, int colLength, int mineNumber) {
        String[][] grid = new String[rowLength][colLength];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = "";
            }
        }

        Random random = new Random();
        for (int i = 1; i <= mineNumber; i++) {
            int randomRow = 0;
            int randomCol = 0;
            while (true) {
                randomRow = random.nextInt(rowLength);
                randomCol = random.nextInt(colLength);

                if (coupleRowColumnIsUnique(randomRow, randomCol)) {
                    positionCouple.put(new Position(randomRow, randomCol), true);
                    grid[randomRow][randomCol] = "X";
                    break;
                }
            }
        }

        return grid;
    }

    private boolean coupleRowColumnIsUnique(int randomRow, int randomCol) {
        return !positionCouple.containsKey(new Position(randomRow, randomCol));
    }

    private class Position {
        private final int randomRow;
        private final int randomCol;

        public Position(int randomRow, int randomCol) {

            this.randomRow = randomRow;
            this.randomCol = randomCol;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return randomRow == position.randomRow &&
                    randomCol == position.randomCol;
        }

        @Override
        public int hashCode() {
            return Objects.hash(randomRow, randomCol);
        }
    }
}
