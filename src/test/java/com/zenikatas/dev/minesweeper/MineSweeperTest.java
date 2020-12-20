package com.zenikatas.dev.minesweeper;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MineSweeperTest {

    @Test
    void win_a_game_with_a_single_empty_cell() {
        String[][] grid = {{""}};
        MineSweeper mineSweeper = new MineSweeper(grid);

        mineSweeper.uncoverCellAt(0, 0);

        assertThat(mineSweeper.isWon()).isEqualTo(true);
        assertThat(mineSweeper.cellAt(0, 0).value()).isEqualTo("0");
    }

    @Test
    void uncovering_a_cell_uncovers_adjacent_cells_if_there_is_no_mine() {
        String[][] grid = {
                {"", "", ""},
                {"", "", ""},
                {"", "", ""}
        };
        MineSweeper mineSweeper = new MineSweeper(grid);

        mineSweeper.uncoverCellAt(1, 1);

        assertThat(mineSweeper.isWon()).isEqualTo(true);
        assertThat(mineSweeper.cellAt(0, 0).value()).isEqualTo("0");
        assertThat(mineSweeper.cellAt(0, 1).value()).isEqualTo("0");
        assertThat(mineSweeper.cellAt(0, 2).value()).isEqualTo("0");
        assertThat(mineSweeper.cellAt(1, 0).value()).isEqualTo("0");
        assertThat(mineSweeper.cellAt(1, 1).value()).isEqualTo("0");
        assertThat(mineSweeper.cellAt(1, 2).value()).isEqualTo("0");
        assertThat(mineSweeper.cellAt(2, 0).value()).isEqualTo("0");
        assertThat(mineSweeper.cellAt(2, 1).value()).isEqualTo("0");
        assertThat(mineSweeper.cellAt(2, 2).value()).isEqualTo("0");
    }

    @Test
    void uncovering_a_cell_uncovers_adjacent_cells_if_there_if_no_mine_and_so_on() {
        String[][] grid = {
                {"", "", "", ""},
                {"", "", "", ""},
                {"", "", "", ""}
        };
        MineSweeper mineSweeper = new MineSweeper(grid);

        mineSweeper.uncoverCellAt(1, 1);

        assertThat(mineSweeper.isWon()).isEqualTo(true);
        assertThat(mineSweeper.cellAt(0, 0).value()).isEqualTo("0");
        assertThat(mineSweeper.cellAt(0, 1).value()).isEqualTo("0");
        assertThat(mineSweeper.cellAt(0, 2).value()).isEqualTo("0");
        assertThat(mineSweeper.cellAt(0, 3).value()).isEqualTo("0");
        assertThat(mineSweeper.cellAt(1, 0).value()).isEqualTo("0");
        assertThat(mineSweeper.cellAt(1, 1).value()).isEqualTo("0");
        assertThat(mineSweeper.cellAt(1, 2).value()).isEqualTo("0");
        assertThat(mineSweeper.cellAt(1, 3).value()).isEqualTo("0");
        assertThat(mineSweeper.cellAt(2, 0).value()).isEqualTo("0");
        assertThat(mineSweeper.cellAt(2, 1).value()).isEqualTo("0");
        assertThat(mineSweeper.cellAt(2, 2).value()).isEqualTo("0");
        assertThat(mineSweeper.cellAt(2, 3).value()).isEqualTo("0");
    }

    @Test
    void uncovering_a_cell_does_not_uncover_adjacent_cells_if_there_is_a_mine_adjacent() {
        String[][] grid = {
                {"", "", "", ""},
                {"X", "", "", ""},
                {"", "", "", ""}
        };
        MineSweeper mineSweeper = new MineSweeper(grid);

        mineSweeper.uncoverCellAt(1, 1);

        assertThat(mineSweeper.isWon()).isEqualTo(false);
        assertThat(mineSweeper.cellAt(0, 0).value()).isEqualTo("");
        assertThat(mineSweeper.cellAt(0, 1).value()).isEqualTo("");
        assertThat(mineSweeper.cellAt(0, 2).value()).isEqualTo("");
        assertThat(mineSweeper.cellAt(0, 3).value()).isEqualTo("");
        assertThat(mineSweeper.cellAt(1, 0).value()).isEqualTo("X");
        assertThat(mineSweeper.cellAt(1, 1).value()).isEqualTo("1");
        assertThat(mineSweeper.cellAt(1, 2).value()).isEqualTo("");
        assertThat(mineSweeper.cellAt(1, 3).value()).isEqualTo("");
        assertThat(mineSweeper.cellAt(2, 0).value()).isEqualTo("");
        assertThat(mineSweeper.cellAt(2, 1).value()).isEqualTo("");
        assertThat(mineSweeper.cellAt(2, 2).value()).isEqualTo("");
        assertThat(mineSweeper.cellAt(2, 3).value()).isEqualTo("");
    }

    @Test
    void uncovering_a_cell_does_uncover_adjacent_cells_if_there_is_no_mine_adjacent() {
        String[][] grid = {
                {"", "", "", ""},
                {"X", "", "", ""},
                {"", "", "", ""}
        };
        MineSweeper mineSweeper = new MineSweeper(grid);

        mineSweeper.uncoverCellAt(1, 2);

        assertThat(mineSweeper.isWon()).isEqualTo(false);
        assertThat(mineSweeper.cellAt(0, 0).value()).isEqualTo("");
        assertThat(mineSweeper.cellAt(0, 1).value()).isEqualTo("1");
        assertThat(mineSweeper.cellAt(0, 2).value()).isEqualTo("0");
        assertThat(mineSweeper.cellAt(0, 3).value()).isEqualTo("0");
        assertThat(mineSweeper.cellAt(1, 0).value()).isEqualTo("X");
        assertThat(mineSweeper.cellAt(1, 1).value()).isEqualTo("1");
        assertThat(mineSweeper.cellAt(1, 2).value()).isEqualTo("0");
        assertThat(mineSweeper.cellAt(1, 3).value()).isEqualTo("0");
        assertThat(mineSweeper.cellAt(2, 0).value()).isEqualTo("");
        assertThat(mineSweeper.cellAt(2, 1).value()).isEqualTo("1");
        assertThat(mineSweeper.cellAt(2, 2).value()).isEqualTo("0");
        assertThat(mineSweeper.cellAt(2, 3).value()).isEqualTo("0");
    }

    @Test
    void uncovering_a_cell_indicates_number_of_adjacent_mine() {
        String[][] grid = {
                {"X", "X", "X"},
                {"X", "", "X"},
                {"X", "X", "X"}
        };
        MineSweeper mineSweeper = new MineSweeper(grid);

        mineSweeper.uncoverCellAt(1, 1);

        assertThat(mineSweeper.cellAt(1, 1).value()).isEqualTo("8");
    }
}
