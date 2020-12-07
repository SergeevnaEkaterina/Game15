package sample.logic;

import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {
    private int[][] unsolvable1 = new int[][]{{1,13,4,14}, {0,12,3,15}, {10,2,5,7}, {11,8,9,6}};
    private int[][] ableToBeSolved = new int[][]{{5,2,1,7}, {3,6,8,4}, {9,10,0,15}, {12,13,14,11}};
    private int[][] unsolvable2 = new int[][]{{1,7,6,2}, {0,3,11,4}, {14,9,5,15}, {10,13,8,12}};
    private int[][] array1 = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 0, 15}};
    private int[][] num = new int[][]{{2,3,4,0}, {1,6,7,8}, {5,10,11,12}, {9,13,14,15}};
    private int[][] num2 = new int[][]{{1,2,3,4}, {5,6,7,8}, {9,10,15,11}, {13,14,0,12}};
    private int[][] num3 = new int[][]{{1, 2, 3, 0}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 4}};


    @Test
    public void isValid() {
        assertFalse(new Board(unsolvable1).isValid());
        assertFalse(new Board(unsolvable2).isValid());
        assertTrue(new Board(ableToBeSolved).isValid());
        assertTrue(new Board(array1).isValid());
        assertTrue(new Board(num).isValid());
        assertTrue(new Board(num2).isValid());
        assertTrue(new Board(num3).isValid());
    }
}