package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Cuadrito {
    float x, y, vx, vy;
    TextureRegion textureRegion;
    static Texture atlas = new Texture("atlas.png");
    static Random random = new Random();

    public Cuadrito(int x, int y, int regionX, int regionY) {
        this.x = x;
        this.y = y;
        this.textureRegion = new TextureRegion(atlas, regionX, regionY, 64, 64);
    }

    void move() {
        x = MathUtils.clamp(x + (vx = MathUtils.clamp(vx + random.nextFloat() - 0.5f, -2, 2)), 0, Gdx.graphics.getWidth() - 64);
        y = MathUtils.clamp(y + (vy = MathUtils.clamp(vy + random.nextFloat() - 0.5f, -2, 2)), 0, Gdx.graphics.getHeight() - 64);
    }
}

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    List<Cuadrito> cuadritos;

    @Override
    public void create() {
        batch = new SpriteBatch();

        cuadritos = new ArrayList<>();
        cuadritos.add(new Cuadrito(0, 0, 0, 0));
        cuadritos.add(new Cuadrito(100, 0, 0, 64));
        cuadritos.add(new Cuadrito(0, 100, 0, 128));
        cuadritos.add(new Cuadrito(100, 100, 0, 192));
    }

    @Override
    public void render() {
        cuadritos.forEach(Cuadrito::move);

        ScreenUtils.clear(1, 1, 1, 1);
        batch.begin();
        cuadritos.forEach(cuadrito -> batch.draw(cuadrito.textureRegion, cuadrito.x, cuadrito.y));
        batch.end();
    }
}
