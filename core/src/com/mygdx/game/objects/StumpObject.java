package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.GameResources;

import java.util.Random;

import static com.mygdx.game.GameSettings.SRC_HEIGHT;
import static com.mygdx.game.GameSettings.SRC_WIDTH;

public class StumpObject {
    int width = 100;
    int height =180;
    int speed = 6;
    Texture textureStump;
    int gapX;
    int gapWeigh = 500;
    Random random;
    int padding = 100;
    int x;

    public StumpObject(int stumpsCount, int stumpIdx) {

        random = new Random();
        textureStump = new Texture(GameResources.NORMAL_STUMP_IMAGE);

        gapX = gapWeigh + padding * 2 + random.nextInt(SRC_WIDTH * 3 * (padding + gapWeigh));
        x = stumpIdx + SRC_WIDTH;


    }

    public void draw(Batch batch) {
        batch.draw(textureStump, x, 0, width, height);
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

        if (a.x >= x && a.x <= x + width && a.y >= 0 && a.y <= height) {
            return true;
        }
        return false;
    }
    public boolean isHitLik(LikObject l) {

        if (l.x + 200 >= x && l.x + 200 <= x + width && l.y >= 0 && l.y <= height) {
            return true;
        }
        return false;
    }

}
