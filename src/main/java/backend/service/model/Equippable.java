package backend.service.model;

import backend.repository.interfaces.IEquippableEntity;
import backend.service.enums.TypeItem;
import backend.service.interfaces.IEquippable;

public final class Equippable implements IEquippable {

    private final IEquippableEntity entity;

    private boolean equip;

    public Equippable(IEquippableEntity entity) {
        this.entity = entity;
        this.equip = false;
    }

    @Override
    public int id() {
        return this.entity.idItem();
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
        return TypeItem.EQUIPPABLE == type;
    }

    @Override
    public boolean isRemove() {
        return !this.equip;
    }

    @Override
    public String toString() {
        return TypeItem.EQUIPPABLE.toString();
    }
}
