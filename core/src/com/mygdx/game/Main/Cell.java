package com.mygdx.game.Main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by HP PAVILION on 10.06.2015.
 * ?????? ????????? ?????????? ????????
 */
public abstract class Cell {
    public Color color;

    Sprite sprite;

    public Cell(Texture texture, Color color){
        this.color = color;
        sprite = new Sprite(texture);
        sprite.setColor(color);
        sprite.setSize(1, 1);
    }

    public abstract void update(Cell[][] map, int x, int y, Texture texture);

    public void setColor(Color color){
        this.color = color;
        sprite.setColor(color);
    }

    public void draw(SpriteBatch batch,int x, int y){

        sprite.setPosition(x- Algo.FIELD_SIZE/2-sprite.getWidth()/2, y-Algo.FIELD_SIZE/2-sprite.getHeight()/2);
        sprite.draw(batch);
    }
}