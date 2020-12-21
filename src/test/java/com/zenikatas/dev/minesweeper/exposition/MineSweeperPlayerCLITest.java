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

    @Test
    void can_play_a_game_using_the_console() {
        String[][] grid = {
                {"", "", ""},
                {"", "X", ""},
                {"", "X", ""},
        };
        ByteArrayOutputStream outputStream = redirectStandardOuputToString();

        List<String> onlyCorrectMoves = List.of(
                "0\n0",
                "0\n1",
                "0\n2",
                "1\n0",
                "1\n2",
                "2\n0",
                "2\n2");
        Scanner scanner = simulateListOfMoves(onlyCorrectMoves);
        MineSweeperPlayerCLI player = new MineSweeperPlayerCLI(grid, scanner);

        player.runGame();

        String currentOutput = outputStream.toString();
        assertThat(currentOutput).contains("? | ? | ?\n? | ? | ?\n? | ? | ?\n");
        assertThat(currentOutput).contains("1 | ? | ?\n? | ? | ?\n? | ? | ?\n");
        assertThat(currentOutput).contains("1 | 1 | ?\n? | ? | ?\n? | ? | ?\n");
        assertThat(currentOutput).contains("1 | 1 | 1\n? | ? | ?\n? | ? | ?\n");
        assertThat(currentOutput).contains("1 | 1 | 1\n2 | ? | ?\n? | ? | ?\n");
        assertThat(currentOutput).contains("1 | 1 | 1\n2 | ? | 2\n? | ? | ?\n");
        assertThat(currentOutput).contains("1 | 1 | 1\n2 | ? | 2\n2 | ? | ?\n");
        assertThat(currentOutput).contains("1 | 1 | 1\n2 | X | 2\n2 | X | 2\n");
        assertThat(currentOutput).contains("you won");
    }

    @Test
    void loose_a_game_with_the_console() {
        String[][] grid = {
                {"", "", ""},
                {"", "X", ""},
                {"", "X", ""},
        };
        ByteArrayOutputStream outputStream = redirectStandardOuputToString();

        String minePosition = "1\n1";
        List<String> moves = List.of(
                "0\n0",
                minePosition);
        Scanner scanner = simulateListOfMoves(moves);
        MineSweeperPlayerCLI player = new MineSweeperPlayerCLI(grid, scanner);

        player.runGame();

        String currentOutput = outputStream.toString();
        assertThat(currentOutput).contains("? | ? | ?\n? | ? | ?\n? | ? | ?\n");
        assertThat(currentOutput).contains("1 | ? | ?\n? | X | ?\n? | X | ?\n");
        assertThat(currentOutput).contains("you lost");
    }

    private Scanner simulateListOfMoves(List<String> moves) {
        String allMoves = String.join("\n", moves);
        InputStream inputStream = new ByteArrayInputStream(allMoves.getBytes(StandardCharsets.UTF_8));
        return new Scanner(inputStream);
    }

    private ByteArrayOutputStream redirectStandardOuputToString() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        return baos;
    }
}