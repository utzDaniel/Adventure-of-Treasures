package model;

public final class Equip {

    private final Player player;

    public Equip(Player player){
        this.player = player;
    }

    public boolean validItemEquipable(Item item) {
        return item instanceof IEquipable && equipItem(item);
    }

    //item equipavel com room e outro sem, será que deve criar uma nova classe?
    private boolean equipItem(Item itemEquipable) {
        for (int i = 0; i < ItemsEquipable.values().length; i++) {
            if (ItemsEquipable.values()[i].getLabel().equals(itemEquipable.getName())){
                return ItemsEquipable.values()[i].equip(player);
            }
        }
        return false;
    }
}
