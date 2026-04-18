package com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;

public class ScreenMenu implements Screen {
    Texture texture;
    MovingBackground background;
    MyGdxGame myGdxGame;
    public ScreenMenu(MyGdxGame myGdxGame){
        this.myGdxGame = myGdxGame;
        background = new MovingBackground("backgrounds/menuBackground.png");

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
