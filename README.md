## Game steps

* When the game starts, the player gets prompted for the grid size (width x height) and the number of mines.
* The grid is generated according to these requirements.
* The games starts, the user is prompted for the coordinates (x, y) of the first cell to uncover.
* The game shows the resulting grid and prompts for new coordinates.
* And so on ...
The game ends when there is no more non-mined cell to uncover or the player uncovers a mine.

## Rules

* Uncover a mine, and the game ends.
* Uncover an empty cell, and the player keeps playing.
* In each empty uncovered cell the number of adjacent cells holding mines is displayed.
* Uncovering a cell holding the number '0' (=no adjacent mined cells) uncovers all adjacent cells, and so on.

## Notes

* Command-line is sufficient, no fancy ui needed.
* Basically it's the good old windows minesweeper (minus flags).
* Focus on readability and simplicity.
* Tests are mandatory.


# How to

## Build (with tests) the MineSweeper

```shell script
./mvnw clean package
```

## Run and play to the MineSweeper CLI (I am not responsible for any damages your eyes could suffer when playing this on the console)
```shell script
java -jar target/minesweeper-1.0-SNAPSHOT.jar
```

## Quick explanation

* The `MineSweeper` class is my aggregate root. It manages a grid (Array of Array of `Cell`). 
* As we are in a finite world, I created the `CellValue` enum, in order to enforce Checks on cell values update.

## Miscellaneous

* The game asked for the number of row, columns and the number of mine. 
* There is no check based on your input, so please respect what the program asks for.

## Improvements and questions

* Do I need a class `Position` ? It seems that all my methods are using `(int row, int col)` parameters... But manipulating those types are easier for the client...
* Should I refactor the method `MineSweeperGame.uncoverAdjacentCell()` with Specification pattern ?
* The CLI gave me a hard time to test... I've done something, but clearly, it could be improved... I am not sure how yet...
* I didn't create an `Application` layer... This could be needed when moving to a game with state management to recover a ongoing game for instance...
* In the class `MineSweeperPlayerCLI`, I am manipulating the `Cell` class with some conditions... Is it my domain leaking, or just some UI behavior ? I am not sure
* Creating an abstraction of RandomGridGenerator and having the parameters `row, col and mineNumber` on the constructor