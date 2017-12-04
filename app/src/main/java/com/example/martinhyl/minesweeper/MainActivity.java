package com.example.martinhyl.minesweeper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
    public static boolean sound;
    public static boolean vibration;
    public static String playerName;
    public Spinner spinner;
    public Grid grid;
    public Switch flagMode;
    public Timer T;
    public TextView timeTextView;
    public static int seconds = 0;
    Vibrator vibrator;
    private DB db;
    public static final String MySETTINGS = "MySettings";
    public static SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(MySETTINGS, Context.MODE_PRIVATE);


        playerName = sharedPreferences.getString("name","Roman");
        vibration = sharedPreferences.getBoolean("vibration",false);
        sound = sharedPreferences.getBoolean("sound",false);

        db = new DB(this);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
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

        //Toast.makeText(getApplicationContext(), String.valueOf(level), Toast.LENGTH_SHORT).show();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.leaderboard)
        {
            Intent intent = new Intent(getApplicationContext(),Leaderboard.class);
            startActivity(intent);

        }

        if (id == R.id.settings)
        {
            Intent intent = new Intent(getApplicationContext(),Settings.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
