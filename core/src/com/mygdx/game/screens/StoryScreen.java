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
    MovingBackground firstPicture;
    MovingBackground secondPicture;
    MovingBackground thirdPicture;
    TextView firstPhrase;
    TextView secondPhrase;
    TextView ThirdPhrase;
    ButtonView continueButtonView;


    public StoryScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        //firstPicture = new MovingBackground (GameResources.FIRST_PICTURE);
        secondPicture = new MovingBackground (GameResources.SECOND_PICTURE);
        thirdPicture = new MovingBackground (GameResources.THIRD_PICTURE);
        continueButtonView = new ButtonView(-50, 0, 850, 1300, myGdxGame.commonBlackFont, GameResources.FIRST_PICTURE);
        firstPhrase = new TextView(myGdxGame.largeWhiteFont, 5,1080, "This story has started many years ago.\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n A lot before these children were born...");
    }


    @Override
    public void render(float delta) {
        handleInput();
        super.render(delta);
        draw();
    }
    public void draw(){

        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        ScreenUtils.clear(Color.CLEAR);
        myGdxGame.batch.begin();

        //firstPicture.draw(myGdxGame.batch);
        continueButtonView.draw(myGdxGame.batch);

        //thirdPicture.draw(myGdxGame.batch);
        firstPhrase.draw(myGdxGame.batch);
        myGdxGame.batch.end();

    }
    private void handleInput(){
        if (Gdx.input.justTouched()) {
            myGdxGame.touch = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (continueButtonView.isHit(myGdxGame.touch.x, myGdxGame.touch.y)){ScreenUtils.clear(Color.CLEAR);
                thirdPicture.draw(myGdxGame.batch);

            }


            }}



    @Override
    public void dispose() {
        super.dispose();
    }
}
