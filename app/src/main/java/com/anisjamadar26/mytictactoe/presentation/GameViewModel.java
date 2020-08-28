package com.anisjamadar26.mytictactoe.presentation;

import androidx.databinding.ObservableArrayMap;
import androidx.lifecycle.LiveData;

import com.anisjamadar26.mytictactoe.domain.Cell;
import com.anisjamadar26.mytictactoe.domain.Game;
import com.anisjamadar26.mytictactoe.domain.Player;
import com.anisjamadar26.mytictactoe.domain.StringUtility;

import javax.inject.Inject;

/**
 * Injecting Game object using Dagger in constructor
 * By using Data Binding, passing this Class object to layout view as data
 * So when button from view clicked, method from this class is called
 */

public class GameViewModel {

    public ObservableArrayMap<String, String> cells;
    private Game game;

    @Inject
    public GameViewModel(Game game) {
        this.game = game;
    }

    public void init() {
        cells = new ObservableArrayMap<>();
    }

    public void onClickedAt(int row, int column) {
        if (game.cells[row][column] == null) {
            game.cells[row][column] = new Cell(game.currentPlayer);
            cells.put(StringUtility.stringFromNumbers(row, column), game.currentPlayer.value);
            if (game.hasGameEnded()) {
                game.reset();
            } else {
                game.switchPlayer();
            }
        }
    }

    public LiveData<Player> getWinner() {
        return game.winner;
    }

    public LiveData<String> getNextPlayer() {
        return game.nextPlayer;
    }
}
