package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.objects.AnjeObject;
import com.mygdx.game.objects.StumpObject;
import components.MovingBackground;


public class ScreenGame implements Screen {
    MyGdxGame myGdxGame;
    AnjeObject anjeObject;

    MovingBackground background;

    public ScreenGame (MyGdxGame myGdxGame){
        this.myGdxGame = myGdxGame;
        background = new MovingBackground("backgrounds/forestBackgroundOne.png");
        initStumpObject();


    }
    @Override
    public void show() {
        this.anjeObject = new AnjeObject(1,50,10);
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            anjeObject.onClick();
        }
        background.move();
        ScreenUtils.clear(1, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();

        background.draw(myGdxGame.batch);
        anjeObject.draw(myGdxGame.batch);
        myGdxGame.batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
    void initStumpObject() {
        stumps = new StumpObject[stumpsCount];
        for (int i = 0; i < stumpCount; i++) {
            stumps[i] = new StumpObject(stumpsCount, i);
        }
    }

}
