package com.mygdx.game.Units;

/**
 * Created by HP PAVILION on 13.06.2015.
 */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Main.Cell;

public class Dirt extends Cell {

    public Dirt(Texture texture) {
        super(texture, new Color(0.49f,0.2f,0,1));
    }

    @Override
    public void update(Cell[][] map, int x, int y, Texture texture) {
        // TODO Auto-generated method stub

    }

}
