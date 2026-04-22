package com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.objects.MovingBackground;

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
        background.draw(myGdxGame.batch);
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
}
