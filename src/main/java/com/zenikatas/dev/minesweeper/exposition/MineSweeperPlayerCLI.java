package com.zenikatas.dev.minesweeper.exposition;

import com.zenikatas.dev.minesweeper.domain.Cell;
import com.zenikatas.dev.minesweeper.domain.MineSweeper;

import java.util.Scanner;

public class MineSweeperPlayerCLI {
    private final MineSweeper mineSweeper;
    private Scanner scanner;

    public MineSweeperPlayerCLI(String[][] grid, Scanner scanner) {
        mineSweeper = new MineSweeper(grid);
        this.scanner = scanner;
    }

    public void runGame() {
        while (!mineSweeper.isTerminated()) {
            displayGrid();
            System.out.print("Enter row index (between 0 and " + (mineSweeper.rowsLength() - 1) + "): ");
            int row = scanner.nextInt();
            System.out.print("Enter column index (between 0 and " + (mineSweeper.columnsLength() - 1) + "): ");
            int col = scanner.nextInt();
            mineSweeper.uncoverCellAt(row, col);
        }
        endGame();
    }

    private void displayGrid() {
        for (int i = 0; i < mineSweeper.rowsLength(); i++) {
            for (int j = 0; j < mineSweeper.columnsLength(); j++) {
                Cell cell = mineSweeper.cellAt(i, j);
                if (cell.isUncovered()) { // TODO this behavior should be encapsulated inside the cell -- no "if" here
                    System.out.print(cell.value().getValue());
                } else {
                    System.out.print("?");
                }
                if (j < mineSweeper.columnsLength() - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
        }
    }

    private void endGame() {
        if(mineSweeper.isWon()) {
            System.out.println("Congratulations, you won !!");
        } else {
            System.out.println("Sorry, you lost !!");
        }

        mineSweeper.revealMines();
        displayGrid();
    }
}
