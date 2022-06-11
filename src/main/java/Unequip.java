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
        if (itemEquipable.getName().equals("mochila") && player.getCapacity() <= (player.getMaxCapacity()-5)) {
            player.setMaxCapacity(-5);
        } else if (itemEquipable.getName().equals("tocha")) {
            Door openDoor = player.getCurrentMap().getDoorMap(90, 240);
            openDoor.setOpen(false);
        } else {
            return false;
        }
        return true;
    }
}
