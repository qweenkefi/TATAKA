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
    int speed = 2;
    Random random;
    int padding = 100;
    int x;
    int y = 300;
    Texture[] framesArray;
    int frameCounter;

    public MonsterObject(int monstersCount, int monsterIdx, World world) {
        super(monstersCount, monsterIdx);

        random = new Random();
        frameCounter = 0;
        framesArray = new Texture[]{
                new Texture("monster0.png"),
                new Texture("monster1.png"),
                new Texture("monster2.png"),
        };

        gapX = (gapWeigh + padding) * 3 + random.nextInt(SRC_WIDTH * (padding + gapWeigh));
        x = monsterIdx + SRC_WIDTH;
    }

    public void draw(Batch batch) {
        int frameMultiplier = 15;
        batch.draw(framesArray[frameCounter/frameMultiplier],x,y,width,height);
        if (frameCounter++ == framesArray.length * frameMultiplier - 1) frameCounter = 0;
    }

     public void dispose() {

     }

    public void move() {
        x -= speed;
        if (x < -width) {
            x = SRC_WIDTH;
            gapX = gapWeigh + padding + random.nextInt(SRC_WIDTH - 2 * (padding + gapWeigh / 2));
        }
    }

    public boolean isHit(AnjeObject a) {

        if (a.x + width >= x && a.x <= x + width && a.y + height >= y && a.y <= y+ height) {
            return true;
        }
        return false;
    }

}
