package com.dom.steering.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Game;
import com.dom.steering.screens.GameScreen;

import com.badlogic.gdx.math.Vector2;

public class GameOverScreen implements Screen {
	Texture img;
	SpriteBatch batch;

	public GameOverScreen(){
		img = new Texture("game_over.png");
		batch = new SpriteBatch();
	}

	public void show(){

	}

	public void render(float delta){
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	
		batch.begin();
		batch.draw(img, -250, -50);
		batch.end();

		if(Gdx.input.isKeyPressed(Keys.ENTER)) {
			((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen());
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