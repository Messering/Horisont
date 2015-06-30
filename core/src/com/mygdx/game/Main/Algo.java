package com.mygdx.game.Main;
//це можна назвати мейном головною точкою входу нашого проекту , те саме як мейн в с++
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Interface.Screen;
import com.mygdx.game.Maze_Search.Recursive;
import com.mygdx.game.Maze_Search.RightHandedRule;
import com.mygdx.game.Player.Player;
import com.mygdx.game.Wall_Empty.*;
import com.mygdx.game.WorldGenerator.EllersAlgorithm.*;
import com.mygdx.game.WorldGenerator.MazeGenerator;

//In Maze Serching
import com.mygdx.game.Maze_Search.Recursive;
import sun.java2d.ScreenUpdateManager;

public class Algo extends ApplicationAdapter {
	public static final int FIELD_SIZE = 13; //розмір нашого лабіринта, скілкьи на скілкьи буде наш масив
	String [][] texture_map;
	SpriteBatch batch; //спрайти хто не розумію вам сюди  http://habrahabr.ru/post/159027/
	OrthographicCamera camera;
	Cell players;
	int play_x=0;
	int play_y=0;
	Player player;
	Texture play;
	Texture t_e;
	Texture t_n;
	Texture t_ne;
	Texture t_nw;
	Texture t_nwe;
	Texture t_s;
	Texture t_se;
	Texture t_sn;
	Texture t_sne;
	Texture t_snw;
	Texture t_sw;
	Texture t_swe;
	Texture t_w;
	Texture t_we;

	Cell[][] map;//масив який набирає параметірв кольору розміру квадрата , вся реалізація в класі СеІІ
	public void create() { //так, це повинні знати, головний ініціалізатор входження
		play=new Texture(Gdx.files.internal("aplayer.png"));
		t_e = new Texture(Gdx.files.internal("e.png"));
		t_n = new Texture(Gdx.files.internal("n.png"));
		t_ne = new Texture(Gdx.files.internal("ne.png"));
		t_nw = new Texture(Gdx.files.internal("nw.png"));
		t_nwe = new Texture(Gdx.files.internal("nwe.png"));
		t_s = new Texture(Gdx.files.internal("s.png"));
		t_se = new Texture(Gdx.files.internal("se.png"));
		t_sn = new Texture(Gdx.files.internal("sn.png"));
		t_sne = new Texture(Gdx.files.internal("sne.png"));
		t_snw = new Texture(Gdx.files.internal("snw.png"));
		t_sw = new Texture(Gdx.files.internal("sw.png"));
		t_swe = new Texture(Gdx.files.internal("swe.png"));
		t_w = new Texture(Gdx.files.internal("w.png"));
		t_we = new Texture(Gdx.files.internal("we.png"));
		batch = new SpriteBatch();
		batch.disableBlending();
		camera = new OrthographicCamera(FIELD_SIZE, FIELD_SIZE);
		map = new Cell[FIELD_SIZE+1][FIELD_SIZE+1];
        Maze a=new Maze(FIELD_SIZE);
        texture_map=a.mass();
		player=new Player(texture_map,FIELD_SIZE+1,FIELD_SIZE+1,1,1);
		//MazeSearch(texture_map);
	}

