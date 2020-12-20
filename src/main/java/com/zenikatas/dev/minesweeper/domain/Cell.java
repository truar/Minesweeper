package com.zenikatas.dev.minesweeper.domain;

public class Cell {
    private CellValue value;
    private boolean isUncovered = false;

    public Cell(String initialValue) {
        this.value = CellValue.of(initialValue);
    }

    public boolean isUncovered() {
        return isUncovered;
    }

    public boolean isCovered() {
        return !isUncovered;
    }

    public void uncover(int countNumberOfAdjacentMine) {
        value = CellValue.of(countNumberOfAdjacentMine);
        isUncovered = true;
    }

    public void reveal() {
        isUncovered = true;
    }

    public boolean isAMine() {
        return value == CellValue.MINED;
    }

    public CellValue value() {
        return value;
    }

    @Override
    public String toString() {
        return "{" + value + "}";
    }
}
