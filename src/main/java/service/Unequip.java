package service;

import model.IEquipable;
import model.Item;
import model.ItemsEquipable;
import model.Player;

public final class Unequip {

    private final Player player;

    public Unequip(Player player){
        this.player = player;
    }

    public boolean validItemEquipable(Item item) {
        return item instanceof IEquipable && unequipItem(item);
    }

    //item equipavel com room e outro sem, será que deve criar uma nova classe?
    private boolean unequipItem(Item itemEquipable) {
        for (int i = 0; i < ItemsEquipable.values().length; i++) {
            if (ItemsEquipable.values()[i].getLabel().equals(itemEquipable.getName())){
                return ItemsEquipable.values()[i].unequip(player);
            }
        }
        return false;
    }
}
