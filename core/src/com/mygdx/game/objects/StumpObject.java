package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.GameResources;

import java.util.Random;

import static com.mygdx.game.GameSettings.SRC_HEIGHT;
import static com.mygdx.game.GameSettings.SRC_WIDTH;

public class StumpObject {
    int width = 400;
    int height = 500;
    int speed = 10;
    Texture textureDownTube;
    int gapY;
    int gapHeight = 400;
    Random random;
    int padding = 100;
    int distanceBetweenTubes;
    int x;

    public StumpObject(int stumpsCount, int stumpIdx, World world) {

            random = new Random();
            textureDownTube = new Texture(GameResources.NORMAL_STUMP_IMAGE);

            gapY = gapHeight / 2 + padding + random.nextInt(SRC_HEIGHT - 2 * (padding + gapHeight / 2));
            distanceBetweenTubes = (SRC_WIDTH + width) / (stumpsCount - 1);
        x = distanceBetweenTubes * stumpIdx + SRC_WIDTH;

        }
        public void draw (Batch batch){
            batch.draw(textureDownTube, x, gapY - gapHeight / 2 - height, width, height);
        }
        public void dispose () {
            textureDownTube.dispose();
        }
        public void move () {
            x -= speed;
            if (x < -width) {
                x = SRC_WIDTH + distanceBetweenTubes;
                gapY = gapHeight / 2 + padding + random.nextInt(SRC_HEIGHT - 2 * (padding + gapHeight / 2));
            }
        }
        public boolean isHit (AnjeObject anjeObject){
            if (anjeObject.y <= gapY - gapHeight / 2 && anjeObject.x + anjeObject.width >= x && anjeObject.x <= x + width)
                return true;
            if (anjeObject.y + anjeObject.height >= gapY + gapHeight / 2 && anjeObject.x + anjeObject.width >= x && anjeObject.x <= x + width)
                return true;
            return false;
        }

    }
