package com.zenikatas.dev.minesweeper.exposition;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

class MineSweeperPlayerCLITest {

    private final static String LINE_SEPARATOR = System.lineSeparator();

    @Test
    void can_play_a_game_using_the_console() {
        String[][] grid = {
                {"", "", ""},
                {"", "X", ""},
                {"", "X", ""},
        };
        ByteArrayOutputStream outputStream = redirectStandardOutputToString();

        List<String> onlyCorrectMoves = List.of(
                at(0, 0),
                at(0, 1),
                at(0, 2),
                at(1, 0),
                at(1, 2),
                at(2, 0),
                at(2, 2)
        );
        Scanner scanner = simulateListOfMoves(onlyCorrectMoves);
        MineSweeperPlayerCLI player = new MineSweeperPlayerCLI(grid, scanner);

        player.runGame();

        String currentOutput = outputStream.toString();
        assertThat(currentOutput).contains(gridWithRows("? | ? | ?", "? | ? | ?", "? | ? | ?"));
        assertThat(currentOutput).contains(gridWithRows("1 | ? | ?", "? | ? | ?", "? | ? | ?"));
        assertThat(currentOutput).contains(gridWithRows("1 | 1 | ?", "? | ? | ?", "? | ? | ?"));
        assertThat(currentOutput).contains(gridWithRows("1 | 1 | 1", "? | ? | ?", "? | ? | ?"));
        assertThat(currentOutput).contains(gridWithRows("1 | 1 | 1", "2 | ? | ?", "? | ? | ?"));
        assertThat(currentOutput).contains(gridWithRows("1 | 1 | 1", "2 | ? | 2", "? | ? | ?"));
        assertThat(currentOutput).contains(gridWithRows("1 | 1 | 1", "2 | ? | 2", "2 | ? | ?"));
        assertThat(currentOutput).contains(gridWithRows("1 | 1 | 1", "2 | X | 2", "2 | X | 2"));
        assertThat(currentOutput).contains("you won");
    }

    @Test
    void can_lose_a_game_with_the_console() {
        String[][] grid = {
                {"", "", ""},
                {"", "X", ""},
                {"", "X", ""},
        };
        ByteArrayOutputStream outputStream = redirectStandardOutputToString();

        String minePosition = at(1, 1);
        List<String> moves = List.of(
                at(0, 0),
                minePosition);
        Scanner scanner = simulateListOfMoves(moves);
        MineSweeperPlayerCLI player = new MineSweeperPlayerCLI(grid, scanner);

        player.runGame();

        String currentOutput = outputStream.toString();
        assertThat(currentOutput).contains(gridWithRows("? | ? | ?", "? | ? | ?", "? | ? | ?"));
        assertThat(currentOutput).contains(gridWithRows("1 | ? | ?", "? | X | ?", "? | X | ?"));
        assertThat(currentOutput).contains("you lost");
    }

    private String at(int x, int y) {
        return x + LINE_SEPARATOR + y;
    }

    private Scanner simulateListOfMoves(List<String> moves) {
        String allMoves = String.join(LINE_SEPARATOR, moves);
        InputStream inputStream = new ByteArrayInputStream(allMoves.getBytes(StandardCharsets.UTF_8));
        return new Scanner(inputStream);
    }

    private ByteArrayOutputStream redirectStandardOutputToString() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        return baos;
    }

    private String gridWithRows(String... rows) {
        return String.join(LINE_SEPARATOR, rows) + LINE_SEPARATOR;
    }
}
