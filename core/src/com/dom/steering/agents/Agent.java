package com.dom.steering.agents;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

import com.dom.steering.tools.Vector;
import com.dom.steering.objects.Building;

public class Agent extends Sprite {

	public Vector velocity, location, acceleration;
	protected int maxSpeed, maxForce, r;
	public Rectangle rect;

	public Agent(String texture, float width, float height, float x, float y){
		super(new Texture(texture));
		setSize(width, height); 
		setPosition(x, y);
		this.location = new Vector(x, y);
		this.velocity = new Vector(0, 0);
		this.acceleration = new Vector(0, 0);
		this.r = 6;
		this.maxSpeed = 6;
		this.maxForce = 1;
		this.rect = getBoundingRectangle();
	}

	public void applyForce(Vector force){
		acceleration = acceleration.add(force);
	}

	public void update(Building building){
		velocity = velocity.add(acceleration);
		limitSpeed();
		location = location.add(velocity);
		setPosition(this.location.x, this.location.y);
		acceleration = acceleration.multiply(0);
		this.rect = getBoundingRectangle();

		if (this.rect.overlaps(building.rect)){
			this.velocity = velocity.multiply(-2);
		}
	}

	protected void limitSpeed(){
		if (acceleration.x == 0 || acceleration.y == 0){
			velocity = velocity.subtract(new Vector(velocity.x / 16, velocity.y / 16));
		}
		if (velocity.x >= maxSpeed){
			velocity.x = maxSpeed;
		}
		if (velocity.x <= (-1 * maxSpeed)){
			velocity.x = (-1 * maxSpeed);
		}
		if (velocity.y >= maxSpeed){
			velocity.y = maxSpeed;
		}
		if (velocity.y <= (-1 * maxSpeed)){
			velocity.y = (-1 * maxSpeed);
		}
	}
}