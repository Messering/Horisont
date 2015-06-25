package com.mygdx.game.Wall_Empty;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Main.Cell;

/**
 * Created by Ollko_000 on 6/25/2015.
 */
public class Walk extends Cell{
    public Walk(Texture texture) {
        super(texture, new Color(1f, 0f, 0f, 1));
    }

    //@Override
    public void update(Cell[][] map, int x, int y, Texture texture) {

    }
}
