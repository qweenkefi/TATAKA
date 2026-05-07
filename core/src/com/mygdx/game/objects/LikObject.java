package com.mygdx.game.objects;

import com.badlogic.gdx.physics.box2d.World;

public class LikObject extends GameObject {
    int livesLeft;

    LikObject(String texturePath, int width, int height, int x, int y, short cBits, World world) {
        super(texturePath, width, height, x, y, cBits, world);
        body.setLinearDamping(10);
        livesLeft = 2;}
        public void hit() {
            livesLeft -= 1;
        }
        public boolean isAlive() {
            return livesLeft > 0;
        }
    public int getLiveLeft() {

        return livesLeft;
    }



}
