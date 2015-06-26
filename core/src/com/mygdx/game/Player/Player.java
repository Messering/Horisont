package com.mygdx.game.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Main.Cell;
import com.mygdx.game.Wall_Empty.Unit;

/**
 * Created by Vlad on 26.06.2015.
 */
public class Player {
    private int h,w;
    private int[][]g;
    Texture texture_2 = new Texture(Gdx.files.internal("title.jpg"));
    private Cell[][] mass;
    private Cell place;
    public Player(int [][]g,Cell[][] m, final int hi, final int we) {
        this.h = hi;
        this.w = we;
        mass = m;
        place=mass[h][w];
        mass[h][w]=new Unit(texture_2);
        this.g=g;
    }

    public void up()
    {
        if (h-1>=0) {
            if (g[h - 1][w] != 1) {
                mass[h][w] = this.place;
                this.place = mass[h - 1][w];
                mass[h - 1][w] = new Unit(texture_2);
                h -= 1;
            }
        }

    }
    public void down()
    {
        if (h+1<=mass.length) {
            if (g[h + 1][w] != 1) {
                mass[h][w] = this.place;
                this.place = mass[h + 1][w];
                mass[h + 1][w] = new Unit(texture_2);
                h += 1;
            }
            for (int i=0;i<mass.length;i++) {
                for (int j = 0; j < mass.length; j++) {
                    System.out.print(mass[i][j]);
                }
                System.out.println();
            }
        }
    }
    public void right()
    {
        if (w+1<=mass.length) {
            if (g[h][w + 1] != 1) {
                mass[h][w] = this.place;
                this.place = mass[h][w + 1];
                mass[h][w + 1] = new Unit(texture_2);
                w += 1;
            }
            for (int i=0;i<mass.length;i++) {
                for (int j = 0; j < mass.length; j++) {
                    System.out.print(mass[i][j]);
                }
                System.out.println();
            }
        }
    }
    public void left()
    {
        if(w-1>=0) {
            if (g[h][w - 1] != 1) {
                mass[h][w] = this.place;
                this.place = mass[h][w - 1];
                mass[h][w - 1] = new Unit(texture_2);
                w -= 1;
            }
            for (int i=0;i<mass.length;i++) {
                for (int j = 0; j < mass.length; j++) {
                    System.out.print(mass[i][j]);
                }
                System.out.println();
            }
        }
    }
}
