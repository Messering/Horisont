package com.mygdx.game.Wall_Empty;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Main.Cell;

/**
 * Created by HP PAVILION on 10.06.2015.
 * наша стінка реалізуєм з абстаркції промальовки
 */
public class Wall extends Cell {

    public Wall(Texture texture) {
        super(texture, new Color(0f, 0f, 0f, 1));
    }

    //@Override
    public void update(Cell[][] map, int x, int y, Texture texture) {

    }
}

