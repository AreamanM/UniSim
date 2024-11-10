package io.github.UniSim;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Building {

    private Texture texture;
    
    public Building(String path) {
        texture = new Texture(path);
    }

    public int getTextureWidth() {
        return texture.getWidth();
    }

    public int getTextureHeight() {
        return texture.getHeight();
    }

    public void draw(SpriteBatch sb, Vector2 coords) {
        sb.draw(texture, coords.x, coords.y);
    }
    
}
