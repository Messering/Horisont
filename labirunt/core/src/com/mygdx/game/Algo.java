package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;

public class Algo extends ApplicationAdapter {
	public static final int FIELD_SIZE = 51;
	public static final float UPDATE_TIME = 0.0001f;

	SpriteBatch batch;
	OrthographicCamera camera;
	Texture texture;

	Cell[][] map;

	@Override
	public void create() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera(FIELD_SIZE, FIELD_SIZE);

		map = new Cell[FIELD_SIZE][FIELD_SIZE];


		Texture texture2 = new Texture(Gdx.files.internal("br.jpg"));

		char[][] bmap = (new MazeGenerator()).getMaze(FIELD_SIZE - 1);
		for (int i = 0; i < FIELD_SIZE; i++)
			for (int j = 0; j < FIELD_SIZE; j++) {
				if (bmap[i][j] == 0)
					map[i][j] = new Empty(texture2);
				if (bmap[i][j] == 1)
					map[i][j] = new Wall(texture2);
			}

	}

	public void render() {
		this.update();

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		for (int i = 0; i < FIELD_SIZE; i++)
			for (int j = 0; j < FIELD_SIZE; j++)
				map[i][j].draw(batch, i, j);
		batch.end();
	}

	public void update() {

		float a = UPDATE_TIME;
		Input input = Gdx.input;

		for (int i = 0; i < FIELD_SIZE; i++)
			for (int j = 0; j < FIELD_SIZE; j++)
				map[i][j].update(map, i, j, texture);

		if(input.isKeyPressed(Input.Keys.W))
			camera.zoom-=Gdx.graphics.getDeltaTime();
		if(input.isKeyPressed(Input.Keys.S))
			camera.zoom+=Gdx.graphics.getDeltaTime();

		if(input.isKeyPressed(Input.Keys.Q))
			camera.rotate(Gdx.graphics.getDeltaTime() * 90);
		if(input.isKeyPressed(Input.Keys.E))
			camera.rotate(-Gdx.graphics.getDeltaTime()*90);

		if(input.isKeyPressed(Input.Keys.CONTROL_LEFT)) a = UPDATE_TIME + Gdx.graphics.getDeltaTime();
		if(input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
			a = UPDATE_TIME - Gdx.graphics.getDeltaTime();}

		if(input.isKeyPressed(Input.Keys.LEFT))
			camera.translate(new Vector2(-Gdx.graphics.getDeltaTime()*50,0));
		if(input.isKeyPressed(Input.Keys.RIGHT))
			camera.translate(new Vector2(Gdx.graphics.getDeltaTime()*50,0));
		if(input.isKeyPressed(Input.Keys.UP))
			camera.translate(new Vector2(0, Gdx.graphics.getDeltaTime() * 50));
		if(input.isKeyPressed(Input.Keys.DOWN))
			camera.translate(new Vector2(0, -Gdx.graphics.getDeltaTime() * 50));

		if(input.isKeyPressed(Input.Keys.SPACE)){
			a = 1f;
			camera = new OrthographicCamera(FIELD_SIZE, FIELD_SIZE);
		}

		camera.update();

	}
}

