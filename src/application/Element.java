package application;

import java.util.HashSet;
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
    public int elem1, elem2, elem3, elem4, elem5, elem6, elem7, elem;
    public boolean isRealCombination;//создать метод для проверки выигрыша и выводить окно при проигрышном раскладе
    public int[][] board;

    private int e; //номер ряда пустой клетки(координата y)

    private int sum;
    private boolean hasNumber = false;

    Random random = new Random();

    public void generateNumbers() {  //расставляем фишки + считаем номер ряда пустой клетки для проверки решаемости
        Set<Integer> avaliableCounts = new TreeSet<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                int number = 0;
                if (!hasNumber) {
                    number = random.nextInt(16);


                    while (!avaliableCounts.contains(number)) {
                        avaliableCounts.add(number);
                        hasNumber = true;
                    }
                }
                if (number == 0) {
                    e = j;//номер строки пустой клетки
                }
                board[i][j] = number;
            }
        }
        generateNumbers();
    }


    public void isAbleToBeSolved() {  //проверка на решаемость
        sum = 0;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (board[i % 4][i / 4] >= board[j % 4][j / 4]) {
                    sum++;
                }
            }
        }
        sum = sum + e;
        isRealCombination = sum % 2 == 0; // количество боьших элементов правее + номер строки пустой фишки нечетно
    }

}
