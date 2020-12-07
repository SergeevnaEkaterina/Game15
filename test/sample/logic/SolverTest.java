package sample.logic;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SolverTest {
    private int[][] num = new int[][]{{2, 3, 4, 0}, {1, 6, 7, 8}, {5, 10, 11, 12}, {9, 13, 14, 15}};
    private int[][] num2 = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 15, 11}, {13, 14, 0, 12}};
    private int[][] num3 = new int[][]{{1, 2, 3, 0}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 4}};
    private int[][] array7 = new int[][]{{1, 2, 3, 7}, {5, 6, 8, 4}, {9, 13, 0, 15}, {12, 14, 10, 11}};

    @Test
    public void solution() {
        Solver solver = new Solver(new Board(num));
        assertEquals(10, solver.solution().size());
        Solver solver2 = new Solver(new Board(num2));
        assertEquals(4, solver2.solution().size());
        Solver solver3 = new Solver(new Board(num3));
        assertEquals(20, solver3.solution().size());
        Solver solver4 = new Solver(new Board(array7));
        assertEquals(31, solver4.solution().size());

    }
}