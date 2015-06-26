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
import com.mygdx.game.Maze_Search.Recursive;
<<<<<<< HEAD
import com.mygdx.game.Player.Player;
import com.mygdx.game.Wall_Empty.Empty;
import com.mygdx.game.Wall_Empty.Walk;
import com.mygdx.game.Wall_Empty.Wall;
=======
import com.mygdx.game.Wall_Empty.*;
import com.mygdx.game.WorldGenerator.EllersAlgorithm.*;
import com.mygdx.game.WorldGenerator.MazeGenerator;
>>>>>>> origin/Messering

//In Maze Serching
import com.mygdx.game.Maze_Search.Recursive;

public class Algo extends ApplicationAdapter {
	public static final int FIELD_SIZE = 13; //розмір нашого лабіринта, скілкьи на скілкьи буде наш масив
	public static final float UPDATE_TIME = 0.1f; /*швидкість обновлення лабіринут
	, зараз застосовується при переміщенні камери
        */
	SpriteBatch batch; //спрайти хто не розумію вам сюди  http://habrahabr.ru/post/159027/
	OrthographicCamera camera;
	Texture texture;
<<<<<<< HEAD
	Texture texture_1;
	Player un;
	int[][] bmap;

=======
>>>>>>> origin/Messering

	Cell[][] map;//масив який набирає параметірв кольору розміру квадрата , вся реалізація в класі СеІІ

	public void create() { //так, це повинні знати, головний ініціалізатор входження
		batch = new SpriteBatch();
		camera = new OrthographicCamera(FIELD_SIZE, FIELD_SIZE);
<<<<<<< HEAD
		map = new Cell[FIELD_SIZE][FIELD_SIZE];
<<<<<<< HEAD
		texture = new Texture(Gdx.files.internal("mob.jpg"));
		texture_1 = new Texture(Gdx.files.internal("way.jpg"));//вантажимо текстуру, картинки знаходять в проекті андроїд
=======
		Texture texture = new Texture(Gdx.files.internal("mob.jpg"));
		Texture texture_1 = new Texture(Gdx.files.internal("way.jpg"));//вантажимо текстуру, картинки знаходять в проекті андроїд
>>>>>>> origin/Messering
		// папка асерт і мають на неї автоматичне посилання у всіх проектах
		/*{
			com.mygdx.game.WorldGenerator.EllersAlgorithm.Cell[] result = new Cell[FIELD_SIZE * FIELD_SIZE];
			EllersAlgorithm algo = new EllersAlgorithm(FIELD_SIZE, FIELD_SIZE);
			for (int index = 0; index < FIELD_SIZE; index++) {
				com.mygdx.game.WorldGenerator.EllersAlgorithm.Cell[] row = algo.step(index);
				for (int i = 0; i < FIELD_SIZE; i++){ result[index * FIELD_SIZE + i] = row[i];
				};
			}*/
		//int[][] bmap = (new EllersAlgorithm(FIELD_SIZE, FIELD_SIZE)).generator();//будуєм масив
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

<<<<<<< HEAD

		//MAP CREATING
=======
	//MAP CREATING
>>>>>>> origin/Messering
		for (int i = 0; i < FIELD_SIZE; i++)
			for (int j = 0; j < FIELD_SIZE; j++) {
				if (bmap[i][j] == 0)
					map[i][j] = new Wall(texture); //стіна
				if (bmap[i][j] == 1)
					map[i][j] = new Empty(texture);//нуль прохід(клас емпті з пакету вол-емпті
				if (bmap[i][j] == 2)
					map[i][j] = new Walk(texture_1);//Обхід лабіринту
			}
		//MAZE SEARCH
		un = new Player(bmap,map, 4, 0);

<<<<<<< HEAD
=======

>>>>>>> origin/Messering
		System.out.println();
		for (int i = 0; i < FIELD_SIZE; i++) {
			for (int j = 0; j < FIELD_SIZE; j++) {
				System.out.print(bmap[i][j]);
			}
			System.out.println();
		}
=======
		map = new Cell[FIELD_SIZE+1][FIELD_SIZE+1];
		Texture t_e = new Texture(Gdx.files.internal("e.jpg"));
        Texture t_n = new Texture(Gdx.files.internal("n.jpg"));
        Texture t_ne = new Texture(Gdx.files.internal("ne.jpg"));
        Texture t_nw = new Texture(Gdx.files.internal("nw.jpg"));
        Texture t_nwe = new Texture(Gdx.files.internal("nwe.jpg"));
        Texture t_s = new Texture(Gdx.files.internal("s.jpg"));
        Texture t_se = new Texture(Gdx.files.internal("se.jpg"));
        Texture t_sn = new Texture(Gdx.files.internal("sn.jpg"));
        Texture t_sne = new Texture(Gdx.files.internal("sne.jpg"));
        Texture t_snw = new Texture(Gdx.files.internal("snw.jpg"));
        Texture t_sw = new Texture(Gdx.files.internal("sw.jpg"));
        Texture t_swe = new Texture(Gdx.files.internal("swe.jpg"));
        Texture t_w = new Texture(Gdx.files.internal("w.jpg"));
        Texture t_we = new Texture(Gdx.files.internal("we.jpg"));

        Maze a=new Maze(FIELD_SIZE);
        String [][] texture_map=new String[FIELD_SIZE+2][FIELD_SIZE+2];
        texture_map=a.mass();

        for (int x = 1; x < FIELD_SIZE+1; x++) {
            for (int y = 1; y < FIELD_SIZE+1; y++) {
                System.out.print(x); System.out.println(y);
                if (texture_map[x][y].equals("_n"))map[x-1][y-1]=new Walk(t_n);
                if (texture_map[x][y].equals("_e")){map[x-1][y-1]=new Walk(t_e); }
                if (texture_map[x][y].equals("_ne")){map[x-1][y-1]=new Walk(t_ne); }
                if (texture_map[x][y].equals("_nw")){map[x-1][y-1]=new Walk(t_nw); }
                if (texture_map[x][y].equals("_nwe")){map[x-1][y-1]=new Walk(t_nwe); }
                if (texture_map[x][y].equals("_s")){map[x-1][y-1]=new Walk(t_s); }
                if (texture_map[x][y].equals("_se")){map[x-1][y-1]=new Walk(t_se); }
                if (texture_map[x][y].equals("_sn")){map[x-1][y-1]=new Walk(t_sn); }
                if (texture_map[x][y].equals("_sne")){map[x-1][y-1]=new Walk(t_sne); }
                if (texture_map[x][y].equals("_snw")){map[x-1][y-1]=new Walk(t_snw); }
                if (texture_map[x][y].equals("_sw")){map[x-1][y-1]=new Walk(t_sw); }
                if (texture_map[x][y].equals("_swe")){map[x-1][y-1]=new Walk(t_swe); }
                if (texture_map[x][y].equals("_w")){map[x-1][y-1]=new Walk(t_w); }
                if (texture_map[x][y].equals("_we")){map[x-1][y-1]=new Walk(t_we); }
                System.out.print("da");
            }}

>>>>>>> origin/Messering
	}

	public void MazeSearch(int[][] bmap) {
		Recursive Search = new Recursive(bmap, 4, 0);
		Search.InMazeSearch();
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
			for (int j = 0; j < FIELD_SIZE; j++) {
				map[i][j].draw(batch, i, j);
			}
		batch.end();
	}

	public void update(){  //прописане керування але подумую щоб запхати в новий клас або пакет контроллер

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

<<<<<<< HEAD
=======
		camera.update();
>>>>>>> origin/Messering

		camera.update();
			}
	}

