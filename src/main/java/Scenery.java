import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class Room - a room in an adventure game.
 * <p>
 * A "Room" represents one location in the scenery of the game. It is
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


//
//    public void setOpen(boolean open) { this.open = open; }//game
//
//
//    /**
//     * Return a string describing the room's exits, for example
//     * "Exits: north west".
//     *
//     * @return Details of the room's exits.
//     */
//    private String getExitString() {
//        String returnString = "\nDireções:";
//        Set<String> keys = this.exits.keySet();
//        for (String exit : keys) {
//            Room roomOpen = this.exits.get(exit);
//            if (roomOpen.getOpen()) {
//                returnString += " " + exit;
//            }
//        }
//        return returnString;
//    }
//
//    /**
//     * Return the room that is reached if we go from this room in direction
//     * "direction". If there is no room in that direction, return null.
//     *
//     * @param direction The exit's direction.
//     * @return The room in the given direction.
//     */

//
//    public Room getRoom(String direction) {
//        return this.exits.get(direction);
//    }//game
//
//    private String getItemString() {
//        String returnStringItem = "\n";
//        for (Item itemRomm : this.item) {
//            if(itemRomm.isVisible()){
//                returnStringItem += itemRomm;
//            }
//        }
//        return returnStringItem;
//    }
//    public ArrayList<Item> getItemInvisible() {
//        ArrayList<Item> listItensInvisible = new ArrayList<>();
//        for (Item itemRomm : this.item) {
//            if(!itemRomm.isVisible()){
//                listItensInvisible.add(itemRomm);
//            }
//        }
//        return listItensInvisible;
//    }
//
//    public Item getItemRoom(String nameItem) {
//        for (Item itemRomm : item) {
//            if (itemRomm.getName().equals(nameItem)) {
//                return itemRomm;
//            }
//        }
//        return null;
//    }
//
//    public void removeItemRomm(Item itens) {
//        this.item.remove(itens);
//    }

}
