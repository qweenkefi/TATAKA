package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
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
import components.ImageView;
import components.MovingBackground;
import components.TextView;

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
    ButtonView homeButton;
    ButtonView continueButton;
    TextView pauseTextView;
    GameSession gameSession;
    MovingBackground settingsBackground;

    public ScreenGame(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        isGameOver = false;
        background = new MovingBackground(GameResources.BACKGROUND_FONE);
        gameSession = new GameSession();
        initStumpObject();
        initMonsterObject();
        initBulletObject();
        bullets = new ArrayList<>();
        pleeButtonView = new ButtonView(600, 100, 120, 200, myGdxGame.commonBlackFont, GameResources.ATTACK_BUTTON);
        jumpButtonView = new ButtonView(20, 100, 120, 200, myGdxGame.commonBlackFont, GameResources.JUMP_BUTTON);
        homeButton = new ButtonView(
                200, 365,
                300, 200,
                myGdxGame.commonBlackFont,
                GameResources.BUTTON_PATH,
                "Home"
        );
        continueButton = new ButtonView(
                200, 600,
                300, 200,
                myGdxGame.commonBlackFont,
                GameResources.BUTTON_PATH,
                "Continue"
        );
        pauseButton = new ButtonView(590, 1120, 130, 130, myGdxGame.commonBlackFont, GameResources.PAUSE_IMG_PATH);
        pauseTextView = new TextView(myGdxGame.largeWhiteFont, 282, 842, "Pause");
        settingsBackground = new MovingBackground( GameResources.SETTINGS_BACKGROUND);


    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            myGdxGame.touch = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            switch (gameSession.state){
                case PLAYING:
                    if (pauseButton.isHit(myGdxGame.touch.x, myGdxGame.touch.y)) {
                        gameSession.pauseGame();

                    }
                case PAUSED:
                    if (continueButton.isHit(myGdxGame.touch.x, myGdxGame.touch.y)) {
                        gameSession.resumeGame();
                    }
                    if (homeButton.isHit(myGdxGame.touch.x, myGdxGame.touch.y)) {
                        myGdxGame.setScreen(myGdxGame.screenMenu);
                    }

            }

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
        restartGame();
    }

    @Override
    public void render(float delta) {
        handleInput();
        if (gameSession.state == GameState.PLAYING){

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





    }
        draw();
    }
    private void draw(){
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        ScreenUtils.clear(Color.CLEAR);

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

        if (gameSession.state == GameState.PAUSED) {
            settingsBackground.draw(myGdxGame.batch);
            pauseTextView.draw(myGdxGame.batch);
            homeButton.draw(myGdxGame.batch);
            continueButton.draw(myGdxGame.batch);

        }
        else if (gameSession.state == GameState.ENDED) {
            settingsBackground.draw(myGdxGame.batch);
            //recordsTextView.draw(myGdxGame.batch);
            //recordsListView.draw(myGdxGame.batch);
            homeButton.draw(myGdxGame.batch);
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
    private void restartGame() {

        gameSession.startGame();
    }
}