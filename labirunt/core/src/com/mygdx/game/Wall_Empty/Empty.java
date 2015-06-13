package com.mygdx.game.Wall_Empty;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Main.Cell;

/**
 * Created by HP PAVILION on 10.06.2015.
 * шлях наш
 */
public class Empty extends Cell {

    public Empty(Texture texture) {
        super(texture, new Color(1, 1, 1, 1));
    }

    //@Override
    public void update(Cell[][] map, int x, int y, Texture texture) {

    }

}
