package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.GameResources;
import com.mygdx.game.MyGdxGame;
import components.ButtonView;
import components.ImageView;
import components.MovingBackground;
import components.TextView;
import managers.MemoryManager;

import java.util.ArrayList;

public class SettingsScreen extends ScreenAdapter {
    MyGdxGame myGdxGame;
    MovingBackground backgroundView;
    TextView titleTextView;
    ImageView blackoutImageView;
    ButtonView returnButton;
    TextView musicSettingView;
    TextView soundSettingView;
    TextView clearSettingView;;
    private String translateStateToText(boolean state) {
        return state ? "ON" : "OFF";
    }

public SettingsScreen(MyGdxGame myGdxGame) {
    this.myGdxGame = myGdxGame;
    backgroundView = new MovingBackground(GameResources.SETTINGS_BACKGROUND);

    titleTextView = new TextView(myGdxGame.largeWhiteFont, 256, 956, "Settings");
    blackoutImageView = new ImageView(0, 380, GameResources.BLACKOUT_FULL_IMG_PATH);
    clearSettingView = new TextView(myGdxGame.commonWhiteFont, 173, 640, "clear records");
    musicSettingView = new TextView(
            myGdxGame.commonWhiteFont,
            173, 790,
           "music: " + translateStateToText(MemoryManager.loadIsMusicOn())
    );

    soundSettingView = new TextView(
            myGdxGame.commonWhiteFont,
            173, 715,
            "sound: " + translateStateToText(MemoryManager.loadIsSoundOn())
    );

    returnButton = new ButtonView(
            220, 300,
            255, 130,
            myGdxGame.commonBlackFont,
            GameResources.BUTTON_PATH,
            "return"
    );

}
public void render(float delta) {
    handleInput();
    myGdxGame.camera.update();
    myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
    ScreenUtils.clear(Color.CLEAR);

    myGdxGame.batch.begin();

    backgroundView.draw(myGdxGame.batch);

    blackoutImageView.draw(myGdxGame.batch);
    returnButton.draw(myGdxGame.batch);
    musicSettingView.draw(myGdxGame.batch);
    soundSettingView.draw(myGdxGame.batch);
    titleTextView.draw(myGdxGame.batch);
    clearSettingView.draw(myGdxGame.batch);

    myGdxGame.batch.end();
}
    void handleInput() {
        if (Gdx.input.justTouched()) {
            myGdxGame.touch = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (returnButton.isHit(myGdxGame.touch.x, myGdxGame.touch.y)) {
                myGdxGame.setScreen(myGdxGame.screenMenu);
            }
            if (musicSettingView.isHit(myGdxGame.touch.x, myGdxGame.touch.y)) {
                MemoryManager.saveMusicSettings(!MemoryManager.loadIsMusicOn());
                musicSettingView.setText("music: " + translateStateToText(MemoryManager.loadIsMusicOn()));
                myGdxGame.audioManager.updateMusicFlag();
            }
            if (soundSettingView.isHit(myGdxGame.touch.x, myGdxGame.touch.y)) {
                MemoryManager.saveSoundSettings(!MemoryManager.loadIsSoundOn());
                soundSettingView.setText("sound: " + translateStateToText(MemoryManager.loadIsSoundOn()));
                myGdxGame.audioManager.updateSoundFlag();
            }
            //if (clearSettingView.isHit(myGdxGame.touch.x, myGdxGame.touch.y)) {
              //  MemoryManager.saveTableOfRecords(new ArrayList<>());
               // clearSettingView.setText("clear records (cleared)");

           // }


        } }
public void dispose() {
    super.dispose();
}
}
