package sample.logic;

import sample.view.MainWindow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.abs;

public class Solver {

    private Game game;

    public Solver(Game game) {
        this.game = game;

    }

    private static int[][] winfield = {{1, 5, 9, 13}, {2, 6, 10, 14}, {3, 7, 11, 15}, {4, 8, 12, 0}};
    private int hx;
    private static List<int[][]> path = new ArrayList<>();
    private List<int[][]> opened, closed;
    private List<Double> heuristicOfOpened;
    private List<Integer> intArrayOpenedSize, intArrayClosedSize;
    private int openedSize;
    private int closedSize;
    public int countSteps = 0;

    public void botFindPath() {
        opened = new ArrayList<>();
        closed = new ArrayList<>();
        //2 списка: открытый и закрытый: в первом находятся вершины, еще не проверенные алгоритмом, а во втором те вершины,
        // которые уже встречались в ходе поиска решения.
        heuristicOfOpened = new ArrayList<>();
        intArrayOpenedSize = new ArrayList<>();
        intArrayClosedSize = new ArrayList<>();
        openedSize = 0;
        closedSize = 0;
        opened.add(Game.field.grid);//пока что поле не было проверено -> в открытом списке


        while (!opened.isEmpty()) {
            //для каждой ячейки проверяем совпадение с ее терминальной позицией
            int counter = 0;
            for (int j = 0; j < 4; j++) {
                for (int i = 0; i < 4; i++) {
                    if (winfield[i][j] == Game.field.grid[i][j]) {
                        counter++;
                    }
                }
            }

            if (counter == 16) { // если все на "выигрышных" местах
                boolean isNotEmpty = true;
                int size = intArrayClosedSize.size();
                while (isNotEmpty) {
                    if (closedSize != 0) path.add(closed.get(size)); //добавляем состояние поля в путь
                    if (size == 0) isNotEmpty = false;
                    else size = intArrayClosedSize.get(size - 1);

                }

                MainWindow.win = true;
                System.out.println("win");

                System.out.println("путь = " + path.size());
                Collections.reverse(path); //переворачиваем, тк добавляли путь в конец

                return;

            }

            //find min in heuristic
            double min = Integer.MAX_VALUE;
            int minInArray = 0;
            if (openedSize != 0) {
                for (int j = 0; j < openedSize - 1; j++) {
                    if (heuristicOfOpened.get(j) < min) { //из открытого списка выбираем состояние с наименьшим весом
                        min = heuristicOfOpened.get(j);
                        minInArray = j;
                    }
                }
            }


            for (int j = 0; j < 4; j++) {
                for (int i = 0; i < 4; i++) {
                    Game.field.grid[i][j] = opened.get(minInArray)[i][j];
                    if (Game.field.grid[i][j] == 0) {
                        Game.field.zeroY = j;
                        Game.field.zeroX = i;
                    }
                }
            }


            opened.remove(minInArray);
            if (openedSize != 0) {
                openedSize--;
                heuristicOfOpened.remove(minInArray);
            }

            //add closed
            int[][] fieldPosition = new int[4][4];
            for (int j = 0; j < 4; j++) {
                for (int i = 0; i < 4; i++) {
                    fieldPosition[i][j] = Game.field.grid[i][j];
                }
            }
            closed.add(fieldPosition);
            closedSize++;
            if (openedSize != 0) {
                intArrayClosedSize.add(intArrayOpenedSize.get(minInArray)); //переместилт из открытого в закрытый
                intArrayOpenedSize.remove(minInArray);
            }
            findNeighbours();


        }
    }

    public void findNeighbours() {
        //состояния, которые могут быть порождены из текущего(дочерние)
        if (Game.field.zeroY != 3) {
            game.moveUp();
            checkPosition();
            game.moveDown();
        }
        if (Game.field.zeroY != 0) {
            game.moveDown();
            checkPosition();
            game.moveUp();
        }
        if (Game.field.zeroX != 3) {
            game.moveLeft();
            checkPosition();
            game.moveRight();
        }
        if (Game.field.zeroX != 0) {
            game.moveRight();
            checkPosition();
            game.moveLeft();
        }

    }

    private void checkPosition() {

        int gx = 0;
        for (int i = intArrayClosedSize.size(); i == 0; i--) {
            gx++;
        }


        //проверяем есть ли такое состояние поля в закрытом списке
        boolean inClosed = false;
        for (int elem = 0; elem < closedSize; elem++) {
            int counter = 0;
            for (int j = 0; j < 4; j++) {
                for (int i = 0; i < 4; i++) {
                    if (Game.field.grid[i][j] == closed.get(elem)[i][j]) {
                        counter++;
                    }
                }
            }
            if (counter == 16) {
                inClosed = true;
            }
        }

        if (!inClosed) {  //если не встречалось-добавляем в открытый список и ищем Н

            int[][] arr = new int[4][4];
            for (int j = 0; j < 4; j++) {
                for (int i = 0; i < 4; i++) {
                    arr[i][j] = Game.field.grid[i][j];
                }
            }
            opened.add(arr);
            H();

            double fx = hx + gx;
            heuristicOfOpened.add(fx);
            openedSize++;
            intArrayOpenedSize.add(closedSize - 1);
        }
    }

    public void step() {
        if (path.size() > countSteps) Game.field.grid = path.get(countSteps);
        countSteps++;
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                if (Game.field.grid[i][j] == 0) {
                    Game.field.zeroX = i;
                    Game.field.zeroY = j;
                }
            }
        }
        game.checkWin();
        if (MainWindow.win) {
            for (int j = 0; j < 4; j++) {
                for (int i = 0; i < 4; i++) {
                    Game.field.grid[i][j] = winfield[i][j];
                }
            }
            Game.field.zeroX = 3;
            Game.field.zeroY = 3;
        }

    }


    private void H() {   // эвристическая функция, которая находит наилучшее положение для следующего хода
        // fx = hx  + gx,
        // где fx - само значение функции,
        // gx - количество ходов, которые привели к данному состоянию (путь),
        // hx - сумма разностей расстояний между расположением(координатами) каждой из плиток и их терминальной позицией(манхеттенское расстояние)=мера неупорядоченности,
        hx = 0;
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                for (int k = 1; k < 16; k++) {
                    if (k == Game.field.grid[i][j]) {
                        int y0 = (k - 1) / 4;
                        int x0 = k - y0 * 4 - 1;
                        hx += (abs(i - x0) + abs(j - y0));
                    }

                }
            }
        }
    }


}
