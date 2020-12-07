package sample.logic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static java.lang.Math.abs;

public class Board {
    private int[][] tile;
    private int zeroX;
    private int zeroY;
    private int h;


    public Board(int[][] tile) {
        this.tile = deepCopy(tile);

        h = 0;
        for (int i = 0; i < 4; i++) {  //в этом цикле определяем координаты нуля и вычисляем h(x)
            for (int j = 0; j < 4; j++) {

                if (tile[i][j] != (i * 4 + j + 1) && tile[i][j] != 0) {
                    for (int k = 1; k < 16; k++) {
                        if (k == tile[i][j]) {  // номер на ячейке от 1 до 16
                            int y0 = (k - 1) / 4;
                            int x0 = k - y0 * 4 - 1;
                            h += (abs(i - x0) + abs(j - y0));
                        }

                    }
                }
                if (tile[i][j] == 0) {
                    zeroX = i;
                    zeroY = j;
                }
            }
        }

        System.out.println("h" + h);
        System.out.println("x" + zeroX);
        System.out.println("Y" + zeroY);
    }
    public boolean isValid() {

         boolean isSolvable = true;
         int counter = 0;
         for (int i = 0; i < 16; i++) {
         for (int j = i + 1; j < 16; j++) {
         if (tile[j % 4][j / 4] != 0)
         if (tile[i % 4][i / 4] > tile[j % 4][j / 4]) {
         counter++;
         }
         }
         }
         if ((counter + zeroY) % 2 == 0) isSolvable = false;
         return isSolvable;

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tile[i][j] != board.tile[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }
    @Override
    public int hashCode() { //deepHashCode проверяет вложенные массивы на совпадение номеров элементов и содержимого
        return Arrays.deepHashCode(tile);
    }


    public int getH() {
        return h;
    }

    public boolean checkWin() {  //   если мера неупорядоченности = -> терминальная позиция
        return h == 0;
    } //мера неупорядоченности = 0 -> все ячейки на терминальных позициях



    public Iterable<Board> neighbors() {  //порождаем дочерние состояния

        Set<Board> boardList = new HashSet<Board>();
        boardList.add(changePositions(getNewBlock(), zeroX, zeroY, zeroX, zeroY + 1));
        boardList.add(changePositions(getNewBlock(), zeroX, zeroY, zeroX, zeroY - 1));
        boardList.add(changePositions(getNewBlock(), zeroX, zeroY, zeroX - 1, zeroY));
        boardList.add(changePositions(getNewBlock(), zeroX, zeroY, zeroX + 1, zeroY));

        return boardList;
    }




    private Board changePositions(int[][] blocks2, int x1, int y1, int x2, int y2) {  //  в этом методе меняем два соседних поля
        if (x2 > -1 && x2 < 4 && y2 > -1 && y2 < 4) {
            int t = blocks2[x2][y2];
            blocks2[x2][y2] = blocks2[x1][y1];
            blocks2[x1][y1] = t;
            return new Board(blocks2);
        } else
            return null;
    }

    private int[][] getNewBlock() {
        return deepCopy(tile);
    }

    private static int[][] deepCopy(int[][] original) { // копия поля, чтобы состояние было неизменным тк иначе поле изменится в changePositions
        if (original == null) {
            return null;
        }

        final int[][] result = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = new int[original[i].length];
            System.arraycopy(original[i], 0, result[i], 0, original[i].length);
        }
        return result;
    }


}
