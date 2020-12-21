package com.zenikatas.dev.minesweeper.domain;

import org.junit.jupiter.api.Test;

import static com.zenikatas.dev.minesweeper.domain.CellValue.*;
import static org.assertj.core.api.Assertions.assertThat;

public class MineSweeperTest {

    @Test
    void terminate_and_win_a_game_with_a_single_empty_cell() {
        String[][] grid = {{""}};
        MineSweeper mineSweeper = new MineSweeper(grid);

        mineSweeper.uncoverCellAt(0, 0);

        assertThat(mineSweeper.isTerminated()).isEqualTo(true);
        assertThat(mineSweeper.isWon()).isEqualTo(true);
        assertThat(mineSweeper.cellAt(0, 0).value()).isEqualTo(MINE_ADJACENT_0);
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

        assertThat(mineSweeper.cellAt(0, 0).value()).isEqualTo(MINE_ADJACENT_0);
        assertThat(mineSweeper.cellAt(0, 1).value()).isEqualTo(MINE_ADJACENT_0);
        assertThat(mineSweeper.cellAt(0, 2).value()).isEqualTo(MINE_ADJACENT_0);
        assertThat(mineSweeper.cellAt(1, 0).value()).isEqualTo(MINE_ADJACENT_0);
        assertThat(mineSweeper.cellAt(1, 1).value()).isEqualTo(MINE_ADJACENT_0);
        assertThat(mineSweeper.cellAt(1, 2).value()).isEqualTo(MINE_ADJACENT_0);
        assertThat(mineSweeper.cellAt(2, 0).value()).isEqualTo(MINE_ADJACENT_0);
        assertThat(mineSweeper.cellAt(2, 1).value()).isEqualTo(MINE_ADJACENT_0);
        assertThat(mineSweeper.cellAt(2, 2).value()).isEqualTo(MINE_ADJACENT_0);
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

        assertThat(mineSweeper.cellAt(0, 0).value()).isEqualTo(MINE_ADJACENT_0);
        assertThat(mineSweeper.cellAt(0, 1).value()).isEqualTo(MINE_ADJACENT_0);
        assertThat(mineSweeper.cellAt(0, 2).value()).isEqualTo(MINE_ADJACENT_0);
        assertThat(mineSweeper.cellAt(0, 3).value()).isEqualTo(MINE_ADJACENT_0);
        assertThat(mineSweeper.cellAt(1, 0).value()).isEqualTo(MINE_ADJACENT_0);
        assertThat(mineSweeper.cellAt(1, 1).value()).isEqualTo(MINE_ADJACENT_0);
        assertThat(mineSweeper.cellAt(1, 2).value()).isEqualTo(MINE_ADJACENT_0);
        assertThat(mineSweeper.cellAt(1, 3).value()).isEqualTo(MINE_ADJACENT_0);
        assertThat(mineSweeper.cellAt(2, 0).value()).isEqualTo(MINE_ADJACENT_0);
        assertThat(mineSweeper.cellAt(2, 1).value()).isEqualTo(MINE_ADJACENT_0);
        assertThat(mineSweeper.cellAt(2, 2).value()).isEqualTo(MINE_ADJACENT_0);
        assertThat(mineSweeper.cellAt(2, 3).value()).isEqualTo(MINE_ADJACENT_0);
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

        assertThat(mineSweeper.cellAt(0, 0).value()).isEqualTo(COVERED);
        assertThat(mineSweeper.cellAt(0, 1).value()).isEqualTo(COVERED);
        assertThat(mineSweeper.cellAt(0, 2).value()).isEqualTo(COVERED);
        assertThat(mineSweeper.cellAt(0, 3).value()).isEqualTo(COVERED);
        assertThat(mineSweeper.cellAt(1, 0).value()).isEqualTo(MINED);
        assertThat(mineSweeper.cellAt(1, 1).value()).isEqualTo(MINE_ADJACENT_1);
        assertThat(mineSweeper.cellAt(1, 2).value()).isEqualTo(COVERED);
        assertThat(mineSweeper.cellAt(1, 3).value()).isEqualTo(COVERED);
        assertThat(mineSweeper.cellAt(2, 0).value()).isEqualTo(COVERED);
        assertThat(mineSweeper.cellAt(2, 1).value()).isEqualTo(COVERED);
        assertThat(mineSweeper.cellAt(2, 2).value()).isEqualTo(COVERED);
        assertThat(mineSweeper.cellAt(2, 3).value()).isEqualTo(COVERED);
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

        assertThat(mineSweeper.cellAt(0, 0).value()).isEqualTo(COVERED);
        assertThat(mineSweeper.cellAt(0, 1).value()).isEqualTo(MINE_ADJACENT_1);
        assertThat(mineSweeper.cellAt(0, 2).value()).isEqualTo(MINE_ADJACENT_0);
        assertThat(mineSweeper.cellAt(0, 3).value()).isEqualTo(MINE_ADJACENT_0);
        assertThat(mineSweeper.cellAt(1, 0).value()).isEqualTo(MINED);
        assertThat(mineSweeper.cellAt(1, 1).value()).isEqualTo(MINE_ADJACENT_1);
        assertThat(mineSweeper.cellAt(1, 2).value()).isEqualTo(MINE_ADJACENT_0);
        assertThat(mineSweeper.cellAt(1, 3).value()).isEqualTo(MINE_ADJACENT_0);
        assertThat(mineSweeper.cellAt(2, 0).value()).isEqualTo(COVERED);
        assertThat(mineSweeper.cellAt(2, 1).value()).isEqualTo(MINE_ADJACENT_1);
        assertThat(mineSweeper.cellAt(2, 2).value()).isEqualTo(MINE_ADJACENT_0);
        assertThat(mineSweeper.cellAt(2, 3).value()).isEqualTo(MINE_ADJACENT_0);
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

        assertThat(mineSweeper.cellAt(1, 1).value()).isEqualTo(MINE_ADJACENT_8);
    }

