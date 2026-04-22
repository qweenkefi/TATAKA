package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.screens.ScreenGame;
import com.mygdx.game.screens.ScreenMenu;

import static com.mygdx.game.GameSettings.*;


public class MyGdxGame extends Game {
	public ScreenGame screenGame;
	public ScreenMenu screenMenu;

	public SpriteBatch batch;
	public OrthographicCamera camera;

	public World world;
	public Vector3 touch;
	float accumulator = 0;

	@Override
	public void create () {
		Box2D.init();
		world = new World(new Vector2(0, 0), true);
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SRC_WIDTH, SRC_HEIGHT);

		screenMenu = new ScreenMenu(this);
		screenGame = new ScreenGame(this);
		setScreen(screenMenu);
	}

	@Override
	public void render () {
	}

	public void stepWorld() {
		float delta = Gdx.graphics.getDeltaTime();
		accumulator += delta;
		if (accumulator >= STEP_TIME) {
			accumulator -= STEP_TIME;
			world.step(STEP_TIME, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}


}