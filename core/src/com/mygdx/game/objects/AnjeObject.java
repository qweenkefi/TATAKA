package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AnjeObject {
    int x, y;
    int width, height;
    int speed;
    int jumpHeight;
    final int maxHeightOfJump = 400;
    boolean jump;
    int frameCounter;
    Texture[] framesArray;

    public AnjeObject(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        frameCounter = 0;
        this.width = 100;
        this.height = 250;

        framesArray = new Texture[]{
                new Texture("AnjeSprites/Anje0.png"),
                new Texture("AnjeSprites/Anje1.png"),
        };
    }

    public void onClick(){
        jump = true;
        jumpHeight = maxHeightOfJump + y;
    }

    public void run(){
        if(y >= jumpHeight){
            jump = false;
        }
        if(jump){
            y += speed;
        } else {
            if (y > 0) {
                y -= speed;
            } else {
            y = speed;}
        }
    }

    public void draw(SpriteBatch batch) {
        int frameMultiplier = 15;
        batch.draw(framesArray[frameCounter/frameMultiplier],x,y,width,height);
        if (frameCounter++ == framesArray.length * frameMultiplier - 1) frameCounter = 0;
    }
}
