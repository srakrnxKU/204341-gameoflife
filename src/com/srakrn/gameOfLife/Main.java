package com.srakrn.gameOfLife;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void printEmptyLines(int n){
        for(int i=0; i<n;i++){
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(System.in);
        System.out.print("Enter the board's width (X axis): ");
        int x = reader.nextInt();
        System.out.print("Enter the board's height (X axis): ");
        int y = reader.nextInt();
        Board b = new Board(x, y);
        System.out.println("Enter the board with 0s and 1s: ");
        for(int i=0; i<x; i++){
            String line = reader.next();
            for(int j=0; j<y; j++){
                if(line.charAt(j) == '1'){
                    b.setCell(i, j, true);
                }
            }
        }
        int counter = 0;
        boolean running = true;
        while(running){
            printEmptyLines(100);
            System.out.println("Iteration: " + counter);
            b.printBoard();
            System.out.print("Press enter to continue.");
            System.in.read();
            b.transit();
            counter++;
        }
    }
}
