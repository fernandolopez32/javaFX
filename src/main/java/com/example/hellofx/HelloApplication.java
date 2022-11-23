package com.example.hellofx;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class HelloApplication extends Application {
    // variables
    static int speed = 5;
    static int foodColor = 0;
    static int with = 20;
    static int height = 20;
    static int foodX = 0;
    static int foodY = 0;
    static int cornerSize = 25;
    static ArrayList<HelloApplication.Corner> snake = new ArrayList<>();
    static HelloApplication.Dir direction = HelloApplication.Dir.left;
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
    @Override
    public void start(Stage stage){
        try{
            newFood();

            VBox root = new VBox();
            Canvas c = new Canvas(with*cornerSize,height*cornerSize);
            GraphicsContext gc = c.getGraphicsContext2D();
            root.getChildren().add(c);
            new AnimationTimer(){
                long lastTick = 0;

                public void handle(long now){
                    if(lastTick == 0){
                        lastTick = now;
                        tick(gc);
                        return;
                    }
                    if (now - lastTick > 1000000000 / speed){
                        lastTick = now;
                        tick(gc);
                    }
                }
            }.start();

            Scene scene = new Scene(root,with*cornerSize, height*cornerSize);

            // control
            scene.addEventFilter(KeyEvent.KEY_PRESSED, key ->{
                if(key.getCode() == KeyCode.W){
                    direction = HelloApplication.Dir.up;
                }
                if(key.getCode() == KeyCode.A){
                    direction = HelloApplication.Dir.left;
                }
                if(key.getCode() == KeyCode.S){
                    direction = HelloApplication.Dir.down;
                }
                if(key.getCode() == KeyCode.D){
                    direction = HelloApplication.Dir.right;
                }
            });

            // add start snake parts
            snake.add(new HelloApplication.Corner(with/2,height/2));
//            snake.add(new HelloApplication.Corner(with/2,height/2));
//            snake.add(new HelloApplication.Corner(with/2,height/2));


            //========= THIS MAKES IT NOT WORK =====
//            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("applications.css")).toExternalForm());

            stage.setTitle("Snake");
            stage.setScene(scene);
            stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    // tick
    public static void tick(GraphicsContext gc){
        if(gameOver){
            gc.setFill(Color.RED);
            gc.setFont(new Font("", 50));
            gc.fillText("GAME OVER",100, 250);
            return;
        }
        for(int i = snake.size() -1; i> 1; i--){
            snake.get(i).x = snake.get(i-1).x;
            snake.get(i).y = snake.get(i-1).y;
        }
        switch (direction) {
            case up -> {
                snake.get(0).y--;
                if (snake.get(0).y < 0) {
                    gameOver = true;
                }
            }
            case down -> {
                snake.get(0).y++;
                if (snake.get(0).y > height) {
                    gameOver = true;
                }
            }
            case left -> {
                snake.get(0).x--;
                if (snake.get(0).x < 0) {
                    gameOver = true;
                }
            }
            case right -> {
                snake.get(0).x++;
                if (snake.get(0).x > with) {
                    gameOver = true;
                }
            }
        }
        // eat food
        if(foodX == snake.get(0).x && foodY == snake.get(0).y){
//            snake.add(new Corner(snake.get(1).x+1,snake.get(0).y+1));
            snake.add(new HelloApplication.Corner(snake.get(0).x-1,snake.get(0).y));

            newFood();
        }
        // self destroy
//        for(int i = 1; i<snake.size();i++){
//            if(snake.get(0).x == snake.get(i).x && snake.get(0).y == snake.get(i).y){
//                gameOver = true;
//            }
//        }
//         fill
//         background
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,with*cornerSize,height*cornerSize);

        // score
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("", 30));
        gc.fillText("Score" +(speed-6), 10,30);

        // random food color
        Color cc = Color.WHITE;

        switch(foodColor){
            case 0 -> cc = Color.PURPLE;
            case 1 -> cc = Color.LIGHTBLUE;
            case 2 -> cc = Color.YELLOW;
            case 3 -> cc = Color.PINK;
            case 4 -> cc = Color.ORANGE;
        }
        gc.setFill(cc);
        gc.fillOval(foodX*cornerSize, foodY *cornerSize, cornerSize,cornerSize);

        // snake color
        for(HelloApplication.Corner c: snake){
            gc.setFill(Color.LIGHTGREEN);
            gc.fillRect(c.x*cornerSize, c.y*cornerSize, cornerSize-1, cornerSize-1);
            gc.setFill(Color.GREEN);
            gc.fillRect(c.x*cornerSize,c.y*cornerSize,cornerSize-2, cornerSize-2);
        }
    }
    //food
    public static void newFood(){
        start:while (true){
            foodX = rand.nextInt(with);
            foodY = rand.nextInt(height);
            for(HelloApplication.Corner c: snake){
                if(c.x == foodX && c.y == foodY){
                    continue start;
                }
            }
            foodColor = rand.nextInt(5);
            speed++;
            break;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}