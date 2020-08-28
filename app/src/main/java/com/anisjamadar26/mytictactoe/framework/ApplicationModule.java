package com.anisjamadar26.mytictactoe.framework;

import com.anisjamadar26.mytictactoe.domain.Game;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    @Provides
    Game provideGame() {
        return new Game("X","O");
    }
}
