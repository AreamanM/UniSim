package io.github.UniSim;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class UIRenderer {

    private BitmapFont font;

    public UIRenderer() {
        font = new BitmapFont();
    }

    public void RenderTimer(FitViewport fvp, long timeSeconds, SpriteBatch sb) {
        CharSequence timerText = String.format("Time remaining: %d seconds", timeSeconds);
        font.draw(sb, timerText, 10, fvp.getWorldHeight() - 10);
    }

    public void RenderCounters(FitViewport fvp, SpriteBatch sb, BuildingManager bm) {
        CharSequence counterText;
        counterText = String.format("cafes: %d", bm.getBuildingCount(bm.getCafe()));
        font.draw(sb, counterText, 10, fvp.getWorldHeight() - 25);
        counterText = String.format("accoms: %d", bm.getBuildingCount(bm.getAccom()));
        font.draw(sb, counterText, 10, fvp.getWorldHeight() - 40);
        counterText = String.format("sports centres: %d", bm.getBuildingCount(bm.getSport()));
        font.draw(sb, counterText, 10, fvp.getWorldHeight() - 55);
        counterText = String.format("lecture halls: %d", bm.getBuildingCount(bm.getLectureHall()));
        font.draw(sb, counterText, 10, fvp.getWorldHeight() - 70);
    }
}
