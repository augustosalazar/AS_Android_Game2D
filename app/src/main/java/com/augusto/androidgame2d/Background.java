package com.augusto.androidgame2d;


import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Background {

    private Bitmap image;
    private int x,y;
    private int dx;

    public Background(Bitmap bitmap){
        this.image = bitmap;
    }

    public void update(){
       
        x += dx;

        if (x < -GamePanel.WIDTH){
            x = 0;
        }
    }

    public void draw(Canvas canvas ){
        canvas.drawBitmap(image,x,y,null);
        if (x < 0){
            canvas.drawBitmap(image,x+GamePanel.WIDTH,y,null);
        }
    }
    public void setVector(int dx){
        this.dx = dx;
    }
}
