package com.dom.steering.agents;

public class FOV extends Agent {
	private float degrees = 0;
	private int direction = -1;

	public FOV(String texture, float width, float height, float x, float y){
		super(texture, width, height, x, y);
		setOriginCenter();
	}

	public void update(float delta){
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