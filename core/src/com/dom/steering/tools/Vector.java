package com.dom.steering.tools;

public class Vector {

	private float[] vector;
	public float x, y;

	public Vector(float x, float y){
		this.x = x;
		this.y = y;
		this.vector = new float[2];
		this.vector[0] = x;
		this.vector[1] = y;
	}

	public Vector add(Vector u){
		return (new Vector(this.x + u.x, this.y + u.y));
	}

	public Vector sub(Vector u){
		return (new Vector(this.x - u.x, this.y - u.y));
	}

	public float dot(Vector u){
		return ((this.x * u.x) + (this.y * u.y));
	}

	public float crossProduct(Vector u){
		return ((this.x * u.y) - (this.y * u.x));
	}

	public Vector scl(int scalar){
		return (new Vector(this.x * scalar, this.y * scalar));
	}

	public Vector nor(){
		return (new Vector(this.x / magnitude(), this.y / magnitude()));
	}

	public float magnitude(){
		return ((float) Math.sqrt(this.x * this.x + this.y * this.y));
	}

	public float angle(){
		float angle = (float) Math.toRadians((double) Math.atan2(y, x));
		return angle;
	}

	public Vector setAngle(float degrees){
		float radians = (float) Math.toRadians((double) degrees);
		float cos = (float) Math.cos(radians);
		float sin = (float) Math.sin(radians);

		float newX = this.x * cos - this.y * sin;
		float newY = this.x * sin + this.y * cos;

		this.x = newX;
		this.y = newY;

		return this;
	}
}