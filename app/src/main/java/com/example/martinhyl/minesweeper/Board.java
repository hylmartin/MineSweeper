package com.example.martinhyl.minesweeper;

import java.util.Random;

/**
 * Created by Martin Hyl on 11/26/2017.
 */

public class Board {
    
    private int[][] board;
    private int numberOfMines;
    private final int width;
    private final int height;

    private final int neighbours[][] = {{-1,-1}, {0,-1}, {1,-1}, {-1,0},{1,0},{-1,1},{0,1},{1,1}};

    public Board(int numberOfMines, int width, int height) {
        this.numberOfMines = numberOfMines;
        this.width = width;
        this.height = height;
        this.board = new int[width][height];
        generateBoard(this.numberOfMines,this.width,this.height);
    }

    public int[][] getBoard() {
        return board;
    }

    public void generateBoard(int numberOfMines, int width, int height) {
        Random r = new Random();
        
        for(int x = 0; x < width; x++) { 
            board[x] = new int[height];
        }
        
        int numberOfPlacedMines = 0;
        
        while(numberOfPlacedMines != numberOfMines) {
            int x = r.nextInt(width);
            int y = r.nextInt(height);
            
            // Minea je v board oznacena cislem -1
            if(board[x][y] != -1) {
                board[x][y] = -1;
                numberOfPlacedMines++;
            }
        }
       
        setNeighbours();
        
    }
    
    public void setNeighbours() {
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++) {
                board[x][y] = getCountOfNeighboursMines(x,y);
            }
        }
    }

    public int getCountOfNeighboursMines(int x, int y) {
        if (board[x][y] == -1) {
            return -1;
        }
        int count = 0;

        for(int[] neighbour : neighbours)
        {
            if(isMineAtPosition(x+neighbour[0],y+neighbour[1]))
            {
                count++;
            }
        }

        return count;
    }

    public boolean isMineAtPosition(int x, int y) {
        if(x >= 0 && x < width && y >= 0 && y < height) {
            if(board[x][y] == -1) {
                return true;
            }
        }
        return false;
    }
}
