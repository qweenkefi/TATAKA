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
import com.mygdx.game.objects.BulletObject;
import com.mygdx.game.objects.MonsterObject;
import com.mygdx.game.objects.StumpObject;
import components.ButtonView;
import components.MovingBackground;



public class ScreenGame extends ScreenAdapter {
    MyGdxGame myGdxGame;
   boolean isGameOver;
    AnjeObject anjeObject;
    StumpObject[] stumps;
    MonsterObject[] monsters;
    int stumpsCount = 3;
    int monstersCount = 3;
    MovingBackground background;
    ButtonView pleeButtonView;

    public ScreenGame(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        isGameOver = false;
        background = new MovingBackground(GameResources.BACKGROUND_FONE);
        initStumpObject();
        initMonsterObject();
        pleeButtonView = new ButtonView(20, 1300, 300, 200, myGdxGame.commonBlackFont, GameResources.BUTTON_PATH, "plee");


    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            myGdxGame.touch = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (pleeButtonView.isHit(myGdxGame.touch.x, myGdxGame.touch.y)) {
                BulletObject.(myGdxGame.screenGame);
            }
        }
        }

    @Override
    public void show() {
        this.anjeObject = new AnjeObject(1, 50, 10);
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            anjeObject.onClick();
        }
            for (StumpObject stump : stumps) {
                stump.move();
                if (stump.isHit(anjeObject)) {
                    System.out.println("HIT");
                    isGameOver = true;
                }
            }


        for (MonsterObject monster : monsters) {
            monster.move();
            if (monster.isHit(anjeObject)) {
                System.out.println("HIT MONSTER");
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
            for (int i = 0; i < stumpsCount; i++) {
                stumps[i].draw(myGdxGame.batch);
            }
            for (int i = 0; i < monstersCount; i++) {
                monsters[i].draw(myGdxGame.batch);
            }
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

        for (int i = 0; i < stumpsCount; i++) {
            stumps[i] = new StumpObject(stumpsCount, i, myGdxGame.world);
        }
    }
    void initMonsterObject() {
        monsters = new MonsterObject[monstersCount];

        for (int i = 0; i < stumpsCount; i++) {
            monsters[i] = new MonsterObject(monstersCount, i, myGdxGame.world);
        }
    }
}