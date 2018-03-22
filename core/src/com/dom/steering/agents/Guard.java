package com.dom.steering.agents;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.dom.steering.screens.GameOverScreen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;

public class Guard extends Agent {
	
	private float FOV = 120;
	private float degrees = 0;
	private float fovDegrees = 0;
	private int rotation = -1;
	private Vector2 startPosition;
	private Vector2 playerDirection;
	private ShapeRenderer renderer;
	private boolean flipped = false;
	private float EPSILON = 4.0f;
	private float COLLISIONEPISLON = 4.0f;
	private float rotationSpeed = 25f;
	private boolean hit = false;

	private enum State {
		GUARDING,
		CHASING,
		RETURNING,
		DEAD
	}

	private State CURRENT;

	public Guard(String texture, float width, float height, float x, float y) {
		super(texture, width, height, x, y);
		setOriginCenter();
		this.renderer = new ShapeRenderer();
 		this.startPosition = new Vector2(x, y);
 		this.playerDirection = new Vector2(0, 0);
 		this.maxSpeed = 4;
 		setRotation(fovDegrees);
 		direction.setAngle(fovDegrees);
		CURRENT = State.GUARDING;
	}

	public void checkState(float delta, Player player){
		Vector2 v = player.location.cpy().sub(location);
		playerDirection = v.cpy().nor();
		float angle = playerDirection.angle(direction);
		
		if (Math.abs(angle) < FOV / 2 && v.len2() < 112000){
			CURRENT = State.CHASING;
		}

		if ((Math.abs(angle) > FOV / 2 || v.len2() > 112000) && CURRENT != State.GUARDING){
			CURRENT = State.RETURNING;
		}

		if (this.rect.overlaps(player.rect)){
			CURRENT = State.DEAD;
		}
		
		if (Math.abs(location.x - startPosition.x) < EPSILON && Math.abs(location.y - startPosition.y) < EPSILON && CURRENT == State.RETURNING) CURRENT = State.GUARDING;

		updateState(delta, player);
	}

	private void updateState(float delta, Player player){
		switch (CURRENT){
			case GUARDING: rotateGuard(delta); break;
			case CHASING: seek(player.location.cpy().add(player.velocity.cpy().scl(3))); break;
			case RETURNING: seek(startPosition.cpy()); break;
			case DEAD: ((Game) Gdx.app.getApplicationListener()).setScreen(new GameOverScreen()); break;
			default: break;
		}
	}

	public void seek(Vector2 target){	
		float angle = location.cpy().sub(target).nor().angle();
		angle += 180;
		setRotation(angle);
		direction.setAngle(angle);
		fovDegrees = angle;

		Vector2 desired = target.cpy().sub(location);
		desired.nor();
		desired.scl(maxSpeed);
		Vector2 force = desired.sub(velocity);
		force.limit(maxForce);
		applyForce(force);
	}

	public void drawView(float delta, OrthographicCamera camera, Player player){
		renderer.begin(ShapeRenderer.ShapeType.Filled);
		renderer.setColor(new Color(1, 0, 0, 0.2f));
		renderer.arc(location.x + getWidth() / 2, location.y + getHeight() / 2, 300, fovDegrees - 60, FOV);
		renderer.setProjectionMatrix(camera.combined);
		renderer.end();
	}

	public void rotateGuard(float delta){
		if (degrees > 270){
			rotation = (rotation * -1);
			degrees = 0;
			if (flipped){
				flipped = false;
			} else {
				flipped = true;
			}
		} else {
			degrees += (delta * rotationSpeed);
			if (flipped){
				fovDegrees += (delta * rotationSpeed);
			} else {
				fovDegrees -= (delta * rotationSpeed);
			}
		}
		direction.setAngle(fovDegrees).nor();
		setRotation(direction.angle());
	}
}