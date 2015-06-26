package com.mygdx.game.Main;
//�� ����� ������� ������ �������� ������ ����� ������ ������� , �� ���� �� ���� � �++
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Maze_Search.Recursive;
import com.mygdx.game.Player.Player;
import com.mygdx.game.Wall_Empty.Empty;
import com.mygdx.game.Wall_Empty.Walk;
import com.mygdx.game.Wall_Empty.Wall;

//In Maze Serching

public class Algo extends ApplicationAdapter {
	public static final int FIELD_SIZE = 13; //����� ������ ��������, ������ �� ������ ���� ��� �����
	public static final float UPDATE_TIME = 0.1f; /*�������� ���������� ��������
	, ����� ������������� ��� ��������� ������
        */
	SpriteBatch batch; //������� ��� �� ������ ��� ����  http://habrahabr.ru/post/159027/
	OrthographicCamera camera;
	Texture texture;
	Texture texture_1;
	Player un;
	int[][] bmap;


	Cell[][] map;//����� ���� ������ ��������� ������� ������ �������� , ��� ��������� � ���� �岲

	public void create() { //���, �� ������ �����, �������� ����������� ���������
		batch = new SpriteBatch();
		camera = new OrthographicCamera(FIELD_SIZE, FIELD_SIZE);
		map = new Cell[FIELD_SIZE][FIELD_SIZE];
		texture = new Texture(Gdx.files.internal("mob.jpg"));
		texture_1 = new Texture(Gdx.files.internal("way.jpg"));//��������� ��������, �������� ��������� � ������ ������
		// ����� ����� � ����� �� �� ����������� ��������� � ��� ��������
		/*{
			com.mygdx.game.WorldGenerator.EllersAlgorithm.Cell[] result = new Cell[FIELD_SIZE * FIELD_SIZE];
			EllersAlgorithm algo = new EllersAlgorithm(FIELD_SIZE, FIELD_SIZE);
			for (int index = 0; index < FIELD_SIZE; index++) {
				com.mygdx.game.WorldGenerator.EllersAlgorithm.Cell[] row = algo.step(index);
				for (int i = 0; i < FIELD_SIZE; i++){ result[index * FIELD_SIZE + i] = row[i];
				};
			}*/
		//int[][] bmap = (new EllersAlgorithm(FIELD_SIZE, FIELD_SIZE)).generator();//����� �����
		bmap = new int[][]{
				{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
				{1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
				{1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
				{1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1},
				{0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
				{1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
				{1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
				{1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1},
				{1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1},
				{1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1},
				{1, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1},
				{1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1},
				{1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1}};
		for (int i = 0; i < FIELD_SIZE; i++) {
			for (int j = 0; j < FIELD_SIZE; j++) {
				System.out.print(bmap[i][j]);
			}
			System.out.println();
		}

		MazeSearch(bmap);//In Maze searching & changing bmap drawing paths


		//MAP CREATING
		for (int i = 0; i < FIELD_SIZE; i++)
			for (int j = 0; j < FIELD_SIZE; j++) {
				if (bmap[i][j] == 0)
					map[i][j] = new Wall(texture); //����
				if (bmap[i][j] == 1)
					map[i][j] = new Empty(texture);//���� ������(���� ���� � ������ ���-����
				if (bmap[i][j] == 2)
					map[i][j] = new Walk(texture_1);//����� ��������
			}
		//MAZE SEARCH
		un = new Player(bmap,map, 4, 0);

		System.out.println();
		for (int i = 0; i < FIELD_SIZE; i++) {
			for (int j = 0; j < FIELD_SIZE; j++) {
				System.out.print(bmap[i][j]);
			}
			System.out.println();
		}
	}

	public void MazeSearch(int[][] bmap) {
		Recursive Search = new Recursive(bmap, 4, 0);
		Search.InMazeSearch();
	}

	@Override
	public void render() { //���������
		this.update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		for (int i = 0; i < FIELD_SIZE; i++)
			for (int j = 0; j < FIELD_SIZE; j++) {
				map[i][j].draw(batch, i, j);
			}

		batch.end();
	}

	public void update(){  //��������� ��������� ��� ������� ��� ������� � ����� ���� ��� ����� ����������

		float a = UPDATE_TIME;
		Input input = Gdx.input;

		for (int i = 0; i < FIELD_SIZE; i++)
			for (int j = 0; j < FIELD_SIZE; j++)
				map[i][j].update(map, i, j, texture);

		/*if(input.isKeyPressed(Input.Keys.W))
			camera.zoom-=Gdx.graphics.getDeltaTime();

		if(input.isKeyPressed(Input.Keys.S))
			camera.zoom+=Gdx.graphics.getDeltaTime();*/

		if (input.isKeyPressed(Input.Keys.Q))
			camera.rotate(Gdx.graphics.getDeltaTime() * 90);
		if (input.isKeyPressed(Input.Keys.E))
			camera.rotate(-Gdx.graphics.getDeltaTime() * 90);

		if (input.isKeyPressed(Input.Keys.CONTROL_LEFT)) a = UPDATE_TIME + Gdx.graphics.getDeltaTime();
		if (input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
			a = UPDATE_TIME - Gdx.graphics.getDeltaTime();
		}

		if (input.isKeyPressed(Input.Keys.LEFT))
			camera.translate(new Vector2(-Gdx.graphics.getDeltaTime() * 50, 0));
		if (input.isKeyPressed(Input.Keys.RIGHT))
			camera.translate(new Vector2(Gdx.graphics.getDeltaTime() * 50, 0));
		if (input.isKeyPressed(Input.Keys.UP))
			camera.translate(new Vector2(0, Gdx.graphics.getDeltaTime() * 50));
		if (input.isKeyPressed(Input.Keys.DOWN))
			camera.translate(new Vector2(0, -Gdx.graphics.getDeltaTime() * 50));

		if (input.isKeyPressed(Input.Keys.SPACE)) {
			a = 1f;
			camera = new OrthographicCamera(FIELD_SIZE, FIELD_SIZE);
		}
		if (input.isKeyPressed(Input.Keys.W)){
			un.up();

		}
		if (input.isKeyPressed(Input.Keys.S)) {
			un.down();
		}
		if (input.isKeyPressed(Input.Keys.D)) {
			un.right();
		}
		if (input.isKeyPressed(Input.Keys.A)) {
			un.left();
		}


		camera.update();
			}
	}

