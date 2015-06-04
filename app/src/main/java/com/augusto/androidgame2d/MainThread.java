package com.augusto.androidgame2d;


import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread
{
    private int FPS = 30;
    private long avarageFPS;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    public Canvas canvas;

    public MainThread(SurfaceHolder surfaceHolder,GamePanel gamePanel){
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    @Override
    public void run() {
        long startTime;
        long timeMilis;
        long waitTime;
        long totalTime = 0;
        int frameCount = 0;
        long targetTime = 1000/FPS;  // in milliseconds


        while (running){
            startTime = System.nanoTime();
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
            }catch (Exception e){

            }
            finally {
                if (canvas != null){
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e){}
                }
            }
            timeMilis = (System.nanoTime() - startTime)/1000000;
            waitTime = targetTime - timeMilis;

            try{
                this.sleep(waitTime);
            }catch (Exception e){}

            totalTime += System.nanoTime()-startTime;
            frameCount++;
            if (frameCount == FPS){
                avarageFPS = 1000/((totalTime/frameCount)/1000000);
                frameCount = 0;
                totalTime = 0;
                System.out.println(avarageFPS);
            }

        }


    }

    public void setRunning(boolean b) {
        running = b;
    }
}
