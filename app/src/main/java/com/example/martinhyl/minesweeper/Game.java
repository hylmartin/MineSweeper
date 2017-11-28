package com.example.martinhyl.minesweeper;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.example.martinhyl.minesweeper.grid.Cell;

/**
 * Created by Martin Hyl on 11/26/2017.
 */

public class Game {
    private static Game instance;
    private Context context;
    public static boolean running = true;
    public static int mines = 10;
    public static int width = 8;
    public static int height = 8;
    public static int level = 1;
    public static boolean flagMode = false;

    private Cell[][] grid = new Cell[width][height];

    public static Game getInstance(){
        if (instance == null) {
           instance = new Game();
        }
        return instance;
    }

    public void createBoard(Context context) {
        this.context = context;

        grid = new Cell[width][height];
        Board board = new Board(mines,width,height);
        setGrid(context,board.getBoard());


    }

    public Cell getCellAt(int i) {
        int x = i % width;
        int y = i / width;

        return grid[x][y];
    }

    private void setGrid(Context context, int[][] grid) {
        for(int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if(this.grid[x][y] == null) {
                    this.grid[x][y] = new Cell(context,y * width + x);
                }
                this.grid[x][y].setValue(grid[x][y]);
                this.grid[x][y].invalidate();
            }
        }
    }

    public Cell getCellAt(int x, int y) {
        return grid[x][y];
    }

    public void click(int xPos, int yPos) {
        if(xPos >= 0 && yPos >= 0 && xPos < width && yPos < height && !getCellAt(xPos,yPos).isChecked()) {
            getCellAt(xPos, yPos).setChecked();

            if(getCellAt(xPos, yPos).getValue() == 0) {
                for(int xt = -1; xt <= 1; xt++){
                    for(int yt = -1; yt <= 1; yt++){
                        if( xt != yt){
                            click(xPos + xt, yPos + yt);
                        }
                    }
                }
            }

            if(getCellAt(xPos, yPos).isBomb()) {
                gameOver();
            }
        }
        isGameFinished();
    }

    private boolean isGameFinished() {
        int bombNotFound = mines;
        int notOpened = width * height;
        for(int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if(getCellAt(x,y).isOpened() || getCellAt(x,y).isFlagged()){
                    notOpened--;
                }

                if(getCellAt(x,y).isFlagged() && getCellAt(x,y).isBomb()) {
                    bombNotFound--;
                }
            }
        }
        if(notOpened == bombNotFound) {
            Toast.makeText(context,"GameWON",Toast.LENGTH_SHORT).show();
            Game.running = false;
        }

        return true;
    }

    private void gameOver() {
        Toast.makeText(context,"LOST",Toast.LENGTH_SHORT).show();

        for(int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                getCellAt(x,y).setOpened();

            }
        }
        Game.running = false;
    }

    public void flag(int xPos, int yPos) {
        boolean isFlagged = getCellAt(xPos, yPos).isFlagged();
        getCellAt(xPos, yPos).setFlagged(!isFlagged);
        getCellAt(xPos, yPos).invalidate();

    }


}
