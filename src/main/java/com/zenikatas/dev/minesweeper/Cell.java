package com.zenikatas.dev.minesweeper;

public class Cell {
    private String value;

    public Cell(String initialValue) {
        this.value = initialValue;
    }

    public boolean isUncovered() {
        return !isCovered();
    }

    public boolean isCovered() {
        return value.equals("");
    }

    public void updateValue(int countNumberOfAdjacentMine) {
        value = countNumberOfAdjacentMine + "";
    }

    public boolean isAMine() {
        return value.equals("X");
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return "{" + value + "}";
    }
}
