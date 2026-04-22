package com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import components.MovingBackground;


public class ScreenGame implements Screen {
    private MyGdxGame myGdxGame;

    MovingBackground background;

    public ScreenGame (MyGdxGame myGdxGame){
        this.myGdxGame = myGdxGame;
        background = new MovingBackground("backgrounds/forestBackgroundOne.png");


    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        background.move();
        ScreenUtils.clear(1, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();

        background.draw(myGdxGame.batch);
        myGdxGame.batch.end();
        // eeeff

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
}
