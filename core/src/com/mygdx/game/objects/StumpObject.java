package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.GameResources;

import java.util.Random;

import static com.mygdx.game.GameSettings.SRC_HEIGHT;
import static com.mygdx.game.GameSettings.SRC_WIDTH;

public class StumpObject {
    int width = 1500;
    int height = 700;
    int speed = 13;
    Texture textureDownTube;
    int gapX;
    int gapWeigh = 500;
    Random random;
    int padding = 1;
    int distanceBetweenTubes;
    int x;

    public StumpObject(int stumpsCount, int stumpIdx, World world) {

        random = new Random();
        textureDownTube = new Texture(GameResources.NORMAL_STUMP_IMAGE);

        gapX = gapWeigh + padding + random.nextInt(SRC_WIDTH - 2 * (padding + gapWeigh / 2));
        distanceBetweenTubes = (SRC_WIDTH + width + random.nextInt(SRC_WIDTH + (padding + gapWeigh / 2))) / (stumpsCount - 1);
        x = distanceBetweenTubes * stumpIdx + SRC_WIDTH;

    }

    public void draw(Batch batch) {
        batch.draw(textureDownTube, x, 0, width, height);
    }

    public void dispose() {
        textureDownTube.dispose();
    }

    public void move() {
        x -= speed;
        if (x < -width) {
            x = SRC_WIDTH + distanceBetweenTubes;
            gapX = gapWeigh + padding + random.nextInt(SRC_WIDTH - 2 * (padding + gapWeigh / 2));
        }
    }

    public boolean isHit(AnjeObject a) {
//            if (anjeObject.y <= gapX - gapWeigh / 2 && anjeObject.x + anjeObject.width >= x && anjeObject.x <= x + width)
//                return true;
//            if (anjeObject.y + anjeObject.height >= gapX + gapWeigh / 2 && anjeObject.x + anjeObject.width >= x && anjeObject.x <= x + width)
//                return true;

        if (a.x >= x && a.x <= x + width && a.y >= 0 && a.y <= height) {
            return true;
        }
        return false;
    }

}
