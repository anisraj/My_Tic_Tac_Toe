package com.anisjamadar26.mytictactoe.presentation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.content.DialogInterface;
import android.os.Bundle;

import com.anisjamadar26.mytictactoe.R;
import com.anisjamadar26.mytictactoe.databinding.ActivityMainBinding;
import com.anisjamadar26.mytictactoe.domain.Player;
import com.anisjamadar26.mytictactoe.domain.StringUtility;
import com.anisjamadar26.mytictactoe.framework.ApplicationModule;
import com.anisjamadar26.mytictactoe.framework.DaggerApplicationComponent;

import javax.inject.Inject;

/***
 * In this Activity, adding Dagger Dependencies and injecting GameViewModel
 * Using Data Binding
 * By using LiveData, observing winner value and next player value from view model
 * Using that winner value, showing game end dialog
 */

public class MainActivity extends AppCompatActivity {

    @Inject
    public GameViewModel gameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDependancies();
        initDataBinding();
    }

    private void initDataBinding() {
        final ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        gameViewModel.init();
        binding.setGameViewModel(gameViewModel);
        setUpEndGameListener();
        gameViewModel.getNextPlayer().observe(this, binding.tvPlayerTurn::setText);
    }

    private void setUpEndGameListener() {
        gameViewModel.getWinner().observe(this, winner -> {
            String winnerName = winner == null || StringUtility.isNullOrEmpty(winner.name) ? getResources().getString(R.string.noWinnerText) : winner.name;
            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle(getResources().getString(R.string.gameEndText))
                    .setMessage(winnerName + getResources().getString(R.string.winsText))
                    .setCancelable(false)
                    .setPositiveButton(getResources().getString(R.string.doneText), (dialog, which) -> {
                        getDependancies();
                        dialog.dismiss();
                        initDataBinding();
                    })
                    .create();
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.setCancelable(false);
            alertDialog.show();
        });
    }

    private void getDependancies() {
        DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule())
                .build()
                .inject(this);
    }
}