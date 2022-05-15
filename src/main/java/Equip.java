public class Equip {

    private final Player player;

    public Equip(Player player){
        this.player = player;
    }


    public boolean validItemEquipable(Item item) {
        if (item instanceof ItemEquipable) {
            ItemEquipable itemEquipable = (ItemEquipable) item;
            return equipItem(itemEquipable);
        } else {
            return false;
        }
    }

    public boolean equipItem(ItemEquipable itemEquipable) {
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
