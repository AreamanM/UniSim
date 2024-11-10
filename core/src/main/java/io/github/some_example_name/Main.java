package io.github.some_example_name;

import java.util.HashMap;
import java.util.HashSet;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;

    private BitmapFont font;

    private Texture map;

    private Texture accom;
    private Texture cafe;
    private Texture lectureHall;
    private Texture sport;

    FitViewport fvp;

    private HashMap<Texture, HashSet<Vector2>> buildings;
    private Texture currBuilding;

    private final long gameLengthMs = 300000;
    private long startTimeMs;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();

        // our map
        map = new Texture("map.jpg");

        startTimeMs = System.currentTimeMillis();

        // building textures loading time
        accom = new Texture("house.png");
        lectureHall = new Texture("lecturehall.png");
        cafe = new Texture("cafe.png");
        sport = new Texture("sport.png");

        // place accoms by default
        currBuilding = accom;

        // populate hashmap
        buildings = new HashMap<>();
        buildings.put(accom, new HashSet<>());
        buildings.put(lectureHall, new HashSet<>());
        buildings.put(cafe, new HashSet<>());
        buildings.put(sport, new HashSet<>());

        // remember to update the default window size if the map tex is changed
        // 640x640 at the moment for map.jpg
        fvp = new FitViewport(map.getWidth(), map.getHeight());
    }

    @Override
    public void render() {
        input();
        draw();
    }

    private void input() {
        if (Gdx.input.isKeyPressed(Keys.NUM_1)) {
            currBuilding = accom;
        } else if (Gdx.input.isKeyPressed(Keys.NUM_2)) {
            currBuilding = lectureHall;
        } else if (Gdx.input.isKeyPressed(Keys.NUM_3)) {
            currBuilding = cafe;
        } else if (Gdx.input.isKeyPressed(Keys.NUM_4)) {
            currBuilding = sport;
        }

        if (Gdx.input.isButtonPressed(Buttons.LEFT)) {
            Vector2 clickPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            Vector2 worldPos = fvp.unproject(clickPos);
            HashSet<Vector2> coordSet = buildings.get(currBuilding);
            coordSet.add(worldPos);
        } else if (Gdx.input.isButtonPressed(Buttons.RIGHT)) {
            Vector2 clickPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            Vector2 worldPos = fvp.unproject(clickPos);
            tryDeleteBuilding(worldPos.x, worldPos.y);
        }
    }

    private void tryDeleteBuilding(float mx, float my) {
        // this little maneuver is going to cost us 51 years
        // do NOT try to delete buildngs if you value your CPU's life
        for (Texture t : buildings.keySet()) {
            HashSet<Vector2> coords = buildings.get(t);
            // tpos is the bottom left corner of our texture
            for (Vector2 tpos : (HashSet<Vector2>)coords.clone()) {
                if (mx <= tpos.x + t.getWidth() && my <= tpos.y + t.getHeight()
                        && mx >= tpos.x && my >= tpos.y) {
                   coords.remove(tpos);
                }
            }
        }
    }
    
    private void draw() {
        ScreenUtils.clear(Color.BLACK);

        fvp.apply();
        batch.setProjectionMatrix(fvp.getCamera().combined);

        batch.begin();
        batch.draw(map, 0, 0, fvp.getWorldWidth(), fvp.getWorldHeight());

        long timeLeftMs = gameLengthMs - (System.currentTimeMillis() - startTimeMs);
        long timeLeftSeconds = Math.ceilDiv(timeLeftMs, 1000);
        CharSequence timerText = String.format("Time remaining: %d seconds", timeLeftSeconds);
        font.draw(batch, timerText, 10, fvp.getWorldHeight() - 10);

        // building counters


        // draw each building type
        for (Texture t : buildings.keySet()) {
            HashSet<Vector2> coords = buildings.get(t);
            for (Vector2 pos : coords) {
                batch.draw(t, pos.x, pos.y);
            }
        }

        batch.end();
    }

    private void logic() {

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
