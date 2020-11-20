import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import entities.Entity;
import entities.Wall;
import entities.Brick;
import entities.Grass;
import entities.Portal;
import entities.Bomber;
import graphics.Sprite;


public class TileMap {
    private int mapWidth = 20;
    private int mapHeight = 13;

    private int [][] map;
    private String urlMap = "D:\\Code big project\\Dic1\\Bommerman\\src\\res\\levels\\level1.txt";


    public TileMap(List<Entity> stillObjects) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(urlMap));
        map = new int [mapHeight][mapWidth];

        String line ="";
        for (int row = 0; row < mapHeight; row++) {
            line = br.readLine();
            String [] arr = line.split(" ");
            for (int col = 0; col < mapWidth; col++) {
                Entity object = null;
                map[row][col] = Integer.parseInt(arr[col]);

                object = new Grass(col, row, Sprite.grass.getFxImage());
                stillObjects.add(object);


                switch (map[row][col]) {
                    case 0:
                        break;
                    case 1 :
                        object = new Wall(col, row, Sprite.wall.getFxImage());
                        break;
                    case 2 :
                        object = new Brick(col, row, Sprite.brick.getFxImage());
                        break;
                    case 3 :
                        object = new Portal(col, row, Sprite.portal.getFxImage());
                        break;
                    case 4 :
                        object = new Bomber(col, row, Sprite.player_right.getFxImage());
                        break;
                    case 5 :

                }

                stillObjects.add(object);
            }
        }
    }

}