package oop.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import oop.BombermanGame;
import oop.fileloader.TileMap;

public class Portal extends Entity {
    public static boolean isClearStage = false;
    public Portal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }


    @Override
    public void update() {
        if(isClearStage) {
            BombermanGame.LEVEL++;
            System.out.println("You Win!");
            BombermanGame.winStage = true;
            isClearStage = false;
            //clear list

        }
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img,x,y);
    }

}