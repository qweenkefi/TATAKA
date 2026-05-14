package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.GameSettings;


public class BulletObject extends GameObject {

    int speed = 20;
    Texture textureBullet;
    int x;
    public boolean bulletRun;


    public BulletObject(int x, int y, int width, int height, String texturePath, World world) {
        super(texturePath, x, y, width, height, GameSettings.BULLET_BIT, world);
        body.setLinearVelocity(new Vector2(0, GameSettings.BULLET_VELOCITY));
        body.setBullet(true);
        this.width = 10;
        this.height = 10;
    }


    public void draw (Batch batch){
        batch.draw(textureBullet, x, 0, width, height);
    }

    public void dispose () {
        textureBullet.dispose();
    }

    public void move () {
            x += speed;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, x - (width / 2f), getY() - (height / 2f), width, height);
    }
    public boolean isHit(MonsterObject m) {
        if (m.x >= x && m.x <= x + width && m.y >= 0 && m.y <= height) {
            return true;
        }
        return false;
    }
}