    @Test
    void uncovering_all_not_mined_cell_win_the_game() {
        String[][] grid = {
                {"X", "X", "X"},
                {"X", "", "X"},
                {"X", "X", "X"}
        };
        MineSweeper mineSweeper = new MineSweeper(grid);

        mineSweeper.uncoverCellAt(1, 1);

        assertThat(mineSweeper.isTerminated()).isEqualTo(true);
        assertThat(mineSweeper.isWon()).isEqualTo(true);
        assertThat(mineSweeper.cellAt(1, 1).value()).isEqualTo(MINE_ADJACENT_8);
    }

    @Test
    void terminate_and_loose_the_game_with_single_mined_cell() {
        String[][] grid = {{"X"}};
        MineSweeper mineSweeper = new MineSweeper(grid);

        mineSweeper.uncoverCellAt(0, 0);

        assertThat(mineSweeper.isTerminated()).isEqualTo(true);
        assertThat(mineSweeper.isWon()).isEqualTo(false);
    }

    @Test
    void all_cells_are_covered_by_default() {
        String[][] grid = {{"X", ""}};
        MineSweeper mineSweeper = new MineSweeper(grid);

        assertThat(mineSweeper.cellAt(0, 0).isCovered()).isEqualTo(true);
        assertThat(mineSweeper.cellAt(0, 1).isCovered()).isEqualTo(true);
    }

    @Test
    void reveal_the_mine_when_the_game_is_terminated() {
        String[][] grid = {{"X", ""}};
        MineSweeper mineSweeper = new MineSweeper(grid);

        mineSweeper.uncoverCellAt(0, 0);

        assertThat(mineSweeper.isTerminated()).isEqualTo(true);
        mineSweeper.revealMines();

        assertThat(mineSweeper.cellAt(0, 0).isUncovered()).isEqualTo(true);
        assertThat(mineSweeper.cellAt(0, 1).isCovered()).isEqualTo(true);
    }

    @Test
    void do_not_reveal_the_mine_when_the_game_is_not_terminated() {
        String[][] grid = {{"X", ""}};
        MineSweeper mineSweeper = new MineSweeper(grid);

        assertThat(mineSweeper.isTerminated()).isEqualTo(false);
        mineSweeper.revealMines();

        assertThat(mineSweeper.cellAt(0, 0).isUncovered()).isEqualTo(false);
        assertThat(mineSweeper.cellAt(0, 1).isCovered()).isEqualTo(true);
    }
}
