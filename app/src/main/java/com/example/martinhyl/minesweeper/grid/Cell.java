package com.example.martinhyl.minesweeper.grid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Switch;

import com.example.martinhyl.minesweeper.Game;
import com.example.martinhyl.minesweeper.R;

/**
 * Created by Martin Hyl on 11/26/2017.
 */

public class Cell extends BaseCell implements View.OnClickListener, View.OnLongClickListener{

    public Cell(Context context, int i) {
        super(context);

        setPosition(i);

        setOnClickListener(this);
        setOnLongClickListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawButton(canvas);

        if( isFlagged() ) {
            drawFlag(canvas);
        } else if(isOpened() && isBomb() && !isChecked()) {
            drawBomb(canvas);
        } else {
            if (isChecked()){
                if( getValue() == -1 ) {
                    drawBomb(canvas);
                } else {
                    drawNumber(canvas);
                }
            } else {
                drawButton(canvas);
            }

        }
    }

    private void drawFlag(Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.flag);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    private void drawBomb(Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.simple_bomb);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    private void drawButton(Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.button);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    @Override
    public void onClick(View view) {
        if(Game.flagMode) {
            if(!Game.getInstance().getCellAt(getXPos(),getYPos()).isOpened())
                Game.getInstance().flag(getXPos(), getYPos());
        }
        else {
            if(!Game.getInstance().getCellAt(getXPos(),getYPos()).isFlagged())
                Game.getInstance().click( getXPos(), getYPos());
            else
                Game.getInstance().flag(getXPos(), getYPos());
        }

    }

    private void drawNumber(Canvas canvas) {
        Drawable drawable = null;

        switch (getValue()) {
            case 0:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.zero);
                break;
            case 1:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.one);
                break;
            case 2:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.two);
                break;
            case 3:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.three);
                break;
            case 4:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.four);
                break;
            case 5:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.five);
                break;
            case 6:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.six);
                break;
            case 7:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.seven);
                break;
            case 8:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.eight);
                break;
        }
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }


    @Override
    public boolean onLongClick(View view) {
        Game.getInstance().flag(getXPos(),getYPos());
        return true;
    }
}
