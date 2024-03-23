package backend.service.model;

import backend.repository.interfaces.IEquipableEntity;
import backend.service.enums.TypeItem;
import backend.service.interfaces.IEquipable;

public final class Equipable implements IEquipable {

    private final IEquipableEntity entity;

    private boolean equip;

    public Equipable(IEquipableEntity entity) {
        this.entity = entity;
        this.equip = false;
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
    public int getUpCapacity() {
        return this.entity.upCapacity();
    }

    @Override
    public boolean isType(TypeItem type) {
        return TypeItem.EQUIPABLE == type;
    }

    @Override
    public boolean isRemove() {
        return !this.equip;
    }

    @Override
    public String toString() {
        return TypeItem.EQUIPABLE.toString();
    }
}
