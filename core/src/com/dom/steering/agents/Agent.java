package com.dom.steering.agents;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

import com.dom.steering.tools.Vector;
import com.dom.steering.objects.Building;
import com.badlogic.gdx.math.Vector2;

public class Agent extends Sprite {

	public Vector2 velocity, location, acceleration, direction;
	protected int maxSpeed, maxForce;
	public Rectangle rect;

	public Agent(String texture, float width, float height, float x, float y){
		super(new Texture(texture));
		setSize(width, height); 
		setPosition(x, y);
		this.location = new Vector2(x, y);
		this.velocity = new Vector2(0, 0);
		this.acceleration = new Vector2(0, 0);
		this.direction = new Vector2(x, y);
		this.maxSpeed = 6;
		this.maxForce = 1;
		this.rect = getBoundingRectangle();
	}

	public void applyForce(Vector2 force){
		this.acceleration.add(force);
	}

	public void update(Building building){
		this.rect = getBoundingRectangle();
		
		if (this.rect.overlaps(building.rect)){
			this.velocity = velocity.scl(-5);
		}

		this.velocity.add(this.acceleration);
		limitSpeed();
		this.location.add(this.velocity);
		setPosition(this.location.x, this.location.y);
		this.acceleration.scl(0);
	}

	protected void limitSpeed(){
		if (acceleration.x == 0 || acceleration.y == 0){
			velocity.sub(velocity.x / 16, velocity.y / 16);
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