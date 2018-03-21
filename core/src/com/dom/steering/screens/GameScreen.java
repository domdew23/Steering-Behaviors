package com.dom.steering.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.Input.Keys;

import com.dom.steering.objects.Building;
import com.dom.steering.agents.Guard;
import com.dom.steering.agents.Player;
import com.dom.steering.tools.Vector;
import com.badlogic.gdx.math.Vector2;

public class GameScreen implements Screen {
	
	private Building building;
	private SpriteBatch batch;
	private Guard guard;
	private Player player;
	private OrthographicCamera camera;

	public GameScreen(){
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		building = new Building("building.png", 100, 150, 400, 200, Color.BLUE);
		batch = new SpriteBatch();
		guard = new Guard("guard.png", 100, 100, -80, -40);
		player = new Player("player.png", 100, 100, -300, 400);
		camera.position.x += 200;
		camera.position.y += 100;
	}

	public void show(){

	}

	public void render(float delta){
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    	Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);		
		guard.drawView(delta, camera, player);
		
		batch.begin();
		handleInput(delta);
		update(delta);
		draw(delta);
		batch.end();
	}

	private void update(float delta){
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		guard.checkState(delta, player);
		player.update(building);
		guard.update(building);
	}

	private void draw(float delta){
		guard.draw(batch);
		player.draw(batch);
		building.draw(batch);
	}

	private void handleInput(float delta){
		if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			player.applyForce(new Vector2(0, -0.5f));
		}
		if(Gdx.input.isKeyPressed(Keys.UP)) {
			player.applyForce(new Vector2(0, 0.5f));
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			player.applyForce(new Vector2(-0.5f, 0));
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			player.applyForce(new Vector2(0.5f, 0));
		}
	}

	public void pause(){

	}

	public void resume(){

	}

	public void hide(){

	}

	public void dispose(){
		
	}

	public void resize(int width, int height) {
		
	}
}