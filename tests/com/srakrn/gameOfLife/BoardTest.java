package com.srakrn.gameOfLife;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    public boolean[][] intsToBools(int[][] arr){
        boolean[][] res = new boolean[arr.length][arr[0].length];
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr.length; j++){
                res[i][j] = arr[i][j] == 1;
            }
        }
        return res;
    }
    @Test
    public void boardCreationTest(){
        Board b = new Board(5, 4);
    }

    @Test
    public void getEmptyBoardValues(){
        int x = 5;
        int y = 4;
        Board b = new Board(x, y);
        boolean[][] boardValues = b.getBoard();

        int boardXSize = boardValues.length;
        int boardYSize = boardValues[0].length;

        assertEquals(boardXSize, x);
        assertEquals(boardYSize, y);

        for(int i=0; i<x; i++){
            for(int j=0; j<y; j++){
                assertEquals(boardValues[i][j], false);
            }
        }
    }

    @Test
    public void setCellTest() {
        int x = 6;
        int y = 6;
        Board b = new Board(x, y);

        b.setCell(1,3, true);
        b.setCell(1,4, true);
        // setting true to false
        b.setCell(1,3, false);
        b.setCell(3,2, true);
        b.setCell(2,4, true);
        b.setCell(5,4, true);
        // setting true to false
        b.setCell(2,4, false);
        b.setCell(4,4, true);

        boolean[][] boardValues = b.getBoard();

        assertEquals(boardValues[1][3], false);
        assertEquals(boardValues[1][4], true);
        assertEquals(boardValues[3][2], true);
        assertEquals(boardValues[2][4], false);
        assertEquals(boardValues[5][4], true);
        assertEquals(boardValues[4][4], true);
    }

    @Test
    public void countAliveNeighbourTest(){
        Board b;
        int[][] multi;

        b = new Board(3, 3);
        multi = new int[][]{
                {1, 0, 1},
                {0, 1, 0},
                {0, 1, 1}
        };
        b.setBoardFromArray(multi);

        assertEquals(4, b.countAliveNeighbour(1, 1));
        assertEquals(1, b.countAliveNeighbour(0, 0));

        System.out.println("------------");

        b = new Board(4, 4);
        multi = new int[][]{
                {0, 0, 0, 0},
                {0, 1, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}
        };
        b.setBoardFromArray(multi);
        assertEquals(3, b.countAliveNeighbour(1, 1));
    }

    @Test
    public void blockPatternTest(){
        Board b = new Board(4, 4);
        int[][] multi = new int[][]{
                {0, 0, 0, 0},
                {0, 1, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}
        };
        b.setBoardFromArray(multi);
        boolean[][] bBefore = b.getBoard();

        for(int i=0; i<100; i++) {
            b.transit();
        }
        boolean[][] bAfter = b.getBoard();
        assertArrayEquals(bBefore, bAfter);
    }

    @Test
    public void loafPatternTest(){
        Board b = new Board(6, 6);
        int[][] multi = new int[][]{
                {0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 0, 0},
                {0, 1, 0, 0, 1, 0},
                {0, 0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0}
        };
        b.setBoardFromArray(multi);
        boolean[][] bBefore = b.getBoard();

        for(int i=0; i<100; i++) {
            b.transit();
        }
        boolean[][] bAfter = b.getBoard();
        assertArrayEquals(bBefore, bAfter);
    }

    @Test
    public void blinkerPatternTest(){
        Board b = new Board(5, 5);
        boolean[][] phaseA = new boolean[][]{
                {false, false, false, false, false},
                {false, false, false, false, false},
                {false, true, true, true, false},
                {false, false, false, false, false},
                {false, false, false, false, false},
        };
        boolean[][] phaseB = new boolean[][]{
                {false, false, false, false, false},
                {false, false, true, false, false},
                {false, false, true, false, false},
                {false, false, true, false, false},
                {false, false, false, false, false}
        };
        b.setBoardFromArray(phaseA);
        b.transit();
        assertArrayEquals(phaseB, b.getBoard());
        b.transit();
        assertArrayEquals(phaseA, b.getBoard());
    }

    @Test
    public void beaconPatternTest(){
        Board b = new Board(6, 6);
        boolean[][] phaseA = new boolean[][]{
                {false, false, false, false, false, false},
                {false, true, true, false, false, false},
                {false, true, false, false, false, false},
                {false, false, false, false, true, false},
                {false, false, false, true, true, false},
                {false, false, false, false, false, false},
        };
        boolean[][] phaseB = new boolean[][]{
                {false, false, false, false, false, false},
                {false, true, true, false, false, false},
                {false, true, true, false, false, false},
                {false, false, false, true, true, false},
                {false, false, false, true, true, false},
                {false, false, false, false, false, false},
       };
        b.setBoardFromArray(phaseA);
        b.transit();
        assertArrayEquals(phaseB, b.getBoard());
        b.transit();
        assertArrayEquals(phaseA, b.getBoard());
    }

    @Test
    public void gliderPatternTest(){
        Board b = new Board(6, 6);
        int[][] phaseA = new int[][]{
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0},
                {0, 1, 0, 1, 0, 0},
                {0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}
        };
        b.setBoardFromArray(phaseA);
        b.printBoard();
        b.transit();
        b.printBoard();
        int[][] phaseB = new int[][]{
                {0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 1, 0},
                {0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}
        };
        assertArrayEquals(b.getBoard(), intsToBools(phaseB));
        b.transit();
        b.printBoard();
        int[][] phaseC = new int[][]{
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 1, 0},
                {0, 0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}
        };
        assertArrayEquals(b.getBoard(), intsToBools(phaseC));
        b.transit();
        b.printBoard();
        int[][] phaseD = new int[][]{
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 1, 0},
                {0, 0, 0, 1, 1, 0},
                {0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0}
        };
        assertArrayEquals(b.getBoard(), intsToBools(phaseD));
        b.transit();
        b.printBoard();
        int[][] phaseE = new int[][]{
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0},
                {0, 0, 1, 0, 1, 0},
                {0, 0, 0, 1, 1, 0},
                {0, 0, 0, 0, 0, 0}
        };
        assertArrayEquals(b.getBoard(), intsToBools(phaseE));
    }
}