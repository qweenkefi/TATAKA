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
    TextView clearSettingView;
    private String translateStateToText(boolean state) {
        return state ? "ON" : "OFF";
    }

public SettingsScreen(MyGdxGame myGdxGame) {
    this.myGdxGame = myGdxGame;
    backgroundView = new MovingBackground(GameResources.BACKGROUND_MENU);

    titleTextView = new TextView(myGdxGame.largeWhiteFont, 256, 956, "Settings");
    blackoutImageView = new ImageView(85, 365, GameResources.BLACKOUT_MIDDLE_IMG_PATH);
    clearSettingView = new TextView(myGdxGame.commonWhiteFont, 173, 540, "clear records");
    musicSettingView = new TextView(
            myGdxGame.commonWhiteFont,
            173, 658
           // "music: " + translateStateToText(MemoryManager.loadIsMusicOn())
    );
}
public void render(float delta) {
    handleInput();
    myGdxGame.camera.update();
    myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
    ScreenUtils.clear(Color.CLEAR);

    myGdxGame.batch.begin();
    backgroundView.draw(myGdxGame.batch);
    titleTextView.draw(myGdxGame.batch);
    blackoutImageView.draw(myGdxGame.batch);
    clearSettingView.draw(myGdxGame.batch);
    myGdxGame.batch.end();
}
void handleInput(){
    if (Gdx.input.justTouched()) {
        myGdxGame.touch = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

      //  if (clearSettingView.isHit(myGdxGame.touch.x, myGdxGame.touch.y)) {
      //      MemoryManager.saveTableOfRecords(new ArrayList<>());
       //     clearSettingView.setText("clear records (cleared)");




        }



}
public void dispose() {
    super.dispose();
}
}
