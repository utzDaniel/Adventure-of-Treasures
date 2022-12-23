package model;

import service.Equip;
import service.Unequip;

import javax.swing.*;

public class ItemEquipable extends Item implements IEquipable {

    private boolean equipped;
//    private Room room;

    public ItemEquipable(String name, String description, int weight, int positionItemX, int positionItemY, ImageIcon imagemIcon) {
        super(name, description, weight, positionItemX, positionItemY, imagemIcon);
        this.equipped = false;
//        this.room = null;
    }

    @Override
    public boolean equip(Item item, Player player) {
        if (isEquipped()) return false;
        this.equipped = new Equip(player,item).run();
        return this.equipped ;
    }

    @Override
    public boolean unequip(Item item, Player player) {
        if (!isEquipped()) return false;
        this.equipped = !new Unequip(player, item).run();
        return !this.equipped;
    }

    @Override
    public boolean isEquipped() {
        return this.equipped;
    }

//    public void setRoom(Room room) {
//        this.room = room;
//    }
//
//    public Room getRoom() {
//        return this.room;
//    }
}
