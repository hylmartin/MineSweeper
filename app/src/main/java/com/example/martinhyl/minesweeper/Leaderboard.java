package com.example.martinhyl.minesweeper;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Leaderboard extends AppCompatActivity {
    private DB db;
    private Cursor rs;
    private TextView leaderEasy;
    private TextView leaderMedium;
    private TextView leaderHard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        leaderEasy = (TextView) findViewById(R.id.textViewLeaderEasy);
        leaderMedium = (TextView) findViewById(R.id.textViewLeaderMedium);
        leaderHard = (TextView) findViewById(R.id.textViewLeaderHard);

        db = new DB(this);

        rs = db.getData(1);
        rs.moveToFirst();
        String leader1 = rs.getString(rs.getColumnIndex(DB.CONTACTS_COLUMN_NAME));
        int timeLeader1 = rs.getInt(rs.getColumnIndex(DB.CONTACTS_COLUMN_TIME));

        rs = db.getData(2);
        rs.moveToFirst();
        String leader2 = rs.getString(rs.getColumnIndex(DB.CONTACTS_COLUMN_NAME));
        int timeLeader2 = rs.getInt(rs.getColumnIndex(DB.CONTACTS_COLUMN_TIME));

        rs = db.getData(3);
        rs.moveToFirst();
        String leader3 = rs.getString(rs.getColumnIndex(DB.CONTACTS_COLUMN_NAME));
        int timeLeader3 = rs.getInt(rs.getColumnIndex(DB.CONTACTS_COLUMN_TIME));

        leaderEasy.setText(leader1 + " " + String.valueOf(timeLeader1) + " seconds" );
        leaderMedium.setText(leader2 + " " + String.valueOf(timeLeader2) + " seconds" );
        leaderHard.setText(leader3 + " " + String.valueOf(timeLeader3) + " seconds");


    }
}
