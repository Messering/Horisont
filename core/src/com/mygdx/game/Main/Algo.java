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
import com.mygdx.game.Maze_Search.RightHandedRule;
import com.mygdx.game.Player.Player;
import com.mygdx.game.Wall_Empty.*;
import com.mygdx.game.WorldGenerator.EllersAlgorithm.*;
import com.mygdx.game.WorldGenerator.MazeGenerator;

//In Maze Serching
import com.mygdx.game.Maze_Search.Recursive;

public class Algo extends ApplicationAdapter {
	public static final int FIELD_SIZE = 13; //розмір нашого лабіринта, скілкьи на скілкьи буде наш масив
	public static final float UPDATE_TIME = 0.0001f; /*швидкість обновлення лабіринут
	, зараз застосовується при переміщенні камери
        */
	String [][] texture_map;
	SpriteBatch batch; //спрайти хто не розумію вам сюди  http://habrahabr.ru/post/159027/
	OrthographicCamera camera;
	Texture play;
	Player player;

	Cell[][] map;//масив який набирає параметірв кольору розміру квадрата , вся реалізація в класі СеІІ
	public void create() { //так, це повинні знати, головний ініціалізатор входження
		batch = new SpriteBatch();
		camera = new OrthographicCamera(FIELD_SIZE, FIELD_SIZE);
		map = new Cell[FIELD_SIZE+1][FIELD_SIZE+1];

        Maze a=new Maze(FIELD_SIZE);

        texture_map=a.mass();
		player=new Player(texture_map,FIELD_SIZE+1,FIELD_SIZE+1,1,1);
	//	MazeSearch(texture_map);
		for (int x = 1; x <= FIELD_SIZE; x++) {
			for (int y = 1; y <= FIELD_SIZE; y++) {System.out.print(texture_map[x][y]);}System.out.println();}
	}
	public void textures() {
		play=new Texture(Gdx.files.internal("aplayer.jpg"));
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

		for (int x = 1; x < FIELD_SIZE + 1; x++) {//System.out.println();
			for (int y = 1; y < FIELD_SIZE + 1; y++) {
				//System.out.print(texture_map[x][y]);
				if (texture_map[x][y].equals("_n")) {
					map[x - 1][y - 1] = new Walk(t_n);}

				if (texture_map[x][y].equals("_e")) {
					map[x - 1][y - 1] = new Walk(t_e);
				}
				if (texture_map[x][y].equals("_ne")) {
					map[x - 1][y - 1] = new Walk(t_ne);
				}
				if (texture_map[x][y].equals("_nw")) {
					map[x - 1][y - 1] = new Walk(t_nw);
				}
				if (texture_map[x][y].equals("_nwe")) {
					map[x - 1][y - 1] = new Walk(t_nwe);
				}
				if (texture_map[x][y].equals("_s")) {
					map[x - 1][y - 1] = new Walk(t_s);
				}
				if (texture_map[x][y].equals("_se")) {
					map[x - 1][y - 1] = new Walk(t_se);
				}
				if (texture_map[x][y].equals("_sn")) {
					map[x - 1][y - 1] = new Walk(t_sn);
				}
				if (texture_map[x][y].equals("_sne")) {
					map[x - 1][y - 1] = new Walk(t_sne);
				}
				if (texture_map[x][y].equals("_snw")) {
					map[x - 1][y - 1] = new Walk(t_snw);
				}
				if (texture_map[x][y].equals("_sw")) {
					map[x - 1][y - 1] = new Walk(t_sw);
				}
				if (texture_map[x][y].equals("_swe")) {
					map[x - 1][y - 1] = new Walk(t_swe);
				}
				if (texture_map[x][y].equals("_w")) {
					map[x - 1][y - 1] = new Walk(t_w);
				}
				if (texture_map[x][y].equals("_we")) {
					map[x - 1][y - 1] = new Walk(t_we);
				}
				if (texture_map[x][y].equals("_n1")) {
					map[x - 1][y - 1] = new Wall(t_n);}
				if (texture_map[x][y].equals("_e1")) {
					map[x - 1][y - 1] = new Wall(t_e);
				}
				if (texture_map[x][y].equals("_ne1")) {
					map[x - 1][y - 1] = new Wall(t_ne);
				}
				if (texture_map[x][y].equals("_nw1")) {
					map[x - 1][y - 1] = new Wall(t_nw);
				}
				if (texture_map[x][y].equals("_nwe1")) {
					map[x - 1][y - 1] = new Wall(t_nwe);
				}
				if (texture_map[x][y].equals("_s1")) {
					map[x - 1][y - 1] = new Wall(t_s);
				}
				if (texture_map[x][y].equals("_se1")) {
					map[x - 1][y - 1] = new Wall(t_se);
				}
				if (texture_map[x][y].equals("_sn1")) {
					map[x - 1][y - 1] = new Wall(t_sn);
				}
				if (texture_map[x][y].equals("_sne1")) {
					map[x - 1][y - 1] = new Wall(t_sne);
				}
				if (texture_map[x][y].equals("_snw1")) {
					map[x - 1][y - 1] = new Wall(t_snw);
				}
				if (texture_map[x][y].equals("_sw1")) {
					map[x - 1][y - 1] = new Wall(t_sw);
				}
				if (texture_map[x][y].equals("_swe1")) {
					map[x - 1][y - 1] = new Wall(t_swe);
				}
				if (texture_map[x][y].equals("_w1")) {
					map[x - 1][y - 1] = new Wall(t_w);
				}
				if (texture_map[x][y].equals("_we1")) {
					map[x - 1][y - 1] = new Wall(t_we);
				}
				if (texture_map[x][y].contains("p")) {
					map[x - 1][y - 1] = new Wall(play);
				}
			}
		}
	}
	public void MazeSearch(String[][] texture_m)
	{
// Recursive RecursiveSearch = new Recursive(texture_m, 1, 1);
		RightHandedRule RightHandedSearch = new RightHandedRule(texture_m, 1, 1);
// RecursiveSearch.InMazeSearch();
		RightHandedSearch.InMazeSearch();
	}
	@Override
	public void render() { //рендеремо
		this.update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		texture_map=player.mapp();
		textures();
		batch.setProjectionMatrix(camera.combined);
		camera.update();  //обновляєм проект і промальовуєм під час переміщення
		batch.begin();
		for (int i = 0; i <FIELD_SIZE; i++)
			for (int j = 0; j < FIELD_SIZE; j++){
				map[i][j].draw(batch, i, j);
			}
		batch.end();
	}
	public void update() {  //прописане керування але подумую щоб запхати в новий клас або пакет контроллер

		float a = UPDATE_TIME;
		Input input = Gdx.input;


		if(input.isKeyPressed(Input.Keys.W))
			player.up();
		if(input.isKeyPressed(Input.Keys.S))
			player.down();
		if(input.isKeyPressed(Input.Keys.D))
			player.right();
		if(input.isKeyPressed(Input.Keys.A))
			player.left();

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

