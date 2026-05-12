package com.mygdx.game.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.GameResources;
import com.mygdx.game.MyGdxGame;
import components.ButtonView;
import components.ImageView;
import components.MovingBackground;
import components.TextView;

public class SettingsScreen extends ScreenAdapter {
    MyGdxGame myGdxGame;
    MovingBackground backgroundView;
    TextView titleTextView;
    ImageView blackoutImageView;
    ButtonView returnButton;
    TextView musicSettingView;
    TextView soundSettingView;
    TextView clearSettingView;
public SettingsScreen(MyGdxGame myGdxGame) {
    this.myGdxGame = myGdxGame;
    backgroundView = new MovingBackground(GameResources.BACKGROUND_MENU);
}
public void render(float delta) {
    handleInput();
    myGdxGame.camera.update();
    myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
    ScreenUtils.clear(Color.CLEAR);
}
void handleInput(){

}
public void dispose() {
    super.dispose();
}
}
