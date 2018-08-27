package com.srakrn.gameOfLife;

public class Board {
    boolean[][] board;
    int x, y;

    public Board(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.board = new boolean[x+2][y+2];
        for(int i=0; i<x+2; i++){
            for(int j=0; j<y+2; j++) {
                board[i][j] = false;
            }
        }
    }

    public boolean[][] getBoard(){
        boolean[][] slicedBoard = new boolean[this.x][this.y];
        for(int i=0; i<this.x; i++){
            for(int j=0; j<this.y; j++){
                slicedBoard[i][j] = this.board[i+1][j+1];
            }
        }
        return slicedBoard;
    }

    public int xLength(){
        return this.x;
    }

    public int yLength(){
        return this.y;
    }

    public boolean getCell(int x, int y) {
        return this.board[x+1][y+1];
    }

    public void setCell(int x, int y, boolean stat) {
        this.board[x+1][y+1] = stat;
    }

    public void setBoardFromArray(int[][] arr){
        for(int i=0; i<this.x; i++){
            for(int j=0; j<this.y; j++){
                if(arr[i][j] != 0){
                    this.board[i+1][j+1] = true;
                }
                else{
                    this.board[i+1][j+1] = false;
                }
            }
        }
    }

    public void setBoardFromArray(boolean[][] arr){
        for(int i=0; i<this.x; i++){
            for(int j=0; j<this.y; j++){
                this.board[i+1][j+1] = arr[i][j];
            }
        }
    }

    public int countAliveNeighbour(int x, int y) {
        int count = 0;
        for(int i=x-1; i<=x+1; i++){
            for(int j=y-1; j<=y+1; j++){
                if(i==x && j==y) {
                    // pass
                }
                else{
                    if (this.getCell(i, j)) {
                        count += 1;
                    }
                }
            }
        }
        return count;
    }

    public void printBoard(){
        for(int i=0; i<this.x; i++){
            for(int j=0; j<this.y; j++){
                if(this.getCell(i, j)){
                    System.out.print("\u25A0 ");
                }
                else{
                    System.out.print("\u25A1 ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void transit(){
        boolean[][] newBoard = new boolean[this.x][this.y];
        for(int i=0; i<this.x; i++){
            for(int j=0; j<this.y; j++){
                int nc = this.countAliveNeighbour(i, j);
                if(nc < 2 || nc > 3){
                    newBoard[i][j] = false;
                }
                else if(nc == 3){
                    newBoard[i][j] = true;
                }
                else{
                    newBoard[i][j] = this.getCell(i, j);
                }
            }
        }
        for(int i=0; i<this.x; i++){
            for(int j=0; j<this.y; j++){
                this.board[i+1][j+1] = newBoard[i][j];
            }
        }
    }
}
