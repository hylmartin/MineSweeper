package com.example.martinhyl.minesweeper.grid;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.example.martinhyl.minesweeper.Game;

/**
 * Created by Martin Hyl on 11/26/2017.
 */

public class Grid extends GridView{
    public Grid (Context context, AttributeSet attrs) {
        super(context,attrs);

        setNumColumns(Game.width);
        setAdapter(new GridAdapter());

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    private class GridAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return Game.getInstance().width * Game.getInstance().height;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            return Game.getInstance().getCellAt(i);
        }
    }
}
