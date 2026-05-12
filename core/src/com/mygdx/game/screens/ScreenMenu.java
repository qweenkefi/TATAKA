package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.GameResources;
import com.mygdx.game.MyGdxGame;
import components.ButtonView;
import components.MovingBackground;
import components.TextView;


public class ScreenMenu extends ScreenAdapter {
    Texture texture;
    MovingBackground background;
    MyGdxGame myGdxGame;
    TextView titleView;
    ButtonView startButtonView;
    ButtonView settingsButtonView;
    ButtonView exitButtonView;

    public ScreenMenu(MyGdxGame myGdxGame){
        this.myGdxGame = myGdxGame;
        background = new MovingBackground("backgrounds/menuBackground.png");
        startButtonView = new ButtonView(-80, 400, 880, 800, myGdxGame.commonBlackFont, GameResources.BUTTON_PATH, "Начать");
        settingsButtonView = new ButtonView(100, 551, 440, 70, myGdxGame.commonBlackFont, GameResources.BUTTON_PATH, "Настройки");
        exitButtonView = new ButtonView(140, 456, 440, 70, myGdxGame.commonBlackFont, GameResources.BUTTON_PATH, "Выход");



    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        handleInput();
        ScreenUtils.clear(1, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();
        background.draw(myGdxGame.batch);
        exitButtonView.draw(myGdxGame.batch);
        settingsButtonView.draw(myGdxGame.batch);
        startButtonView.draw(myGdxGame.batch);

        myGdxGame.batch.end();
    }
    private void handleInput() {
        if (Gdx.input.justTouched()) {
            myGdxGame.touch = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (startButtonView.isHit(myGdxGame.touch.x, myGdxGame.touch.y)) {
                myGdxGame.setScreen(myGdxGame.screenGame);
            }
            if (exitButtonView.isHit(myGdxGame.touch.x, myGdxGame.touch.y)) {
                Gdx.app.exit();
            }
            if (settingsButtonView.isHit(myGdxGame.touch.x, myGdxGame.touch.y)) {
                myGdxGame.setScreen(myGdxGame.settingsScreen);
            }}
}



    @Override
    public void dispose() {

    }
}
