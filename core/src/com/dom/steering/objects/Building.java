package com.dom.steering.objects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Building extends Sprite {
	
	private float x, y, width, height;
	private Color color;
	private ShapeRenderer renderer;
	public Rectangle rect;

	public Building(String texture, float x, float y, float width, float height, Color color) {
		super(new Texture(texture));
		setSize(width, height);
		setPosition(x, y);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		this.renderer = new ShapeRenderer();
		this.rect = getBoundingRectangle();
	}

	/*public void draw(OrthographicCamera camera){
		renderer.begin(ShapeRenderer.ShapeType.Line);
		renderer.setColor(color);
		renderer.rect(x, y, width, height);
		renderer.setProjectionMatrix(camera.combined);
		renderer.end();
	}*/

	public float getX(){
		return x;
	}

	public float getY(){
		return y;
	}
}