	public void textures() {
		for (int x = 1; x < FIELD_SIZE + 1; x++) {
			for (int y = 1; y < FIELD_SIZE + 1; y++) {
				if (texture_map[x][y].equals("_n")||texture_map[x][y].equals("_np")) {
					map[x - 1][y - 1] = new Walk(t_n);}

				if (texture_map[x][y].equals("_e")||texture_map[x][y].equals("_ep")) {
					map[x - 1][y - 1] = new Walk(t_e);
				}
				if (texture_map[x][y].equals("_ne")||texture_map[x][y].equals("_nep")) {
					map[x - 1][y - 1] = new Walk(t_ne);
				}
				if (texture_map[x][y].equals("_nw")||texture_map[x][y].equals("_nwp")) {
					map[x - 1][y - 1] = new Walk(t_nw);
				}
				if (texture_map[x][y].equals("_nwe")||texture_map[x][y].equals("_nwep")) {
					map[x - 1][y - 1] = new Walk(t_nwe);
				}
				if (texture_map[x][y].equals("_s")||texture_map[x][y].equals("_sp")) {
					map[x - 1][y - 1] = new Walk(t_s);
				}
				if (texture_map[x][y].equals("_se")||texture_map[x][y].equals("_sep")) {
					map[x - 1][y - 1] = new Walk(t_se);
				}
				if (texture_map[x][y].equals("_sn")||texture_map[x][y].equals("_snp")) {
					map[x - 1][y - 1] = new Walk(t_sn);
				}
				if (texture_map[x][y].equals("_sne")||texture_map[x][y].equals("_snep")) {
					map[x - 1][y - 1] = new Walk(t_sne);
				}
				if (texture_map[x][y].equals("_snw")||texture_map[x][y].equals("_snwp")) {
					map[x - 1][y - 1] = new Walk(t_snw);
				}
				if (texture_map[x][y].equals("_sw")||texture_map[x][y].equals("_swp")) {
					map[x - 1][y - 1] = new Walk(t_sw);
				}
				if (texture_map[x][y].equals("_swe")||texture_map[x][y].equals("_swep")) {
					map[x - 1][y - 1] = new Walk(t_swe);
				}
				if (texture_map[x][y].equals("_w")||texture_map[x][y].equals("_wp")) {
					map[x - 1][y - 1] = new Walk(t_w);
				}
				if (texture_map[x][y].equals("_we")||texture_map[x][y].equals("_wep")) {
					map[x - 1][y - 1] = new Walk(t_we);
				}
				if (texture_map[x][y].equals("_n1")||texture_map[x][y].equals("_np1")) {
					map[x - 1][y - 1] = new Wall(t_n);}
				if (texture_map[x][y].equals("_e1")||texture_map[x][y].equals("_ep1")) {
					map[x - 1][y - 1] = new Wall(t_e);
				}
				if (texture_map[x][y].equals("_ne1")||texture_map[x][y].equals("_nep1")) {
					map[x - 1][y - 1] = new Wall(t_ne);
				}
				if (texture_map[x][y].equals("_nw1")||texture_map[x][y].equals("_nwp1")) {
					map[x - 1][y - 1] = new Wall(t_nw);
				}
				if (texture_map[x][y].equals("_nwe1")||texture_map[x][y].equals("_nwep1")) {
					map[x - 1][y - 1] = new Wall(t_nwe);
				}
				if (texture_map[x][y].equals("_s1")||texture_map[x][y].equals("_sp1")) {
					map[x - 1][y - 1] = new Wall(t_s);
				}
				if (texture_map[x][y].equals("_se1")||texture_map[x][y].equals("_sep1")) {
					map[x - 1][y - 1] = new Wall(t_se);
				}
				if (texture_map[x][y].equals("_sn1")||texture_map[x][y].equals("_snp1")) {
					map[x - 1][y - 1] = new Wall(t_sn);
				}
				if (texture_map[x][y].equals("_sne1")||texture_map[x][y].equals("_snep1")) {
					map[x - 1][y - 1] = new Wall(t_sne);
				}
				if (texture_map[x][y].equals("_snw1")||texture_map[x][y].equals("_snwp1")) {
					map[x - 1][y - 1] = new Wall(t_snw);
				}
				if (texture_map[x][y].equals("_sw1")||texture_map[x][y].equals("_swp1")) {
					map[x - 1][y - 1] = new Wall(t_sw);
				}
				if (texture_map[x][y].equals("_swe1")||texture_map[x][y].equals("_swep1")) {
					map[x - 1][y - 1] = new Wall(t_swe);
				}
				if (texture_map[x][y].equals("_w1")||texture_map[x][y].equals("_wp1")) {
					map[x - 1][y - 1] = new Wall(t_w);
				}
				if (texture_map[x][y].equals("_we1")||texture_map[x][y].equals("_wep1")) {
					map[x - 1][y - 1] = new Wall(t_we);
				}
				if (texture_map[x][y].contains("p")) {
					play_x=x-1;
					play_y=y-1;
					players=new Wall(play);
				}
			}
		}

	}
	public void MazeSearch(String[][] texture_m)
	{
 Recursive RecursiveSearch = new Recursive(texture_m, 1, 1);
		//RightHandedRule RightHandedSearch = new RightHandedRule(texture_m, 1, 1);
		RecursiveSearch.InMazeSearch();
		//RightHandedSearch.InMazeSearch();
	}
	public void pause()
	{
		try {
			Thread.sleep(80);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void render() { //рендеремо
		this.pause();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		texture_map=null;
		texture_map=player.mapp();
		textures();
		batch.setProjectionMatrix(camera.combined);
		//camera.update();  //обновляєм проект і промальовуєм під час переміщення
		batch.begin();
		for (int i = 0; i <FIELD_SIZE; i++)
			for (int j = 0; j < FIELD_SIZE; j++){
				if (i==play_x&&j==play_y){batch.enableBlending();
					map[i][j].draw(batch, i, j);
					map[i][j]=null;
					players.draw(batch,i,j);
					batch.disableBlending();
				}
				else
				map[i][j].draw(batch, i, j);
				map[i][j]=null;
			}
		batch.end();
		this.update();
	}

	public void update() {  //прописане керування але подумую щоб запхати в новий клас або пакет контроллер
		Input input = Gdx.input;
		if(input.isKeyPressed(Input.Keys.W)) {
			player.up();
		}
		if(input.isKeyPressed(Input.Keys.S)) {
			player.down();
		}
		if(input.isKeyPressed(Input.Keys.D)) {
			player.right();
		}
		if(input.isKeyPressed(Input.Keys.A)) {
			player.left();
		}

		if(input.isKeyPressed(Input.Keys.Q)){
			camera.rotate(Gdx.graphics.getDeltaTime() * 90);}

		if(input.isKeyPressed(Input.Keys.E)){
			camera.rotate(-Gdx.graphics.getDeltaTime()*90);}

		if(input.isKeyPressed(Input.Keys.LEFT)){
			camera.translate(new Vector2(-Gdx.graphics.getDeltaTime()*50,0));}

		if(input.isKeyPressed(Input.Keys.RIGHT)){
			camera.translate(new Vector2(Gdx.graphics.getDeltaTime()*50,0));
		}

		if(input.isKeyPressed(Input.Keys.UP)){
			camera.translate(new Vector2(0, Gdx.graphics.getDeltaTime() * 50));
		}

		if(input.isKeyPressed(Input.Keys.DOWN)){
			camera.translate(new Vector2(0, -Gdx.graphics.getDeltaTime() * 50));}

		if(input.isKeyPressed(Input.Keys.SPACE)){
			camera = new OrthographicCamera(FIELD_SIZE, FIELD_SIZE);
		}
		camera.update();
	}
}

