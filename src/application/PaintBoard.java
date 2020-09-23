package application;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class PaintBoard {
    private Map<Integer, Image> images = new HashMap<>();
    private void paint() throws FileNotFoundException {
        for(int i =0;i<=16;i++){  // расставляем картинки 
            images.put(i,new Image(new FileInputStream("src/pictures/"+i+".jpg")));
        }

    }
}
