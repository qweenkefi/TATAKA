package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.GameResources;
import com.mygdx.game.MyGdxGame;
import components.ButtonView;
import components.ImageView;
import components.MovingBackground;
import components.TextView;

public class StoryScreen extends ScreenAdapter {
    MyGdxGame myGdxGame;
    ButtonView fisrtPicture;
    ButtonView secondPicture;
    ButtonView thirdPicture;
    ButtonView fourthPicture;
    TextView firstPhrase;
    TextView secondPhrase;
    TextView ThirdPhrase;

    boolean needDrawSecPicture;
    boolean needDrawThiPicture;


    public StoryScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        fisrtPicture = new ButtonView(-50, 0, 850, 1300, myGdxGame.commonBlackFont, GameResources.FIRST_PICTURE);
        secondPicture = new ButtonView(-50, 0, 850, 1300, myGdxGame.commonBlackFont, GameResources.SECOND_PICTURE);
        thirdPicture = new ButtonView(-50, 0, 850, 1300, myGdxGame.commonBlackFont, GameResources.THIRD_PICTURE);

        firstPhrase = new TextView(myGdxGame.largeWhiteFont, 5, 1080, "This story has started many years ago.\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n A lot before these children were born...");
    }


    @Override
    public void render(float delta) {
        handleInput();
        super.render(delta);
        draw();
    }

    public void draw() {

        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        ScreenUtils.clear(Color.CLEAR);
        myGdxGame.batch.begin();

        fisrtPicture.draw(myGdxGame.batch);
        if (needDrawSecPicture) {
            secondPicture.draw(myGdxGame.batch);
        }
        if (needDrawThiPicture) {
            thirdPicture.draw(myGdxGame.batch);
        }
        firstPhrase.draw(myGdxGame.batch);
        myGdxGame.batch.end();

    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            myGdxGame.touch = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (fisrtPicture.isHit(myGdxGame.touch.x, myGdxGame.touch.y) && !needDrawSecPicture) {
                ScreenUtils.clear(Color.CLEAR);
                needDrawSecPicture = true;
                firstPhrase = new TextView(myGdxGame.largeWhiteFont, 5, 1080, "Prodo");
            } else if (secondPicture.isHit(myGdxGame.touch.x, myGdxGame.touch.y)) {
                ScreenUtils.clear(Color.CLEAR);
                needDrawThiPicture = true;
                firstPhrase = new TextView(myGdxGame.largeWhiteFont, 5, 1080, "His race was ashamed of his sins");


            }


        }
    }


    @Override
    public void dispose() {
        super.dispose();
    }
}
