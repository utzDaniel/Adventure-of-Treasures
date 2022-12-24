package model;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Scenery extends MapGame {

    private final Map<String, Scenery> exits;

    public Scenery(String name, ImageIcon imagemIcon) {
        super(name,imagemIcon);
        exits = new HashMap<>();
        if(this.name.equals("cais")) mapInicial = this;
    }

    public Scenery getExit(String direction) {
        return Objects.isNull(this.exits.get(direction)) ? null : this.exits.get(direction);
    }

    public void setExit(String direction, Scenery neighbor) {
        this.exits.put(direction, neighbor);
    }

}
