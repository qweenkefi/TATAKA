package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.GameResources;

import java.util.Random;

import static com.mygdx.game.GameSettings.SRC_WIDTH;

public class MonsterObject extends StumpObject {

    int width = 150;
    int height =200;
    int speed = 7;
    Random random;
    int padding = 100;
    int x;
    Texture textureMonster;

    public MonsterObject(int stumpsCount, int stumpIdx, World world) {
        super(stumpsCount, stumpIdx, world);

        random = new Random();
        textureMonster = new Texture(GameResources.NORMAL_STUMP_IMAGE);

        gapX = (gapWeigh + padding) * 2 + random.nextInt(SRC_WIDTH * (padding + gapWeigh));
        x = stumpIdx + SRC_WIDTH;
    }

    public void draw(Batch batch) {
        batch.draw(textureStump, x, 300, width, height);
    }

    public void dispose() {
        textureStump.dispose();
    }

    public void move() {
        x -= speed;
        if (x < -width) {
            x = SRC_WIDTH;
            gapX = gapWeigh + padding + random.nextInt(SRC_WIDTH - 2 * (padding + gapWeigh / 2));
        }
    }


    public boolean isHit(AnjeObject a) {

        if (a.x >= x && a.x <= x + width && a.y >= 300 && a.y <= height) {
            return true;
        }
        return false;
    }

}
