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
        if (itemEquipable.getName().equals("mochila")) {
            player.setMaxCapacity(5);
        } else if (itemEquipable.getName().equals("tocha")) {
            Door openDoor = player.getCurrentMap().getDoorMap(90, 240);
            openDoor.setOpen(true);
        } else {
            return false;
        }
        return true;
    }
}
