package org.example;

public class Cell {
    private boolean isMine;
    private boolean isRevealed;
    private int neighbouringMines;
    private boolean flagged;

    public Cell() {
        this.isMine = false;
        this.isRevealed = false;
        this.neighbouringMines = 0;
        this.flagged = false;
    }
    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean status) {
        flagged = status;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    public int getNeighbouringMines() {
        return neighbouringMines;
    }

    public void setNeighboursMines(int neighboursMines) {
        this.neighbouringMines = neighboursMines;
    }

    public void reveal() {
        this.isRevealed = true;
    }

    @Override
    public String toString() {
        if (isRevealed) {
            if (isMine) {
                return "X";
            } else {
                return Integer.toString(neighbouringMines);
            }
        }
        else if (flagged) {
            return "F";
        }
        else {
            return "-";
        }
    }




}

