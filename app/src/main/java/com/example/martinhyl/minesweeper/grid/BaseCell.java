package com.example.martinhyl.minesweeper.grid;

import android.content.Context;
import android.view.View;

import com.example.martinhyl.minesweeper.Game;

/**
 * Created by Martin Hyl on 11/26/2017.
 */

public abstract class BaseCell extends View{
    private int value;
    private boolean isBomb;
    private boolean isOpened;
    private boolean isChecked;
    private boolean isFlagged;

    private int x;
    private int y;

    private int position;

    public BaseCell(Context context) {
        super(context);

    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        isBomb = false;
        isOpened = false;
        isChecked = false;
        isFlagged = false;

        if(value == -1) {
            isBomb = true;
        }
    }

    public boolean isBomb() {
        return isBomb;
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked() {
        this.isChecked = true;
        this.isOpened = true;

        invalidate();
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }


    public int getXPos() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getYPos() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
        x = position % Game.height;
        y = position / Game.width;
        invalidate();
    }
}
