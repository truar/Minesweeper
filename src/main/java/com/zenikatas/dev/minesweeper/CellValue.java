package com.zenikatas.dev.minesweeper;

public enum CellValue {

    COVERED(""),
    MINED("X"),
    MINE_ADJACENT_0("0"),
    MINE_ADJACENT_1("1"),
    MINE_ADJACENT_2("2"),
    MINE_ADJACENT_3("3"),
    MINE_ADJACENT_4("4"),
    MINE_ADJACENT_5("5"),
    MINE_ADJACENT_6("6"),
    MINE_ADJACENT_7("7"),
    MINE_ADJACENT_8("8");


    private String value;

    CellValue(String value) {
        this.value = value;
    }

    public static CellValue of(String value) {
        for(CellValue cellValue : CellValue.values()) {
            if(cellValue.value.equals(value)) {
                return cellValue;
            }
        }
        throw new IllegalArgumentException("Value " + value + " is not allowed");
    }

    public static CellValue of(int value) {
        return CellValue.of(value + "");
    }
}
