package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;

public class AnjeOblect {
    int x, y;
    int width, height;
    Texture texture;
    int speed;
    int jumpHeight;
    final int maxHeightOfJump = 200;
    boolean jump;
    int frameCounter;
    Texture[] framesArray;

    public AnjeOblect(int x, int y, Texture texture, int speed) {
        this.x = x;
        this.y = y;
        this.texture = texture;
        this.speed = speed;
        frameCounter = 0;
        this.width = 200;
        this.height = 200;

        framesArray = new Texture[]{
                new Texture("AnjeSprites/Anje0.png"),
                new Texture("AnjeSprites/Anje11.png"),
        };
    }

    public void jump(){
        if (y >= jumpHeight) {
            jump = false;
        }
        if (jump) {
            y += speed;
        } else{
            y -= speed;
        }
    }
}
