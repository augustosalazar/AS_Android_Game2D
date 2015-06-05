package com.augusto.androidgame2d;

import android.graphics.Bitmap;
import android.graphics.Canvas;


public class Player extends GameObject {
    private Bitmap spritesheet;
    private int score;
    private double dya;
    private boolean up;
    private boolean playing;
    private Animation animation = new Animation();
    private long startTime;

    public Player (Bitmap bitmap, int w, int h, int numFrames){
        x = 100;
        y = GamePanel.HEIGHT/2;
        dy = 0;
        score = 0;
        height = h;
        width = w;

        Bitmap[] image = new Bitmap[numFrames];
        spritesheet = bitmap;

        for (int i = 0; i< image.length; i++){
            image[i] = Bitmap.createBitmap(spritesheet,i*width,0,width,height);
        }

        animation.setFrames(image);
        animation.setDelay(10);
        startTime  = System.nanoTime();
    }

    public void setUp(boolean b){
        up = b;
    }

    public void update(){
        long elapsed = System.nanoTime()-startTime/1000000;

        if (elapsed > 100){
            score++;
            startTime = System.nanoTime();
        }

        animation.update();

        if (up){
            dy-=2;
        } else{
            dy+=2 ;
        }

        y += dy*2;
        dy = 0;

        if (y > 456){
            y  = 456;
            //System.out.println("Holding in low y "+y);
        }

        if (y < -2){
            y = -2;
            //System.out.println("Holding in higtht y "+y);
        }

        //System.out.println("HEIGHT "+GamePanel.HEIGHT+" y "+y+" dy "+dy+ "tempy "+tempy);

    }



    public void draw(Canvas canvas){
        canvas.drawBitmap(animation.getImage(),x,y,null);
    }

    public int getScore(){
        return score;
    }

    public boolean getPlaying(){
        return playing;
    }

    public void setPlaying(boolean b){ playing = b;}

    public void resetDYA(){
        dya = 0;
    }

    public void resetScore(){
        score = 0;
    }



}
