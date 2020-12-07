package sample.logic;


import sample.view.MainWindow;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Field {
    private final int size = 4;
    public int[][] grid = new int[4][4];
    public int zeroX; //public тк должны изменяться в Game
    public int zeroY;
    private Random random = new Random();
    private Set<Integer> numbers = new HashSet<>();

    public Field(int[][] grid) {

        this.grid = grid;
        fill();
    }

    public Field() {
        fill();
    }

    public int[][] getGrid() {
        return grid;
    }

    public void fill() {
        numbers.clear();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                boolean numbersContainsC = false;
                int value = 0;
                while (!numbersContainsC) {
                    value = random.nextInt(16);
                    if (!numbers.contains(value)) {
                        numbers.add(value);   //добавляем уникальное случайное число [1..16], заполняем сет
                        numbersContainsC = true;
                    }
                }
                if (value == 0) {//координаты пустой клетки для проверки решаемости и далее для контроля выхода за границы поля во время игры
                    zeroX = i;
                    zeroY = j;
                }
                grid[i][j] = value; // присваиваем ячейке число по которому будем брать картинку из сета для картинок

            }
        }
        System.out.println("grid" + Arrays.deepToString(grid));//числа на доске переписываются если нерешаемая комбинация
        if (!isAbleToBeSolved()) fill();


    }


    /**
     * Можно показать, что ровно половину из всех возможных 1 307 674 368 000 (=15!) начальных положений пятнашек
     * невозможно привести к собранному виду: пусть квадратик с числом i расположен до (если считать слева направо и сверху вниз)
     * k квадратиков с числами меньшими i. Будем считать ni = k, то есть если после костяшки с i-м числом нет чисел, меньших i, то k = 0.
     * Также введем число e — номер ряда пустой клетки (считая с единицы).
     * если сумма ni(от 1 до 15) нечетна, то комбинация нерешаема
     */
    public boolean isAbleToBeSolved() {
        boolean isSolvable = true;
        int counter = 0;
        for (int i = 0; i < 16; i++) {
            for (int j = i + 1; j < 16; j++) {
                if (grid[j % 4][j / 4] != 0)
                    if (grid[i % 4][i / 4] > grid[j % 4][j / 4]) {
                        counter++;
                    }
            }
        }
        if ((counter + zeroY) % 2 == 0) isSolvable = false;
        return isSolvable;
        /**
        boolean isSolvable = false;
        int counter = 0;
        for (int i = 0; i < 16; i++) {
            for (int j = i + 1; j < 16; j++) {
                if (grid[j % 4][j / 4] != 0)
                    if (grid[i % 4][i / 4] > grid[j % 4][j / 4]) {
                        counter++;
                    }
            }
        }
        if ((counter + zeroY) % 2 == 0) isSolvable = true;
        return isSolvable;
        */
    }

}
