package com.mygdx.game.Interface;

import com.badlogic.gdx.graphics.GL20;

import static com.badlogic.gdx.Gdx.gl;
import static com.badlogic.gdx.Gdx.input;

/**
 * Created by HP PAVILION on 14.06.2015.
 * на це також не звертаєм уваги нігде поки не задіяно
 * мало б бути для інтерфейсу(інтуїтивно по назві пакету))
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
