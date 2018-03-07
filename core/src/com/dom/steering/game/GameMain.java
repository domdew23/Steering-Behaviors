package com.dom.steering.game;

import com.badlogic.gdx.Game;
import com.dom.steering.screens.GameScreen;

public class GameMain extends Game {
	
	public void create () {
		setScreen(new GameScreen());
	}

	public void render () {
		super.render();
	}
	
	public void dispose () {
		super.dispose();
	}

	public void pause(){
		super.pause();
	}

	public void resume(){
		super.resume();
	}
}
