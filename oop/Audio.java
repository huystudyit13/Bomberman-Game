package oop;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

public class Audio {
    protected static MediaPlayer meidaPlayer;
    protected static final String file = "E:\\IdeaProjects\\bomberman-game\\src\\oop\\res\\audio\\";
    public static void backgr() {
        String s = file + "16.mp3";
        Media media = new Media(Paths.get(s).toUri().toString());
        meidaPlayer = new MediaPlayer(media);
        meidaPlayer.play();
    }

    public static void placeBomb() {
        String s = file + "setbomb.wav";
        Media media = new Media(Paths.get(s).toUri().toString());
        meidaPlayer = new MediaPlayer(media);
        meidaPlayer.play();
    }

    public static void item() {
        String s = file + "item.wav";
        Media media = new Media(Paths.get(s).toUri().toString());
        meidaPlayer = new MediaPlayer(media);
        meidaPlayer.play();
    }

    public static void bombexplode() {
        String s = file + "bombexplode.wav";
        Media media = new Media(Paths.get(s).toUri().toString());
        meidaPlayer = new MediaPlayer(media);
        meidaPlayer.play();
    }

    public static void bomberdie() {
        String s = file + "bomberdie.wav";
        Media media = new Media(Paths.get(s).toUri().toString());
        meidaPlayer = new MediaPlayer(media);
        meidaPlayer.play();
    }

    public static void botdie() {
        String s = file + "botdie.wav";
        Media media = new Media(Paths.get(s).toUri().toString());
        meidaPlayer = new MediaPlayer(media);
        meidaPlayer.play();
    }
}
