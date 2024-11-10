package io.github.UniSim;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;

    FitViewport fvp;

    private Map map;
    private BuildingManager bm;
    private InputHandler ih;
    private UIRenderer uir;

    private final long gameLengthMs = 300000;
    private long startTimeMs;

    @Override
    public void create() {
        batch = new SpriteBatch();

        // remember to update the default window size if the map tex is changed
        // 640x640 at the moment for map.jpg
        fvp = new FitViewport(640, 640);

        // our map
        bm = new BuildingManager();
        map = new Map("map.jpg", bm);
        ih = new InputHandler(fvp, bm);
        uir = new UIRenderer();

        startTimeMs = System.currentTimeMillis();
    }

    @Override
    public void render() {
        ih.handleKeyboard();
        ih.handleMouse(fvp);

        ScreenUtils.clear(Color.BLACK);
        fvp.apply();
        batch.setProjectionMatrix(fvp.getCamera().combined);

        batch.begin();
        map.drawMapAndBuildings(batch);

        long timeLeftMs = gameLengthMs - (System.currentTimeMillis() - startTimeMs);
        long timeLeftSeconds = Math.ceilDiv(timeLeftMs, 1000);
        // A bit crude but this will do for now, this should ideally be in logic()
        if (timeLeftSeconds == 0) {
            Gdx.app.exit();
        }

        uir.RenderTimer(fvp, timeLeftSeconds, batch);
        uir.RenderCounters(fvp, batch, bm);
        
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public void resize(int width, int height) {
        fvp.update(width, height, true);
    }
}
