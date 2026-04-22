package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.MyGdxGame;

public class MovingBackground {
    Texture texture;
    int texture1X, texture2X;
    int speed = 2;
    public MovingBackground(String pathToTexture){
        texture1X = 0;
        texture2X = MyGdxGame.SRC_WIDTH;
        texture = new Texture(pathToTexture);
    }
    public void move(){
        texture1X -= speed;
        texture2X -= speed;
        if (texture1X <= -MyGdxGame.SRC_WIDTH) {
            texture1X = MyGdxGame.SRC_WIDTH;
        }
        if (texture2X <= -MyGdxGame.SRC_WIDTH){
            texture2X = MyGdxGame.SRC_WIDTH;
        }
    }
    public void draw(Batch batch){
        batch.draw(texture,texture1X,0,MyGdxGame.SRC_WIDTH,MyGdxGame.SRC_HEIGHT);
        batch.draw(texture, texture2X, 0, MyGdxGame.SRC_WIDTH, MyGdxGame.SRC_HEIGHT);
    }

    public void dispose() {
        texture.dispose();
    }
}
