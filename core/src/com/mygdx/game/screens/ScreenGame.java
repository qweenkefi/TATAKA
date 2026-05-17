package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.GameResources;
import com.mygdx.game.GameSettings;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.objects.AnjeObject;
import com.mygdx.game.objects.BulletObject;
import com.mygdx.game.objects.MonsterObject;
import com.mygdx.game.objects.StumpObject;
import components.ButtonView;
import components.MovingBackground;

import java.util.ArrayList;

import static com.mygdx.game.GameResources.BULLET_IMG_PATH;


public class ScreenGame extends ScreenAdapter {
    MyGdxGame myGdxGame;
    boolean isGameOver;
    AnjeObject anjeObject;
    StumpObject[] stumps;
    MonsterObject[] monsters;
    ArrayList<BulletObject> bullets;
    ButtonView pauseButton;
    int stumpsCount = 3;
    int monstersCount = 3;
    int bulletsCount = 3;
    MovingBackground background;
    ButtonView pleeButtonView;
    ButtonView jumpButtonView;

    public ScreenGame(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        isGameOver = false;
        background = new MovingBackground(GameResources.BACKGROUND_FONE);
        initStumpObject();
        initMonsterObject();
        initBulletObject();
        bullets = new ArrayList<>();
        pleeButtonView = new ButtonView(600, 100, 120, 200, myGdxGame.commonBlackFont, GameResources.ATTACK_BUTTON);
        jumpButtonView = new ButtonView(20, 100, 120, 200, myGdxGame.commonBlackFont, GameResources.JUMP_BUTTON);

        pauseButton = new ButtonView(590, 1120, 130, 130, myGdxGame.commonBlackFont, GameResources.PAUSE_IMG_PATH);


    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            myGdxGame.touch = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (pleeButtonView.isHit(myGdxGame.touch.x, myGdxGame.touch.y)) {
                BulletObject bo = new BulletObject(
                        anjeObject.getX(), anjeObject.getY() + anjeObject.height / 2,
                        GameSettings.BULLET_WIDTH, GameSettings.BULLET_HEIGHT,
                        BULLET_IMG_PATH,
                        myGdxGame.world
                );
                bullets.add(bo);
            }
            if (jumpButtonView.isHit(myGdxGame.touch.x, myGdxGame.touch.y)) {
                anjeObject.onClick();
            }
        }
    }

    @Override
    public void show() {
        this.anjeObject = new AnjeObject(1, 50, 10);
    }

    @Override
    public void render(float delta) {
        handleInput();
        for (StumpObject stump : stumps) {
            stump.move();
            if (stump.isHit(anjeObject)) {
                System.out.println("HIT");
                isGameOver = true;
            }
        }


        for (MonsterObject monster : monsters) {
            monster.move();
        }
        for (int i = 0; i < bullets.size(); i++) {
            BulletObject bullet = bullets.get(i);
            bullet.move();
            for (int j = 0; j < monsters.length; j++) {
                MonsterObject monster = monsters[j];
                if (bullet.isHit(monster)) {
                    System.out.println("HIT MONSTER");
                    isGameOver = true;
                    bullets.remove(i--);
                    bullet.dispose();
                    monsters[i] = null;
                    monsters[i].dispose();
                }
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
        pleeButtonView.draw(myGdxGame.batch);
        jumpButtonView.draw(myGdxGame.batch);
        pauseButton.draw(myGdxGame.batch);
        for (int i = 0; i < stumpsCount; i++) {
            stumps[i].draw(myGdxGame.batch);
        }
        for (int i = 0; i < monstersCount; i++) {
            monsters[i].draw(myGdxGame.batch);
        }
        for (BulletObject b: bullets) {
            b.draw(myGdxGame.batch);
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

    void initBulletObject() {
        bullets = new ArrayList<>();

    }

    void initMonsterObject() {
        monsters = new MonsterObject[monstersCount];

        for (int i = 0; i < stumpsCount; i++) {
            monsters[i] = new MonsterObject(monstersCount, i, myGdxGame.world);
        }
    }
}