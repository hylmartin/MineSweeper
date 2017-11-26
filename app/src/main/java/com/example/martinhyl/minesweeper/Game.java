package com.example.martinhyl.minesweeper;

import android.content.Context;
import android.view.View;

import com.example.martinhyl.minesweeper.grid.Cell;

/**
 * Created by Martin Hyl on 11/26/2017.
 */

public class Game {
    private static Game instance;
    private Context context;

    public static final int mines = 10;
    public static final int width = 10;
    public static final int height = 10;

    private Cell[][] grid = new Cell[width][height];

    public static Game getInstance(){
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public void createBoard(Context context) {
        this.context = context;

        Board board = new Board(mines,width,height);
        setGrid(context,board.getBoard());
        

    }

    public View getCellAt(int i) {
        int x = i % width;
        int y = i / height;

        return grid[x][y];
    }

    private void setGrid(final Context context, final int[][] grid) {
        for(int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if(this.grid[x][y] == null) {
                    this.grid[x][y] = new Cell(context,y * height + x);
                }
                this.grid[x][y].setValue(grid[x][y]);
                this.grid[x][y].invalidate();
            }
        }
    }
}
