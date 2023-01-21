package model.builder.item;

import model.interfaces.IEquipable;
import service.Equip;
import service.Unequip;

import java.util.List;

public class ItemEquipable extends Item implements IEquipable {

    private boolean equipped;
//    private Room room;

    protected ItemEquipable() {
    }

    protected void setEquipped(boolean equipped) {
        this.equipped = equipped;
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

    @Override
    public String toString() {
        return "ItemEquipable{" +
                "equipped=" + equipped +
                "} " + super.toString();
    }

    //    public void setRoom(Room room) {
//        this.room = room;
//    }
//
//    public Room getRoom() {
//        return this.room;
//    }
}
