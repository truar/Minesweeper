package com.zenikatas.dev.minesweeper.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class RandomGridGenerator {

    private Map<Position, Boolean> positionCouple = new HashMap<>();  // TODO what does "couple" mean? Why not a set of positions?

    public String[][] generate(int rowLength, int colLength, int mineNumber) { // TODO Rename rowCount / colCount / mineCount
        String[][] grid = new String[rowLength][colLength];

        for (int i = 0; i < grid.length; i++) { // TODO Use rowLength?
            for (int j = 0; j < grid[0].length; j++) { // TODO Use colLength?
                grid[i][j] = "";
            }
        }

        // TODO infinite loop if mineNumber > rowLength * colLength ?
        // TODO what is the execution time if rowLength/colLength is large and mineNumber is close to rowLength * colLength ?
        // TODO wouldn't it be easier to generate random positions for mine between 0 and rowLength * colLength and split into rows afterwards?
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

    // TODO I think this whole class can be replaced with the following:
//    public String[][] generate(int rowLength, int colLength, int mineNumber) {
//        String[][] grid = new String[rowLength][colLength];
//        List<Integer> cells = Stream.iterate(0, v -> v + 1).limit((long) rowLength * colLength).collect(toList());
//        Collections.shuffle(cells);
//        Set<Integer> mineCells = new HashSet<>(cells.subList(0, mineNumber));
//        for (int i = 0; i < rowLength * colLength; i++) {
//            grid[i / colLength][i % colLength] = mineCells.contains(i) ? "X" : "";
//        }
//        return grid;
//    }

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
