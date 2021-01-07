package com.zenikatas.dev.minesweeper.exposition;

import com.zenikatas.dev.minesweeper.domain.RandomGridGenerator;

import java.io.InputStreamReader;
import java.util.Scanner;

public class MineSweeperCLI {

    private final Scanner scanner = new Scanner(new InputStreamReader(System.in));
    private int colLength;
    private int rowLength;
    private int mineNumber;

    public void run() {
        displayIntroductionMessage();
        askForGridSize();
        
        String[][] grid = new RandomGridGenerator().generate(rowLength, colLength, mineNumber);
        
        MineSweeperPlayerCLI mineSweeperPlayerCLI = new MineSweeperPlayerCLI(grid, scanner);
        mineSweeperPlayerCLI.runGame();
    }

    private void displayIntroductionMessage() {
        System.out.println("Welcome to the MineSweeper Game !!!!");
        System.out.println();
    }

    private void askForGridSize() {
        System.out.print("Please enter the number of rows: ");
        rowLength = scanner.nextInt();
        System.out.print("Please enter the number of columns: ");
        colLength = scanner.nextInt();
        System.out.print("Please enter the number of mines: ");
        mineNumber = scanner.nextInt();
        System.out.println();
    }
}
