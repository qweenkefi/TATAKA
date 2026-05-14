package com.mygdx.game.objects;

import com.badlogic.gdx.physics.box2d.World;

import java.util.Random;

public class MonsterObject extends StumpObject {

    int width = 150;
    int height =200;
    int speed = 7;
    Random random;
    int padding = 100;
    int x;

    public MonsterObject(int stumpsCount, int stumpIdx, World world) {
        super(stumpsCount, stumpIdx, world);
    }


    public boolean isHit(AnjeObject a) {

        if (a.x >= x && a.x <= x + width && a.y >= 0 && a.y <= height) {
            return true;
        }
        return false;
    }

}
