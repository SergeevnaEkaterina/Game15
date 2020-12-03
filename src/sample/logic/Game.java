package sample.logic;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sample.view.MainWindow;

public class Game {
    public static Field field;


    public Game() {
        field = new Field();
    }

    public int[][] getIntField() { // потом передаем это поле в решатель
        return field.getGrid();
    }

    public void keyPressed(KeyEvent keyEvent) {

        if (keyEvent.getCode().equals(KeyCode.RIGHT)) {
            if (field.zeroX != 0) {
                moveRight();
                checkWin();
            }
        }
        if (keyEvent.getCode().equals(KeyCode.DOWN)) {
            if (field.zeroY != 0) {
                moveDown();
                checkWin();
            }
        }
        if (keyEvent.getCode().equals(KeyCode.LEFT)) {
            if (field.zeroX != 3) {
                moveLeft();
                checkWin();
            }
        }
        if (keyEvent.getCode().equals(KeyCode.UP)) {
            if (field.zeroY != 3) {
                moveUp();
                checkWin();
            }
        }

    }

    void moveRight() {
        field.grid[field.zeroX][field.zeroY] = field.grid[field.zeroX - 1][field.zeroY];
        field.grid[field.zeroX - 1][field.zeroY] = 0;
        field.zeroX--;
    }

    void moveDown() {
        field.grid[field.zeroX][field.zeroY] = field.grid[field.zeroX][field.zeroY - 1];
        field.grid[field.zeroX][field.zeroY - 1] = 0;
        field.zeroY--;
    }

    void moveLeft() {
        field.grid[field.zeroX][field.zeroY] = field.grid[field.zeroX + 1][field.zeroY];
        field.grid[field.zeroX + 1][field.zeroY] = 0;
        field.zeroX++;
    }

    void moveUp() {
        field.grid[field.zeroX][field.zeroY] = field.grid[field.zeroX][field.zeroY + 1];
        field.grid[field.zeroX][field.zeroY + 1] = 0;
        field.zeroY++;
    }

    private static int[][] winfield = {{1, 5, 9, 13}, {2, 6, 10, 14}, {3, 7, 11, 15}, {4, 8, 12, 0}};

    void checkWin() {
        int counter = 0;
        for (int j = 0; j < 4; j++) {  //для каждой ячейки сравниваем с ее терминальной позицией
            for (int i = 0; i < 4; i++) {
                if (winfield[i][j] == field.grid[i][j]) counter++;
            }
        }
        MainWindow.win = counter == 16;

    }

}