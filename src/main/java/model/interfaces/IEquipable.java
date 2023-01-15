package model.interfaces;

import model.Item;

public interface IEquipable {
    boolean equip(Item item);
    boolean unequip(Item item);
    boolean isEquipped();
}
