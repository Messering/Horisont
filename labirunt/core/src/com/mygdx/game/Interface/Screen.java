package com.mygdx.game.Interface;

import com.badlogic.gdx.graphics.GL20;

import static com.badlogic.gdx.Gdx.gl;
import static com.badlogic.gdx.Gdx.input;

/**
 * Created by HP PAVILION on 14.06.2015.
 */
public interface Screen {
    public void render(float delta);

    public void resize(int width, int height);

    public void show();

    public void hide();

    public void pause();

    public void resume();

    public void dispose();
}
