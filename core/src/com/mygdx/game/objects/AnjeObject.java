package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.GameSettings;

public class AnjeObject extends GameObject {


    int livesLeft;

    AnjeObject(String texturePath, int width, int height, int x, int y, short cBits, World world) {
        super(texturePath, width, height, x, y, cBits, world);
        body.setLinearDamping(10);
        livesLeft = 3;
    }

    public int getLiveLeft() {
        return livesLeft;
    }

    @Override
    public void draw(SpriteBatch batch) {
        putInFrame();
        super.draw(batch);
    }

    public void move () {
        body.applyForceToCenter((new Vector2(0 , 0,
                true
        )));
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

//    public void onClick(){
//        jump = true;
//        jumpHeight = GameSettings.MAX_HEIGHT_OF_JUMP + y;
//    }

    @Override
    public void hit() {
        livesLeft -= 1;
    }

    public boolean isAlive() {
        return livesLeft > 0;
    }
}
