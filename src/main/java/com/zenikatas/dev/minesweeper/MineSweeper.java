package com.zenikatas.dev.minesweeper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MineSweeper {
    private final Cell[][] grid;

    public MineSweeper(String[][] grid) {
        this.grid = new Cell[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                this.grid[i][j] = new Cell(grid[i][j]);
            }
        }
    }

    public boolean isWon() {
        return allCells()
                .allMatch(Cell::isUncovered);
    }

    private Stream<Cell> allCells() {
        return Arrays.stream(grid)
                .flatMap(Arrays::stream);
    }

    public Cell cellAt(int y, int x) {
        return grid[y][x];
    }

    public void uncoverCellAt(int y, int x) {
        if (cellAt(y, x).isCovered()) {
            uncoverAdjacentCells(y, x);
        }
    }


    private void uncoverAdjacentCells(int y, int x) {
        if (isCellOnTheEdge(y, x)) {
            return;
        }
        if (cellAt(y, x).isUncovered()) {
            return;
        }

        cellAt(y, x).updateValue(countNumberOfAdjacentMine(y, x));

        if (isAdjacentCellsContainAMine(y, x)) {
            return;
        }
        uncoverAdjacentCells(y - 1, x - 1);
        uncoverAdjacentCells(y - 1, x);
        uncoverAdjacentCells(y - 1, x + 1);
        uncoverAdjacentCells(y, x - 1);
        uncoverAdjacentCells(y, x + 1);
        uncoverAdjacentCells(y + 1, x - 1);
        uncoverAdjacentCells(y + 1, x);
        uncoverAdjacentCells(y + 1, x + 1);
    }

    private int countNumberOfAdjacentMine(int y, int x) {
        return (int) getAdjacentCells(y, x).stream()
                .filter(Cell::isAMine)
                .count();
    }

    private boolean isAdjacentCellsContainAMine(int y, int x) {
        return getAdjacentCells(y, x).stream()
                .anyMatch(Cell::isAMine);
    }

    private List<Cell> getAdjacentCells(int y, int x) {
        List<Cell> adjacentCells = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (isCellOnTheEdge(y + i, x + j)) {
                    continue;
                }
                adjacentCells.add(cellAt(y + i, x + j));
            }
        }
        return adjacentCells;
    }

    private int getRowsLength() {
        return grid.length;
    }

    private boolean isCellOnTheEdge(int y, int x) {
        return x < 0
                || y < 0
                || x >= getColumnsLength()
                || y >= getRowsLength();
    }

    private int getColumnsLength() {
        return grid[0].length;
    }

    public boolean isTerminated() {
        return false;
    }
}
