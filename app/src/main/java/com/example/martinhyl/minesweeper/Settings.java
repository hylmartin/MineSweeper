package com.example.martinhyl.minesweeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class Settings extends AppCompatActivity {
    private Switch soundSwitch;
    private Switch vibrationSwitch;
    private EditText name;
    private Button saveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        soundSwitch = (Switch) findViewById(R.id.switchSound);
        vibrationSwitch = (Switch) findViewById(R.id.switchVibration);
        name = (EditText) findViewById(R.id.editTextName);
        saveButton  = (Button) findViewById(R.id.buttonSaveSettings);

        soundSwitch.setChecked(MainActivity.sound);
        vibrationSwitch.setChecked(MainActivity.vibration);
        name.setText(MainActivity.playerName);


        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                saveSettings();
            }
        });
    }




    public void saveSettings()
    {
        MainActivity.sound = soundSwitch.isChecked();
        MainActivity.vibration = vibrationSwitch.isChecked();
        MainActivity.playerName = String.valueOf(name.getText());

        onBackPressed();
    }

}
