package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture map;

    FitViewport fvp;

    @Override
    public void create() {
        batch = new SpriteBatch();
        map = new Texture("map.jpg");

        // remember to update the default window size if the map tex is changed
        // 640x640 at the moment for map.jpg
        fvp = new FitViewport(map.getWidth(), map.getHeight());
    }

    @Override
    public void render() {
        ScreenUtils.clear(Color.BLACK);

        fvp.apply();
        batch.setProjectionMatrix(fvp.getCamera().combined);

        batch.begin();
        //batch.draw(map, 0, 0, fvp.getWorldWidth(), fvp.getWorldHeight());
        batch.draw(map, 0, 0);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        map.dispose();
    }

    @Override
    public void resize(int width, int height) {
        fvp.update(width, height, true);
    }
}
