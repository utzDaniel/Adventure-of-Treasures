public class Unequip {

    private final Player player;

    public Unequip(Player player){
        this.player = player;
    }

    public boolean validItemEquipable(Item item) {
        if (item instanceof ItemEquipable itemEquipable) {
            return unequipItem(itemEquipable);
        } else {
            return false;
        }
    }

    public boolean unequipItem(ItemEquipable itemEquipable) {
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
