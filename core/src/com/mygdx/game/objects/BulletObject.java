package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.GameSettings;

public class BulletObject extends GameObject {
    public boolean wasHit;
    Texture textureBullet;
    int x;

    public BulletObject(int x, int y, int width, int height, String texturePath, World world) {
        super(texturePath, x, y, width, height, GameSettings.BULLET_BIT, world);
        body.setLinearVelocity(new Vector2(GameSettings.BULLET_VELOCITY, GameSettings.BULLET_VELOCITY));
        body.setLinearVelocity(new Vector2(200, GameSettings.BULLET_VELOCITY));
        body.setBullet(true);
        wasHit = false;
    }

    public void draw(Batch batch, AnjeObject anjeObject) {
        batch.draw(textureBullet, x, anjeObject.getY() + anjeObject.height / 2, width, height);
    }

    public boolean hasToBeDestroyed() {
        return wasHit || (getY() - height / 2 > GameSettings.SRC_HEIGHT);
    }

    @Override
    public void hit() {
        wasHit = true;
    }
}
