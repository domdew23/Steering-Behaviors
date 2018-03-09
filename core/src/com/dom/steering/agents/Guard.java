package com.dom.steering.agents;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.dom.steering.tools.Vector;
import com.badlogic.gdx.math.Vector2;

public class Guard extends Agent {
	
	private float FOV = 120;
	private float degrees = 0;
	private float fovDegrees = 0;
	private int direction = -1;
	private Vector startPosition;
	private Vector lineStart;
	private Vector lineEnd;
	private ShapeRenderer renderer;
	private boolean flipped = false;

	private enum State {
		GUARDING,
		CHASING,
		RETURNING,
		DEAD
	}

	public Guard(String texture, float width, float height, float x, float y) {
		super(texture, width, height, x, y);
		setOriginCenter();
		this.renderer = new ShapeRenderer();
		//this.renderer.identity();
 		//this.renderer.translate(this.location.x + this.getWidth() / 2, this.location.y + this.getHeight() / 2, 0);
 		this.startPosition = new Vector(x, y);
 		this.lineStart = new Vector(this.location.x + this.getWidth() / 2, this.location.y + this.getHeight() / 2);
 		this.lineEnd = new Vector(lineStart.x + 250, lineStart.y + 1);
 		this.maxSpeed = 4;
	}

	public void seek(Vector target){
		Vector desired = target.subtract(location);
		desired = desired.normalize();
		desired = desired.multiply(maxSpeed);
		Vector force = desired.subtract(velocity);
		applyForce(force);
	}

	public void arrive(){
	}

	public void avoid(){

	}

	public void drawView(float delta, OrthographicCamera camera, Player player){
		renderer.begin(ShapeRenderer.ShapeType.Filled);
		renderer.setColor(new Color(1, 0, 0, 0.2f));
		//renderer.arc(this.location.x + this.getWidth() / 2, this.location.y + this.getHeight() / 2, 300, degrees, FOV);
		
		//lineStart = new Vector2(this.location.x + this.getWidth() / 2, this.location.y + this.getHeight() / 2);
 		//lineEnd = new Vector2(lineStart.x - 250, lineStart.y - 250);
		
		renderer.line(lineStart.x, lineStart.y, lineEnd.x, lineEnd.y);
		System.out.println(lineEnd.dotProduct(player.location));
		renderer.rotate(0, 0, 1, direction * delta * 50);
		renderer.setProjectionMatrix(camera.combined);
		renderer.end();
	}

	public void rotateGuard(float delta){
		if (degrees > 270){
			rotate(direction * delta * 50);
			direction = (direction * -1);
			degrees = 0;
			if (flipped){
				flipped = false;
			} else {
				flipped = true;
			}
		} else {
			rotate(direction * delta * 50);
			degrees += (delta * 50);
			if (flipped){
				fovDegrees -= (delta * 50);
			} else {
				fovDegrees += (delta * 50);
			}
		}
	}
}