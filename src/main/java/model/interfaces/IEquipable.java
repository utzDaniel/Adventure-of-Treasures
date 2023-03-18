package model.interfaces;

public interface IEquipable {
    boolean equip();

    boolean unequip();

    boolean isEquipped();
    String getName();

    String getEffect();
}
