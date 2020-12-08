package sample.logic;

import java.util.*;

public class Solver {


    private List<Board> path = new ArrayList<>();   // этот лист - цепочка ходов, приводящих к решению задачи

    private static class Item {    //класс - сслылка на предыдущие состояния поля
        private Item predecessor;  // ссылка на предыдущее
        private Board board;   // сама позиция
        private int gx;


        private Item(Item predecessor, Board board) {
            this.predecessor = predecessor;
            this.board = board;
            calculateG(this);
        }


        private void calculateG(Item item) {
            int g = 0;
            Item itemCurr = item;
            while (true) {
                g++;
                itemCurr = itemCurr.predecessor;
                if (itemCurr == null) {
                    gx = g;

                    break;
                }
            }
        }

        public Board getBoard() {
            return board;
        }

        public int getG() {
            return gx;
        }
    }


    public Solver(Board primaryCondition) {

        if (!primaryCondition.isValid()) throw new IllegalArgumentException("Unsolvable");

        Comparator<Item> comp = Comparator.comparingInt(Solver::fx);
        //  очередь. Для нахождения приоритетного сравниваем меры,min Мера = max Приоритет
        PriorityQueue<Item> priorityQueue = new PriorityQueue<>(comp);


        // добавляем исходное поле
        priorityQueue.add(new Item(null, primaryCondition));

        for (; ; ) {
            Item board = priorityQueue.poll();

            //   если дошли до решения, сохраняем весь путь ходов в лист
            assert board != null;
            if (board.board.checkWin()) {
                insertInPath(new Item(board, board.board));
                return;
            }


            for (Board neighbor : board.board.neighbors()) {
                // один из соседей - это позиция которая была ходом раньше

                if (neighbor != null && !wasInPath(board, neighbor)) //если уже не содержится в пути(списке рассмотренных)
                    priorityQueue.add(new Item(board, neighbor));
            }

        }
    }

    //  f(x) = h+g
    private static int fx(Item item) {
        int hx = item.getBoard().getH();
        return hx + item.predecessor.getG() + 1;
    }


    private void insertInPath(Item item) {
        Item item2 = item;
        for (; ; ) {
            item2 = item2.predecessor;
            if (item2 == null) {
                return;
            }
            path.add(0, item2.board); //добавляем в начало
        }
    }

    // была ли уже такая позиция в пути
    private boolean wasInPath(Item item, Board board) {
        Item item2 = item;
        for (; ; ) {
            if (item2.board.equals(board)) return true;
            item2 = item2.predecessor;
            if (item2 == null) return false;
        }
    }


    public List<Board> solution() {
        System.out.println(path.size());
        return path;

    }


}
