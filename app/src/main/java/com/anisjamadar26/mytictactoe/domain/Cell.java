package com.anisjamadar26.mytictactoe.domain;

/**
 * This acts as a One cell for game
 * It can be empty or has a clicked player value
 */
public class Cell {
    public Player player;

    public Cell(Player player) {
        this.player = player;
    }

    public boolean isEmpty() {
        return player == null || StringUtility.isNullOrEmpty(player.value);
    }
}
