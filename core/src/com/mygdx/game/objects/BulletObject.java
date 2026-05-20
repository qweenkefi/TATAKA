package com.mygdx.game.objects;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.GameResources;

public class BulletObject {
    int x, y;
    int width;
    int height;
    int speed;
    AnjeObject anjeObject;
    Texture textureBullet;

public BulletObject(int x, int y, World world){

    this.x = 0;
    this.y = 200;
    this.speed = speed;
    this.height = 25;
    textureBullet = new Texture(GameResources.NORMAL_STUMP_IMAGE);

}

    public void dispose() {
        textureBullet.dispose();
    }

    public void move() {
        x += speed;
    }

    public void draw(Batch batch) {
        batch.draw(textureBullet, x, y, width, height);
    }

    public boolean isHit(MonsterObject m) {

        if (m.x >= x && m.x <= x + width && m.y >= 0 && m.y <= height) {
            return true;
        }
        return false;
    }
}