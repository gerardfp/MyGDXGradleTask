package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Cuadrito {
    float x, y, vx, vy;
    int num;
    TextureRegion textureRegion;
    static Texture atlas = new Texture("atlas.png");
    static Random random = new Random();

    public Cuadrito(int x, int y, int regionX, int regionY) {
        this.x = x;
        this.y = y;
        this.num = regionY/64;
        this.textureRegion = new TextureRegion(atlas, regionX, regionY, 64, 64);
    }

    void move() {
        x = MathUtils.clamp(x + (vx = MathUtils.clamp(vx + random.nextFloat() - 0.5f, -2, 2)), 0, Gdx.graphics.getWidth() - 64);
        y = MathUtils.clamp(y + (vy = MathUtils.clamp(vy + random.nextFloat() - 0.5f, -2, 2)), 0, Gdx.graphics.getHeight() - 64);
    }

    void render(SpriteBatch batch, BitmapFont font) {
        batch.draw(textureRegion, x, y);
        font.draw(batch, new GlyphLayout(font, Resources.strings[num], Color.BLACK, 64, Align.center, false), x, y-10);
    }
}

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    BitmapFont font;
    List<Cuadrito> cuadritos;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();

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

        cuadritos.forEach(cuadrito ->cuadrito.render(batch, font));

        batch.end();
    }
}
