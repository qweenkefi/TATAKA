package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.screens.ScreenGame;
import com.mygdx.game.screens.ScreenMenu;

public class MyGdxGame extends ApplicationAdapter {
	public ScreenGame screenGame;
	public ScreenMenu screenMenu;
	public static final int SRC_WIDTH = 1280;
	public static final int SRC_HEIGHT = 720;
	public OrthographicCamera camera;
	
	@Override
	public void create () {

	}

	@Override
	public void render () {

	}
	
	@Override
	public void dispose () {

	}
}
