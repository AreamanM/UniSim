package io.github.UniSim;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class InputHandler {

    private FitViewport vp;
    private BuildingManager bm;

    public InputHandler(FitViewport fvp, BuildingManager bma) {
        vp = fvp;
        bm = bma;
    }

    public void handleMouse(FitViewport fvp) {
        if (Gdx.input.isButtonPressed(Buttons.LEFT)) {
            Vector2 clickPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            Vector2 worldPos = vp.unproject(clickPos);
            bm.addBuilding(worldPos);
        } else if (Gdx.input.isButtonPressed(Buttons.RIGHT)) {
            Vector2 clickPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            Vector2 worldPos = vp.unproject(clickPos);
            bm.tryRemoveBuilding(worldPos);
        }
    }

    public void handleKeyboard() {
        if (Gdx.input.isKeyPressed(Keys.NUM_1)) {
            bm.setCurrSelected(bm.getAccom());
        } else if (Gdx.input.isKeyPressed(Keys.NUM_2)) {
            bm.setCurrSelected(bm.getLectureHall());
        } else if (Gdx.input.isKeyPressed(Keys.NUM_3)) {
            bm.setCurrSelected(bm.getCafe());
        } else if (Gdx.input.isKeyPressed(Keys.NUM_4)) {
            bm.setCurrSelected(bm.getSport());
        }
    }
}
