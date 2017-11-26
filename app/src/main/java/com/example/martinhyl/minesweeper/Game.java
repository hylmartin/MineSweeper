package com.example.martinhyl.minesweeper;

import android.content.Context;

/**
 * Created by Martin Hyl on 11/26/2017.
 */

public class Game {
    private static Game instance;
    private Context context;
    private final int mines = 10;
    private final int width = 10;
    private final int height = 10;

    public static Game getInstance(){
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public void createBoard(Context context) {
        this.context = context;

        Board board = new Board(mines,width,height);
        

    }

}
