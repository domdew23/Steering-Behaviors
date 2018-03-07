package com.dom.steering.objects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Building {
	
	private float x, y, width, height;
	private Color color;
	private ShapeRenderer renderer;

	public Building(float x, float y, float width, float height, Color color){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		this.renderer = new ShapeRenderer();
	}

	public void draw(OrthographicCamera camera){
		renderer.begin(ShapeRenderer.ShapeType.Line);
		renderer.setColor(color);
		renderer.rect(x, y, width, height);
		renderer.setProjectionMatrix(camera.combined);
		renderer.end();
	}

	public float getX(){
		return x;
	}

	public float getY(){
		return y;
	}
}