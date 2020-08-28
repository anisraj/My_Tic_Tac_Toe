package com.anisjamadar26.mytictactoe.framework;

import com.anisjamadar26.mytictactoe.domain.Game;
import com.anisjamadar26.mytictactoe.presentation.MainActivity;

import dagger.Component;

@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(MainActivity mainActivity);

    Game getTheGame();
}
