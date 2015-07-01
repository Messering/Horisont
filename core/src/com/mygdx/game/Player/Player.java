package com.mygdx.game.Player;



/**
 * Created by Vlad on 26.06.2015.
 */
public class Player {
    private int h,w;
    String[][]mass;
    public Player(String[][] m,final int x,final int y, final int hi, final int we) {
        h = hi;
        w = we;
        mass = m;
        mass[h][w] += "p";
    }
    public String removeChar(String s, char c) {
        String r = "";
        for (int i = 0; i < s.length(); i ++) {
            if (s.charAt(i) != c) r += s.charAt(i);
        }
        return r;
    }
    public String[][] mapp(){return mass;}
    public void up()
    {
        if (!mass[h][w].contains("n")){mass[h][w]=removeChar(mass[h][w],'p');w++;mass[h][w]+="p";}
    }
    public void down()
    {
        if (!mass[h][w].contains("s")){mass[h][w]=removeChar(mass[h][w],'p');w--;mass[h][w]+="p";}
    }
    public void right()
    {
        if (!mass[h][w].contains("e")){mass[h][w]=removeChar(mass[h][w],'p');h++;mass[h][w]+="p";}
    }
    public void left()
    {
        if (!mass[h][w].contains("w")){mass[h][w]=removeChar(mass[h][w],'p');h--;mass[h][w]+="p";}
    }
}
