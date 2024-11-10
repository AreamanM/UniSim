package io.github.UniSim;

import java.util.HashMap;
import java.util.HashSet;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class BuildingManager {
    private HashMap<Building, HashSet<Vector2>> buildings;
    private Building accom, lectureHall, cafe, sport, currSelected;

    public BuildingManager() {
        accom = new Building("house.png");
        lectureHall = new Building("lecturehall.png");
        cafe = new Building("cafe.png");
        sport = new Building("sport.png");

        buildings = new HashMap<>();
        buildings.put(accom, new HashSet<>());
        buildings.put(lectureHall, new HashSet<>());
        buildings.put(cafe, new HashSet<>());
        buildings.put(sport, new HashSet<>());

        currSelected = accom;
    }

    public void addBuilding(Vector2 target) {
        buildings.get(currSelected).add(target);
    }

    public void tryRemoveBuilding(Vector2 target) {
        // this little maneuver is going to cost us 51 years
        // do NOT try to delete buildngs if you value your CPU's life
        for (Building t : buildings.keySet()) {
            HashSet<Vector2> coords = buildings.get(t);
            // tpos is the bottom left corner of our texture
            for (Vector2 tpos : (HashSet<Vector2>)coords.clone()) {
                if (target.x <= tpos.x + t.getTextureWidth() && target.y <= tpos.y + t.getTextureHeight()
                        && target.x >= tpos.x && target.y >= tpos.y) {
                   coords.remove(tpos);
                }
            }
        }
    }

    public void draw(SpriteBatch sb) {
        for (Building b : buildings.keySet()) {
            for (Vector2 pos : buildings.get(b)) {
                System.err.println("hiii");
                b.draw(sb, pos);
            }
        }
    }

    public int getBuildingCount(Building b) {
        return buildings.get(b).size();
    }

    public Building getCurrSelected() {
        return currSelected;
    }

    public Building getAccom() {
        return accom;
    }

    public Building getCafe() {
        return cafe;
    }
    
    public Building getLectureHall() {
        return lectureHall;
    }

    public Building getSport() {
        return sport;
    }

    public void setCurrSelected(Building b) {
        currSelected = b;
    }
}
