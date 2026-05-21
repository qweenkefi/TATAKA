package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

public class LikObject {
    int x, y;
    int width;
    public int height;
    int speed;
    int jumpHeight;
    final int maxHeightOfJump = 410;
    boolean jump;
    int frameCounter;
    Texture[] framesArray;
    int livesLeft;

    public LikObject(int x, int y, int speed) {

        this.x = x;
       this.y = y;
       this.speed = speed;
       frameCounter = 0;
       this.width = 80;
        this.height = 230;

        framesArray = new Texture[]{
                new Texture("LikSprites/runningLik.png"),
                new Texture("LikSprites/standingLik.png"),
                new Texture("LikSprites/walkingLik.png"),
        };
    }


    public void onClick() {
            jump = true;
            jumpHeight = maxHeightOfJump + y;
    }

    public void run() {
        if (y >= jumpHeight) {
            jump = false;
        }
        if (jump) {
            y += speed;
        } else {
            if (y > 0) {
                y -= speed;
            } else {
                y = speed;
            }
        }
    }

    public void draw(SpriteBatch batch) {
        int frameMultiplier = 15;
        batch.draw(framesArray[frameCounter / frameMultiplier], x, y, width, height);
        if (frameCounter++ == framesArray.length * frameMultiplier - 1) frameCounter = 0;
    }





    public boolean isAlive() {
        return livesLeft > 0;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x + 20;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y + 20;
    }
}

