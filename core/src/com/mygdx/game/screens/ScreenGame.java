package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.GameResources;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.objects.AnjeObject;
import com.mygdx.game.objects.StumpObject;
import components.ButtonView;
import components.MovingBackground;



public class ScreenGame extends ScreenAdapter {
    MyGdxGame myGdxGame;
   boolean isGameOver;
    AnjeObject anjeObject;
    StumpObject stumpObject;
    StumpObject[] stumps;
    int stumpsCount;
    MovingBackground background;
    ButtonView buttonStart;
    private OrthographicCamera camera;

    public ScreenGame(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        isGameOver = false;
        stumpsCount = 30;
        background = new MovingBackground(GameResources.BACKGROUND_FONE);
        initStumpObject();


    }

    @Override
    public void show() {
        this.anjeObject = new AnjeObject(1, 50, 10);
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {

            Vector3 touch = myGdxGame.camera.unproject(
                    new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)
            );

            if (buttonStart.isHit((int) touch.x, (int) touch.y)) {
                anjeObject.onClick();
            }
            for (StumpObject stump : stumps) {
                stump.move();
                if (stump.isHit(anjeObject)) {
                    System.out.println("HIT");
                    isGameOver = true;
                }
            }

            background.move();
            anjeObject.run();
            ScreenUtils.clear(1, 0, 0, 1);
            myGdxGame.camera.update();
            myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
            myGdxGame.batch.begin();

            background.draw(myGdxGame.batch);
            anjeObject.draw(myGdxGame.batch);
            buttonStart.draw(myGdxGame.batch);
            stumpObject.draw(myGdxGame.batch);
            myGdxGame.batch.end();
        }
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
        for (int i = 0; i < stumpsCount; i++) {
            stumps[i] = new StumpObject(stumpsCount, i, myGdxGame.world);
        }
    }
}