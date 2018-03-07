package com.dom.steering.agents;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.dom.steering.tools.Vector;

public class Guard extends Agent {
	
	private float FOV = 120;
	private float degrees = 0;
	private int direction = -1;
	private Vector startPosition;
	private ShapeRenderer renderer;

	public Guard(String texture, float width, float height, float x, float y) {
		super(texture, width, height, x, y);
		setOriginCenter();
		this.renderer = new ShapeRenderer();
		this.renderer.identity();
 		this.renderer.translate(this.location.x + this.getWidth() / 2, this.location.y + this.getHeight() / 2, 0);
 		this.startPosition = new Vector(x, y);
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

	public void pursuit(){

	}

	public void drawView(float delta, OrthographicCamera camera){
		renderer.begin(ShapeRenderer.ShapeType.Filled);
		renderer.setColor(new Color(1, 0, 0, 0.2f));
		renderer.rotate(0, 0, 1, direction * delta * 50);
		renderer.arc(this.location.x + this.getWidth() / 2, this.location.y + this.getHeight() / 2, 300, 270, FOV);
		renderer.setProjectionMatrix(camera.combined);
		renderer.end();
	}

	public void rotateGuard(float delta){
		if (degrees > 270){
			rotate(direction * delta * 50);
			direction = (direction * -1);
			degrees = 0;
		} else {
			rotate(direction * delta * 50);
			degrees += (delta * 50);
		}
	}
}