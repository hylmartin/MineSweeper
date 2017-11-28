package com.example.martinhyl.minesweeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.martinhyl.minesweeper.grid.Grid;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private static int level;
    public Spinner spinner;
    public Grid grid;
    public Switch flagMode;
    public Timer T;
    public TextView timeTextView;
    public int seconds = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.levelSpinner);
        grid = (Grid) findViewById(R.id.mineGridView);
        flagMode = (Switch) findViewById(R.id.switchFlagMode);
        timeTextView = (TextView) findViewById(R.id.timeTextView);
        T =new Timer();

        Game.getInstance().createBoard(this);

        T.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(Game.running) {
                            timeTextView.setText(String.valueOf(seconds));
                            seconds++;
                        }
                    }
                });
            }
        },1000,1000);

        flagMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Game.flagMode = flagMode.isChecked();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                restartGame(parentView);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


    }

    public void restartGame(View view) {
        level = spinner.getSelectedItemPosition()+1;
        switch(level) {
            case 1:
                Game.mines = 10;
                Game.height = 8;
                Game.width = 8;
                break;
            case 2:
                Game.mines = 15;
                Game.height = 10;
                Game.width = 10;
                break;
            case 3:
                Game.mines = 20;
                Game.height = 13;
                Game.width = 10;
                break;
        }
        Game.level = level;
        Game.running = true;
        seconds = 0;
        timeTextView.setText(String.valueOf(seconds));
        //setContentView(R.layout.activity_main);
        Game.getInstance().createBoard(this);
        grid.setNumColumns(Game.width);
        grid.invalidateViews();

        Toast.makeText(getApplicationContext(), String.valueOf(level), Toast.LENGTH_SHORT).show();


    }
}
