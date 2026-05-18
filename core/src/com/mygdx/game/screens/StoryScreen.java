package com.mygdx.game.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.GameResources;
import com.mygdx.game.MyGdxGame;
import components.ButtonView;
import components.ImageView;
import components.MovingBackground;
import components.TextView;

public class StoryScreen extends ScreenAdapter {
    MyGdxGame myGdxGame;
    MovingBackground firstPicture;
    TextView firstPhrase;


    public StoryScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        firstPicture = new MovingBackground (GameResources.FIRST_PICTURE);
        firstPhrase = new TextView(myGdxGame.largeWhiteFont, 5,1080, "This story has started many years ago.\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n A lot before these children were born...");
    }


    @Override
    public void render(float delta) {
        super.render(delta);
        draw();
    }
    public void draw(){

        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        ScreenUtils.clear(Color.CLEAR);
        myGdxGame.batch.begin();

        firstPicture.draw(myGdxGame.batch);
        firstPhrase.draw(myGdxGame.batch);
        myGdxGame.batch.end();

    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
