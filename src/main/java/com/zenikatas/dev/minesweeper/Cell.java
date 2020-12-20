package com.zenikatas.dev.minesweeper;

public class Cell {
    private CellValue value;

    public Cell(String initialValue) {
        this.value = CellValue.of(initialValue);
    }

    public boolean isUncovered() {
        return !isCovered();
    }

    public boolean isCovered() {
        return value == CellValue.COVERED;
    }

    public void updateValue(int countNumberOfAdjacentMine) {
        value = CellValue.of(countNumberOfAdjacentMine);
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
