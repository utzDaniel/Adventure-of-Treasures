package backend.service.model;

import backend.service.enums.TypeItem;
import backend.service.interfaces.IEquipable;

public final class Equipable implements IEquipable {

    private int upCapacity;
    //OBSERVE
    private boolean equip = false;

    public Equipable(int upCapacity) {
        this.upCapacity = upCapacity;
    }

    @Override
    public boolean isEquip() {
        return this.equip;
    }

    @Override
    public void setEquip(boolean equip) {
        this.equip = equip;
    }

    @Override
    public boolean isType(TypeItem type) {
        return TypeItem.EQUIPABLE == type;
    }

    @Override
    public boolean isRemovable() {
        return !equip;
    }

    @Override
    public String toString() {
        return TypeItem.EQUIPABLE.toString();
    }
}
