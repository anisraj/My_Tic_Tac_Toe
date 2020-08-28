package com.anisjamadar26.mytictactoe.framework;

import com.anisjamadar26.mytictactoe.domain.Game;

import dagger.Module;
import dagger.Provides;

/**
 * Implemented Dagger for this project
 * This module provides new Game object
 */
@Module
public class ApplicationModule {

    @Provides
    Game provideGame() {
        return new Game("X","O");
    }
}
