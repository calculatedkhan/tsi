package org.example;
import java.util.Random;

public class Board {
    private int rows;
    private int columns;
    private Cell[][] cells;
    private boolean gameOver;

    public Board(int rows, int columns) { //initialises the board with cells
        this.rows = rows;
        this.columns = columns;
        this.cells = new Cell[rows][columns];
        this.gameOver = false;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                cells[row][col] = new Cell();
            }
        }

    }

    public boolean isGameOver() { //checks whether game is over
        return gameOver;
    }

    public void setGameOver(boolean result) { //sets the game to be over
        gameOver = result;
    }

    public boolean cellRevealedStatus(int r, int c) { //checks if cell is revealed
        return (cells[r-1][c-1].isRevealed());
    }

    public boolean cellFlaggedStatus(int r, int c) { //checks if cell is flagged
        return (cells[r-1][c-1].isFlagged());
    }

    public boolean checkWin() { //checks if player has won
        boolean allNonMineCellsRevealed = true;
        boolean allMineCellsNotRevealed = true;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (cells[i][j].isRevealed() && cells[i][j].isMine()) {
                    // If a mine cell is revealed, player hasn't won
                    return false;
                } else if (!cells[i][j].isRevealed() && !cells[i][j].isMine()) {
                    // If a non-mine cell is not revealed, player hasn't won
                    allNonMineCellsRevealed = false;
                }
            }
        }

        // Player wins if all non-mine cells are revealed and all mine cells are not revealed
        return allNonMineCellsRevealed;
    }

    public void placeMines(int numberOfMines) { //randomly places mines, based on given difficulty
        Random random = new Random();
        for (int i = 0; i < numberOfMines; i++) {
            int mineRow = random.nextInt(rows);
            int mineCol = random.nextInt(columns);
            boolean isMine = cells[mineRow][mineCol].isMine();
            int mineValue = isMine ? 1 : 0; //in case it chooses the same cell
            switch (mineValue) {
                case (1) :
                    int mineRow1 = random.nextInt(rows);
                    int mineCol1 = random.nextInt(columns);
                    cells[mineRow1][mineCol1].setMine(true);
                default:
                    cells[mineRow][mineCol].setMine(true);
            }
        }
    }

    public boolean cellFlagChecker() { //checks if at least 1 cell is flagged
        boolean flagChecker = false;
        for (int i=0; i<rows; i++) {
            for (int j=0; j<columns; j++) {
                if (cells[i][j].isFlagged()) {
                    flagChecker = true;
                }
            }
        }
        return flagChecker;
    }

    public void flagCell(int r, int c) { //sets a given cell to be flagged
        cells[r-1][c-1].setFlagged(true);
    }

    public void unflagCell(int r, int c) { //sets a given cell to be unflagged
        cells[r-1][c-1].setFlagged(false);
    }

    public void revealCell(int r, int c) { //reveals cells based on chosen cell
        r = r - 1;
        c = c - 1;
        if (!cells[r][c].isRevealed() && !cells[r][c].isFlagged()) {
            cells[r][c].reveal();
            if (cells[r][c].isMine()) {
                System.out.println("GAME OVER");
                setGameOver(true);
            }
            else if (cells[r][c].getNeighbouringMines() == 0) {
                if (r-1 >= 0) {
                    revealCell(r, c+1);
                }
                if (r+1 < rows) {
                    revealCell(r+2, c+1);
                }
                if (c-1 >= 0) {
                    revealCell(r+1, c);
                }
                if (c+1 < columns) {
                    revealCell(r+1, c+2);
                }
                if (r-1 >=0 && c-1 >= 0) { // top left
                    revealCell(r, c);
                }
                if (r-1 >= 0 && c+1 < columns) { //top right
                    revealCell(r,c+2);
                }
                if (r+1 < rows && c-1 >= 0) { //bottom left
                    revealCell(r+2, c);
                }
                if (r+1 < rows && c+1 < columns) {
                    revealCell(r+2, c+2);
                }
            }
        }
        else if (cells[r][c].isFlagged()) {
            System.out.println("Cell has already been revealed");
        }
    }

    public void calculateNeighbouringMines() { //calculates neighbouring mines for each cell
        int counter = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (!cells[i][j].isMine()) {
                    if (i - 1 >= 0 && cells[i-1][j].isMine()) { //checking up
                        ++counter;
                    }
                    if (i + 1 < rows && cells[i+1][j].isMine()) { //checking down
                        ++counter;
                    }
                    if (j - 1 >= 0 && cells[i][j-1].isMine()) { //checking left
                        ++counter;
                    }
                    if (j + 1 < columns && cells[i][j+1].isMine()) {  //checking right
                        ++counter;
                    }
                    if (i-1 >=0 && j-1 >= 0 && cells[i-1][j-1].isMine()) { //checking top left
                        ++counter;
                    }
                    if (i-1 >= 0 && j+1 < columns && cells[i-1][j+1].isMine()) { //checking top right
                        ++counter;
                    }
                    if (i+1 < rows && j-1 >= 0 && cells[i+1][j-1].isMine()) { //checking bottom left
                        ++counter;
                    }
                    if (i+1 < rows && j+1 < columns && cells[i+1][j+1].isMine()) {
                        ++counter;
                    }
                }
                cells[i][j].setNeighboursMines(counter);
                counter = 0;
            }
        }
    }

    public void displayBoard() { //displays board to console
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                System.out.print(cells[row][col] + " ");
            }
            System.out.println();
        }
    }
}