package sample.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import sample.logic.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MainWindow {
    public static boolean win = false;
    private int[][] array1 = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 0, 15}}; // простой массив для проверки(путь=2)

    private int[][] array2 = new int[][]{{1, 2, 3, 0}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 4}}; //(путь =20)


    private int[][] array23 = new int[][]{{1,2,3,4}, {5,6,7,8}, {9,10,15,11}, {13,14,0,12}};//4
    Game game;


    @FXML
    Button buttonMove;
    @FXML
    private ImageView gridBack; //background
    @FXML
    private ImageView im00, im10, im20, im30, im01, im11, im21, im31, im02, im12, im22, im32, im03, im13, im23, im33; //cells
    @FXML
    Text text;
    private Set<ImageView> s = new HashSet<>(); //будем брать отсюда картинки для ячеек

    @FXML
    void initialize() {
        Image background = new Image("sample/images/pic0.jpg");
        gridBack.setImage(background);
        start();
    }

    private void keyPressed(Game game) {
        buttonMove.setOnKeyPressed(keyEvent ->
        {
            game.keyPressed(keyEvent);
            repaint();
        });
    }

    public void startRandomGame() { // новое поле для игры пользователем
        start();
    }

    private void start() {

        win = false;
        game = new Game();
        keyPressed(game);
        repaint();

    }


    public void begin() {

        //Board primary = new Board(game.getIntField()); //когда передаем реальное поле с экрана
      //  System.out.println("main" + Arrays.deepToString(game.getIntField())); // проверяем правильное ли поле передалось в доску для решателя
        Board primary = new Board(array1); //сейчас передаем заранее заготовленный массив

        Solver solver = new Solver(primary);

        System.out.println(solver.solution());
    }


    //имена ImageView складываются из "im" + номер строки + номер столбца
    private void repaint() {
        s.add(im00);
        s.add(im01);
        s.add(im02);
        s.add(im03);
        s.add(im10);
        s.add(im11);
        s.add(im12);
        s.add(im13);
        s.add(im20);
        s.add(im21);
        s.add(im22);
        s.add(im23);
        s.add(im30);
        s.add(im31);
        s.add(im32);
        s.add(im33);
        for (ImageView i : s) { //обнуляем каждую imageview из сета
            i.setImage(null);
        }

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                for (int k = 1; k < 16; k++) {
                    if (Game.field.grid[i][j] == k) {
                        try {
                            whatImageView(i, j).setImage(makeImages().get(k));//расставляем картинки с номерами на ячейки
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private ArrayList<Image> makeImages() throws FileNotFoundException { //массив для картинок
        ArrayList<Image> images = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            images.add(new Image(new FileInputStream("src/sample/images/pic" + i + ".jpg")));
        }

        return images;
    }


    private ImageView whatImageView(int x, int y) { // получаем ячейку по координатам, чтобы потом поставить  на нее картинку из сета
        if (x == 0 && y == 0) return im00;
        if (x == 1 && y == 0) return im01;
        if (x == 2 && y == 0) return im02;
        if (x == 3 && y == 0) return im03;
        if (x == 0 && y == 1) return im10;
        if (x == 1 && y == 1) return im11;
        if (x == 2 && y == 1) return im12;
        if (x == 3 && y == 1) return im13;
        if (x == 0 && y == 2) return im20;
        if (x == 1 && y == 2) return im21;
        if (x == 2 && y == 2) return im22;
        if (x == 3 && y == 2) return im23;
        if (x == 0 && y == 3) return im30;
        if (x == 1 && y == 3) return im31;
        if (x == 2 && y == 3) return im32;
        if (x == 3 && y == 3) return im33;
        return im00;
    }
}
