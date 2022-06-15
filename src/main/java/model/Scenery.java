package model;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class Scenery extends MapGame {

    private final Map<String, Scenery> exits;

    public Scenery(String name, ImageIcon imagemIcon) {
        super(name,imagemIcon);
        exits = new HashMap<>();
    }

    public Scenery getExit(String direction) {
        if (this.exits.get(direction) != null) {
            return this.exits.get(direction);
        }
        return null;
    }

    public void setExit(String direction, Scenery neighbor) {
        this.exits.put(direction, neighbor);
    }

}
