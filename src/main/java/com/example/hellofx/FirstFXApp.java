package com.example.hellofx;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class FirstFXApp extends Application {

    // variables

    static int speed = 5;
    static int foodColor = 0;
    static int with = 20;
    static int height = 20;
    static int foodX = 0;
    static int foodY = 0;
    static int cornerSize = 25;
    static  ArrayList<Corner> snake = new ArrayList<>();
    static Dir direction = dir.left;
    static boolean gameOver = false;
    static Random rand = new Random();

    public enum Dir{
        left,right,up,down
    }

    public static class Corner{
        int x;
        int y;
        public Corner(int x, int y){
            this.x =x;
            this.y =y;
        }
    }




    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Snake");

        try
        {
            VBox root = new VBox();
            Canvas c = new Canvas(with*cornerSize,height*cornerSize);
            GraphicsContext gc = c.getGraphicsContext2D();
            root.getChildren().add(c);

            new AnimationTimer();
            long lastTick = 0;

            public void handle(long now){
                if(lastTick == 0){
                    lastTick == now;
                    return;
                }
                if (now - lastTick > 1000000000 / speed){
                    lastTick = now;
                }
        }

        }

     }
}
