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

	public Vector subtract(Vector u){
		return (new Vector(this.x - u.x, this.y - u.y));
	}

	public float dotProduct(Vector u){
		return ((this.x * u.x) + (this.y * u.y));
	}

	public float crossProduct(Vector u){
		return ((this.x * u.y) - (this.y * u.x));
	}

	public Vector multiply(int scalar){
		return (new Vector(this.x * scalar, this.y * scalar));
	}

	public Vector normalize(){
		return (new Vector(this.x / magnitude(), this.y / magnitude()));
	}

	public float magnitude(){
		return ((float) Math.sqrt(this.x * this.x + this.y * this.y));
	}
}