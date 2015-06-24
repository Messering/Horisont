package com.mygdx.game.Main;
//це можна назвати мейном головною точкою входу нашого проекту , те саме як мейн в с++
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Wall_Empty.*;
import com.mygdx.game.WorldGenerator.EllersAlgorithm.*;
import com.mygdx.game.WorldGenerator.MazeGenerator;

public class Algo extends ApplicationAdapter {
	public static final int FIELD_SIZE = 51; //розмір нашого лабіринта, скілкьи на скілкьи буде наш масив
	public static final float UPDATE_TIME = 0.0001f; /*швидкість обновлення лабіринут
	, зараз застосовується при переміщенні камери
        */
	SpriteBatch batch; //спрайти хто не розумію вам сюди  http://habrahabr.ru/post/159027/
	OrthographicCamera camera;
	Texture texture;

	Cell[][] map;//масив який набирає параметірв кольору розміру квадрата , вся реалізація в класі СеІІ
	public void create() { //так це повинні знати головний ініціалізатор входження
		batch = new SpriteBatch();
		camera = new OrthographicCamera(FIELD_SIZE, FIELD_SIZE);

		map = new Cell[FIELD_SIZE][FIELD_SIZE];

		Texture texture = new Texture(Gdx.files.internal("mob.jpg")); //вантажимо текстуру, картинки знаходять в проекті андроїд
		// папка асерт і мають на неї автоматичне посилання у всіх проектах
		/*{
			com.mygdx.game.WorldGenerator.EllersAlgorithm.Cell[] result = new Cell[FIELD_SIZE * FIELD_SIZE];
			EllersAlgorithm algo = new EllersAlgorithm(FIELD_SIZE, FIELD_SIZE);
			for (int index = 0; index < FIELD_SIZE; index++) {
				com.mygdx.game.WorldGenerator.EllersAlgorithm.Cell[] row = algo.step(index);
				for (int i = 0; i < FIELD_SIZE; i++){ result[index * FIELD_SIZE + i] = row[i];

				};
			}*/


		int[][] bmap = (new EllersAlgorithm(FIELD_SIZE, FIELD_SIZE)).generator();//будуєм масив
		for (int i = 0; i < FIELD_SIZE; i++)
			for (int j = 0; j < FIELD_SIZE; j++) {
				if (bmap[i][j] == 0)
					map[i][j] = new Empty(texture);//нуль прохід(клас емпті з пакету вол-емпті
				if (bmap[i][j] == 1)
					map[i][j] = new Wall(texture); //стіна
			}
	}

	@Override
	public void render() { //рендеремо
		this.update();

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		camera.update();  //обновляєм проект і промальовуєм під час переміщення
		batch.begin();

		for (int i = 0; i < FIELD_SIZE; i++)
			for (int j = 0; j < FIELD_SIZE; j++){
				map[i][j].draw(batch, i, j);
			}

		batch.end();

	}
	public void update() {  //прописане керування але подумую щоб запхати в новий клас або пакет контроллер

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

