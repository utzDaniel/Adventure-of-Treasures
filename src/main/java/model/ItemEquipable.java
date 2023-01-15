package model;

import model.interfaces.IEquipable;
import service.Equip;
import service.Unequip;

import javax.swing.*;
import java.util.List;

public class ItemEquipable extends Item implements IEquipable {

    private boolean equipped;
//    private Room room;

    public ItemEquipable(String name, String description, int weight, int positionItemX, int positionItemY, ImageIcon imagemIcon) {
        super(name, description, weight, positionItemX, positionItemY, imagemIcon);
        this.equipped = false;
//        this.room = null;
    }

    @Override
    public boolean equip(Item item) {
        this.equipped = new Equip(item).run();
        return this.equipped;
    }

    @Override
    public boolean unequip(Item item) {
        this.equipped = !new Unequip(item).run();
        return !this.equipped;
    }

    @Override
    public boolean isEquipped() {
        return this.equipped;
    }

    @Override
    public boolean action(Item item) {
        return this.isEquipped() ?
                this.unequip(item) : this.equip(item);
    }

    @Override
    public boolean action(List<Item> itens) {
        return action(itens.get(0));
    }

//    public void setRoom(Room room) {
//        this.room = room;
//    }
//
//    public Room getRoom() {
//        return this.room;
//    }
}
