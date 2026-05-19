package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.GameSettings;

public class AnjeObject extends GameObject {


    int livesLeft;
    Texture[] framesArray;
    int frameCounter = 0;

    public AnjeObject(int width, int height, int x, int y, short cBits, World world) {
        super(width, height, x, y, cBits, world);
        body.setLinearDamping(10);
        livesLeft = 3;

        framesArray = new Texture[]{
                new Texture("AnjeSprites/Anje0.png"),
                new Texture("AnjeSprites/Anje1.png"),
        };
    }

    public int getLiveLeft() {
        return livesLeft;
    }

    @Override
    public void draw(SpriteBatch batch) {
//        putInFrame();
        int frameMultiplier = 10;
        int x = getX();
        int y = getY();
        System.out.println("x - " + x + "y - " + y);
        batch.draw(framesArray[frameCounter/frameMultiplier], x, y, width, height);
        if (frameCounter++ == framesArray.length * frameMultiplier - 1) frameCounter = 0;

    }

    public void move () {

        body.applyForceToCenter(0, 200, true);

    }

    private void putInFrame() {
        if (getY() > (GameSettings.SRC_HEIGHT - height )) {
            setY((int) (GameSettings.SRC_HEIGHT - height));
        }
        if (getY() <= (height)) {
            setY(height);
        }
        if (getX() < (-width)) {
            setX(GameSettings.SRC_WIDTH);
        }
        if (getX() > (GameSettings.SRC_WIDTH + width )) {
            setX(0);
        }
    }



    @Override
    public void hit() {
        livesLeft -= 1;
    }

    public boolean isAlive() {
        return livesLeft > 0;
    }
}
