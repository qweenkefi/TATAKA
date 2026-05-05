package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.GameSettings;
import com.mygdx.game.MyGdxGame;

import java.util.Random;

import static com.mygdx.game.GameResources.NORMAL_STUMP_IMAGE;
import static com.mygdx.game.GameSettings.SRC_HEIGHT;
import static com.mygdx.game.GameSettings.SRC_WIDTH;


public class StumpObject extends GameObject{
    Texture NORMAL_STUMP_IMAGE;
    boolean isPointReceived;
    Random random;
    int distanceBetweenStumps;
    int x = SRC_WIDTH + distanceBetweenStumps;
    int y = 300;
    int width = 200;
    int height = 250;
    int speed = 10;
    int stumpsCount;
    StumpObject(String texturePath, int width, int height, int x, int y, short cBits, World world) {
        super(texturePath, width, height, x, y, cBits, world);
        distanceBetweenStumps = (GameSettings.SRC_WIDTH + width) / (stumpsCount - 1);
        random = new Random();
        isPointReceived = false;

    }
    public boolean needAddPoint(AnjeObject anjeObject){


        return !isPointReceived && (anjeObject.x >=( x + width));
    }
    public void move(){
        x -= speed;
        if (x < -width){
            isPointReceived = false;
            x = SRC_WIDTH + distanceBetweenStumps;

        }


    }

    public void draw(Batch batch){
        batch.draw(NORMAL_STUMP_IMAGE, x,y, width, height);

    }
    public void dispose(){
        NORMAL_STUMP_IMAGE.dispose();


    }
}
