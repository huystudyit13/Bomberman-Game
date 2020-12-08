package oop;

import oop.entities.*;
import oop.entities.Enemy.Balloon;
import oop.entities.Enemy.Doll;
import oop.entities.Enemy.Doria;
import oop.entities.Enemy.Oneal;
import oop.entities.Item.BombPoweredUp;
import oop.entities.Item.FlamePoweredUp;
import oop.entities.Item.SpeedPoweredUp;
import oop.entities.Player.Bomber;
import oop.graphics.Sprite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


public class TileMap {
    private int mapWidth = 31;
    private int mapHeight = 13;

    private int [][] map;
    private String urlMap = "D:\\Code big project\\Dic1\\Bommerman\\src\\oop\\res\\levels\\level1.txt";

    public TileMap(List<Entity> stillObjects, List<Entity> entities, List<Entity> item) throws IOException {
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
                        entities.add(object);
                        break;
                    case 2 :
                        object = new Brick(col, row, Sprite.brick.getFxImage());
                        entities.add(object);
                        break;
                    case 3 :
                        object = new Portal(col, row, Sprite.portal.getFxImage());
                        entities.add(object);
                        object = new Brick(col, row, Sprite.brick.getFxImage());
                        entities.add(object);
                        break;
                    case 4 :
                        object = new Bomber(col, row, Sprite.player_right.getFxImage());
                        entities.add(object);
                        break;
                    case 5 :
                        object = new Balloon(col, row, Sprite.balloom_left1.getFxImage(), 1);
                        entities.add(object);
                        break;
                    case 6 :
                        object = new Oneal(col, row, Sprite.oneal_left1.getFxImage(),2);
                        entities.add(object);
                        break;
                    case 10 :
                        object = new Doria(col, row, Sprite.kondoria_left1.getFxImage(),0.5, BombermanGame.getBomber());
                        entities.add(object);
                        break;
                    case 11 :
                        object = new Doll(col, row, Sprite.kondoria_left1.getFxImage(),1.5, BombermanGame.getBomber());
                        entities.add(object);
                        break;
                    case 8 :
                        object = new BombPoweredUp(col, row, Sprite.powerup_bombs.getFxImage());
                        item.add(object);
                        object = new Brick(col, row, Sprite.brick.getFxImage());
                        entities.add(object);
                        break;
                    case 7 :
                        object = new SpeedPoweredUp(col, row, Sprite.powerup_speed.getFxImage());
                        item.add(object);
                        object = new Brick(col, row, Sprite.brick.getFxImage());
                        entities.add(object);
                        break;
                    case 9 :
                        object = new FlamePoweredUp(col, row, Sprite.powerup_flames.getFxImage());
                        item.add(object);
                        object = new Brick(col, row, Sprite.brick.getFxImage());
                        entities.add(object);
                        break;
                }

            }
        }


    }

}