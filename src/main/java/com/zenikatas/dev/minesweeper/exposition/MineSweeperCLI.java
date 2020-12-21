package com.zenikatas.dev.minesweeper.exposition;

import com.zenikatas.dev.minesweeper.domain.Cell;
import com.zenikatas.dev.minesweeper.domain.MineSweeper;
import com.zenikatas.dev.minesweeper.domain.RandomGridGenerator;

import java.io.InputStreamReader;
import java.util.Scanner;

public class MineSweeperCLI {

    private Scanner scanner = new Scanner(new InputStreamReader(System.in));
    private int colLength;
    private int rowLength;
    private int mineNumber;
    private MineSweeper mineSweeper;

    public void run() {
        displayIntroductionMessage();
        askForGridSize();

        initializeGame();

        runGame();

        endGame();
    }

    private void displayIntroductionMessage() {
        System.out.println("Welcome to the MineSweeper Game !!!!");
        System.out.println();
    }

    private void askForGridSize() {
        System.out.print("Please enter the row length: ");
        rowLength = scanner.nextInt();
        System.out.println();
        System.out.print("Please enter the column length: ");
        colLength = scanner.nextInt();
        System.out.println();
        System.out.print("Please enter the mine number: ");
        mineNumber = scanner.nextInt();
    }

    private void initializeGame() {
        String[][] grid = new RandomGridGenerator().generate(rowLength, colLength, mineNumber);
        mineSweeper = new MineSweeper(grid);
    }

    private void runGame() {
        while (!mineSweeper.isTerminated()) {
            displayGrid();
            System.out.print("Enter row index (between 0 and " + (rowLength - 1) + "): ");
            int row = scanner.nextInt();
            System.out.print("Enter column index (between 0 and " + (colLength - 1) + "): ");
            int col = scanner.nextInt();
            mineSweeper.uncoverCellAt(row, col);
        }
    }

    private void displayGrid() {
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                Cell cell = mineSweeper.cellAt(i, j);
                if (cell.isUncovered()) {
                    System.out.print(cell.value().getValue());
                } else {
                    System.out.print("?");
                }
                if (j < colLength - 1) {
                    System.out.print(" , ");
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

        mineSweeper.reveal();
        displayGrid();

    }
}
