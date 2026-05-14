package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.GameSettings;


public class BulletObject extends GameObject {

    public int width = 10;
    public int height = 10;
    int speed = 20;
    Texture textureBullet;
    int x;
    public Body body;
    public boolean bulletRun;
    MonsterObject m;

    public BulletObject(int x, int y, int width, int height, String texturePath, World world) {
        super(texturePath, x, y, width, height, GameSettings.BULLET_BIT, world);
        body.setLinearVelocity(new Vector2(GameSettings.BULLET_VELOCITY, GameSettings.BULLET_VELOCITY));
        body.setLinearVelocity(new Vector2(0, GameSettings.BULLET_VELOCITY));
        body.setBullet(true);
    }


    public void draw (Batch batch){
        batch.draw(textureBullet, x, 0, width, height);
    }

    public void dispose () {
        textureBullet.dispose();
    }

    public void move () {
        if (bulletRun) {
            x += speed;
        }

    }

    public boolean hasToBeDestroyed() {
        if (m.x >= x && m.x <= x + width && m.y >= 0 && m.y <= height) {
            return true;
        }
        return false;
    }
}