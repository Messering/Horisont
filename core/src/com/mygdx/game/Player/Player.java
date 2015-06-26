package com.mygdx.game.Player;

/**
 * Created by Vlad on 26.06.2015.
 */
public class Player {
    private int h,w;
    private int[][] mass;
    private int place;
    public Player(int[][] m, final int hi, final int we) {
        this.h = hi;
        this.w = we;
        mass = m;
        place=mass[h][w];
        mass[h][w]=9;
    }

    public void up()
    {
        if (h-1>=0) {
            if (mass[h - 1][w] != 1) {
                mass[h][w] = this.place;
                this.place = mass[h - 1][w];
                mass[h - 1][w] = 9;
                h -= 1;
            }
        }

    }
    public void down()
    {
        if (h+1<=mass.length) {
            if (mass[h + 1][w] != 1) {
                mass[h][w] = this.place;
                this.place = mass[h + 1][w];
                mass[h + 1][w] = 9;
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
            if (mass[h][w + 1] != 1) {
                mass[h][w] = this.place;
                this.place = mass[h][w + 1];
                mass[h][w + 1] = 9;
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
            if (mass[h][w - 1] != 1) {
                mass[h][w] = this.place;
                this.place = mass[h][w - 1];
                mass[h][w - 1] = 9;
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
