package com.zenikatas.dev.minesweeper.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RandomGridGeneratorTest {

    @Test
    void generates_random_grid() {
        for (int row = 2; row <= 10; row++) { // TODO tests in a loop? Please, this is not Golang...
            for (int col = 2; col <= 10; col++) {
                for (int mineNumber = 1; mineNumber < (row * col) / 2; mineNumber++) {
                    String[][] grid = new RandomGridGenerator().generate(row, col, mineNumber);
                    MineSweeper game = new MineSweeper(grid);
                    assertThat(game.rowsLength()).isEqualTo(row);
                    assertThat(game.columnsLength()).isEqualTo(col);
                    assertThat(game.numberOfMine()).isEqualTo(mineNumber);
                }
            }
        }
    }
}
