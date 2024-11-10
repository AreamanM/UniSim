package io.github.UniSim;

import java.util.HashMap;
import java.util.HashSet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Map {
    private Texture map;
    private BuildingManager bm;

    public Map(String path, BuildingManager bmg) {
        map = new Texture(path);
        bm = bmg;
    }

    public void drawMapAndBuildings(SpriteBatch sb) {
        sb.draw(map, 0.0f, 0.0f);
        bm.draw(sb);
    }
}
