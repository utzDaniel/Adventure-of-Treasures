package backend.model.builder.item;

import backend.model.interfaces.ICombinable;
import backend.model.interfaces.IEquipable;
import rules.service.Equip;
import rules.service.Unequip;

import java.util.List;

public class ItemEquipable extends Item implements IEquipable {

    private boolean equipped;
    //    private Room room;
    private String effect;

    protected ItemEquipable() {
    }

    protected void setEquipped(boolean equipped) {
        this.equipped = equipped;
    }

    @Override
    public boolean equip() {
        this.equipped = new Equip(this).run();
        return this.equipped;
    }

    @Override
    public boolean unequip() {
        this.equipped = !new Unequip(this).run();
        return !this.equipped;
    }

    @Override
    public boolean isEquipped() {
        return this.equipped;
    }

    @Override
    public boolean action() {
        return this.isEquipped() ?
                this.unequip() : this.equip();
    }

    @Override
    public String getEffect() {
        return this.effect;
    }
    protected void setEffect(String filename) {
        this.effect = filename;
    }

    @Override
    public boolean action(List<ICombinable> itens) {
        return action();
    }

    @Override
    public String toString() {
        return "ItemEquipable{" +
                "equipped=" + this.equipped +
                ", effect='" + this.effect +"} "+ super.toString();
    }

    //    public void setRoom(Room room) {
//        this.room = room;
//    }
//
//    public Room getRoom() {
//        return this.room;
//    }
}
