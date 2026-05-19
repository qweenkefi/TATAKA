package com.mygdx.game.objects;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.GameSettings;

import static com.badlogic.gdx.math.MathUtils.random;

public class MonsterObject extends StumpObject {


    private static final int padding = 30;

    private int livesLeft;

    MonsterObject(String texturePath, int width, int height, int x, int y, short cBits, World world) {
        super(texturePath, 100, 100, x , y, GameSettings.STUMP_BIT, world);

        body.setLinearVelocity(new Vector2(-(width + GameSettings.SRC_WIDTH + padding + random(700)),0)) ;
        livesLeft = 1;
    }
    public boolean isAlive() { return livesLeft > 0; }

    public boolean isInFrame() {
        return getY() + height / 2 > 0;
    }

    @Override
    public void hit() {
        livesLeft -= 1;
    }

}