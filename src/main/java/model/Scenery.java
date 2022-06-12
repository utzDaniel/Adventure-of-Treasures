package model;

import javax.swing.*;
import java.util.HashMap;

/**
 * Class model.Room - a room in an adventure game.
 * <p>
 * A "model.Room" represents one location in the scenery of the game. It is
 * connected to other rooms via exits. For each existing exit, the room
 * stores a reference to the neighboring room.
 *
 * @author Daniel dos Anjos
 * @version 2021-03-30
 */

public class Scenery extends MapGame {

    private final HashMap<String, Scenery> exits;

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
