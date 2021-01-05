package com.zenikatas.dev.minesweeper.domain;

public class Cell {
    private CellValue value;
    private boolean isUncovered = false; // TODO uncovered?

    public Cell(String initialValue) { // TODO why not pass the CellValue here?
        this.value = CellValue.of(initialValue);
    }

    public boolean isUncovered() {
        return isUncovered;
    }

    public boolean isCovered() {
        return !isUncovered;
    } // TODO why two methods?

    public void uncover(int countNumberOfAdjacentMine) {
        value = CellValue.of(countNumberOfAdjacentMine); // TODO it troubles me that the outside world tells what to display inside the cell. This should be encapsulated in the CellValue somehow?
        isUncovered = true;
    }

    public void reveal() {
        isUncovered = true;
    }

    public boolean isAMine() {
        return value == CellValue.MINED;
    } // TODO can we encapsulate the caller behavior inside CellValue ?

    public CellValue value() {
        return value;
    }

    @Override
    public String toString() {
        return "{" + value + "}";
    }
}
