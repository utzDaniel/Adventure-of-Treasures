package model.interfaces;

import model.Item;
import model.Player;

public interface IEquipable {
    boolean equip(Item item, Player player);
    boolean unequip(Item item, Player player);
    boolean isEquipped();
}
