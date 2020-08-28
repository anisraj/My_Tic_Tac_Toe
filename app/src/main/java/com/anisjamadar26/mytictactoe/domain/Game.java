package com.anisjamadar26.mytictactoe.domain;

import androidx.lifecycle.MutableLiveData;

public class Game {

    private static final int BOARD_SIZE = 3;
    private static final String X_TURN = "X Turn";
    private static final String O_TURN = "O Turn";

    public Player player1;
    public Player player2;

    public Player currentPlayer = player1;

    public Cell[][] cells;

    public MutableLiveData<Player> winner = new MutableLiveData<>();

    public MutableLiveData<String> nextPlayer = new MutableLiveData<>();

    public Game(String playerOne, String playerTwo) {
        cells = new Cell[BOARD_SIZE][BOARD_SIZE];
        player1 = new Player(playerOne, "X");
        player2 = new Player(playerTwo, "O");
        currentPlayer = player1;
    }

    public boolean hasGameEnded() {
        if (hasSameHorizontalCells() || hasSameVerticalCells() || hasSameDiagonalCells()) {
            winner.setValue(currentPlayer);
            return true;
        }

        if (isBoardFull()) {
            winner.setValue(null);
            return true;
        }

        if (currentPlayer.value.equals("X")) {
            nextPlayer.postValue(O_TURN);
        } else {
            nextPlayer.postValue(X_TURN);
        }

        return false;
    }

    public boolean hasSameHorizontalCells() {
        try {
            for (int i = 0; i < BOARD_SIZE; i++)
                if (areEqual(cells[i][0], cells[i][1], cells[i][2]))
                    return true;

            return false;
        } catch (NullPointerException e) {
            return false;
        }
    }

    public boolean hasSameVerticalCells() {
        try {
            for (int i = 0; i < BOARD_SIZE; i++)
                if (areEqual(cells[0][i], cells[1][i], cells[2][i]))
                    return true;
            return false;
        } catch (NullPointerException e) {
            return false;
        }
    }

    public boolean hasSameDiagonalCells() {
        try {
            return areEqual(cells[0][0], cells[1][1], cells[2][2]) ||
                    areEqual(cells[0][2], cells[1][1], cells[2][0]);
        } catch (NullPointerException e) {
            return false;
        }
    }

    public boolean isBoardFull() {
        for (Cell[] cell: cells) {
            for (Cell cellItem : cell) {
                if (cellItem == null || cellItem.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean areEqual(Cell... cells) {
        if (cells == null || cells.length == 0) {
            return false;
        }

        for (Cell cell : cells) {
            if (cell == null || StringUtility.isNullOrEmpty(cell.player.value)) {
                return false;
            }
        }

        Cell firstCell = cells[0];
        for (int i = 1; i < cells.length; i++) {
            if (!firstCell.player.value.equals(cells[i].player.value)) {
                return false;
            }
        }

        return true;
    }

    public void switchPlayer() {
        currentPlayer = currentPlayer == player1 ? player2 : player1;
    }

    public void reset() {
        player1 = null;
        player2 = null;
        currentPlayer = null;
        cells = null;
        winner = new MutableLiveData<>();
        nextPlayer = new MutableLiveData<>();
    }
}
