package application;


import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/**
 * Можно показать, что ровно половину из всех возможных 1 307 674 368 000 (=15!) начальных положений пятнашек
 * невозможно привести к собранному виду: пусть квадратик с числом i расположен до (если считать слева направо и сверху вниз)
 * k квадратиков с числами меньшими i. Будем считать ni = k, то есть если после костяшки с i-м числом нет чисел, меньших i, то k = 0.
 * Также введем число e — номер ряда пустой клетки (считая с единицы).
 * если сумма ni(от 1 до 15) нечетна, то комбинация нерешаема
 */

public class Element {
    public int elem1, elem2, elem3, elem4, elem5, elem6, elem7, elem8, elem9, elem10,elem11,elem12,elem13,elem14,elem15,elem16;
    public boolean isRealCombination;//является ли данная комбинация решаемой
    public int[][] board;
    private int emptyY; //номер ряда пустой клетки(координата y)
    private int sum;
    private boolean hasNumber = false;
    Random random = new Random();

    public void generateNumbers() {  //расставляем фишки + считаем номер ряда пустой клетки для проверки решаемости
        Set<Integer> availableCounts = new TreeSet<>();
        board = new int[][]{{elem1, elem5, elem9, elem13}, {elem2, elem6, elem10, elem14}, {elem3, elem7, elem11, elem15}, {elem4, elem8, elem12, elem16}};
        for (int i = 0; i < 4; i++) { //если хотим генерировать случайным образом
            for (int j = 0; j < 4; j++) {
                int number = 0;
                if (!hasNumber) {
                    number = random.nextInt(16);
                    while (!availableCounts.contains(number)) {
                        availableCounts.add(number);
                        hasNumber = true;
                    }
                }
                if (number == 0) {
                    emptyY = j;//номер строки пустой клетки
                }
                board[i][j] = number;
            }
        }
        generateNumbers();
    }


    public void isAbleToBeSolved() { //проверка на решаемость
        sum = 0;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if ( (board[j % 4][j / 4] != 0) && board[i % 4][i / 4] > board[j % 4][j / 4]) {
                    sum++;
                }
            }
        }
        sum = sum + emptyY;

        isRealCombination = sum % 2 == 0; // количество больших элементов правее + номер строки пустой фишки четно
    }

}
