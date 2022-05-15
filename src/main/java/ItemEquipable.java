import javax.swing.*;

public class ItemEquipable extends Item implements Equipable {

    private boolean equipped;
    private Room room;

    public ItemEquipable(String name, String description, int weight, int positionItemX, int positionItemY, ImageIcon imagemIcon) {
        super(name, description, weight, positionItemX, positionItemY, imagemIcon);
        this.equipped = false;
        this.room = null;
    }

    @Override
    public boolean equip(Item item, Player player) {
        Equip equip = new Equip(player);
        if (!isEquipped()) {
            boolean isEquip = equip.validItemEquipable(item);
            setEquipped(isEquip);
            return isEquip;
        }
        return false;
    }

    @Override
    public boolean unequip(Item item, Player player) {
        Unequip unequip = new Unequip(player);
        if (isEquipped()) {
            boolean isUnequip = unequip.validItemEquipable(item);
            setEquipped(!isUnequip);
            return isUnequip;
        }
        return false;
    }


    private void setEquipped(boolean equipped) {
        this.equipped = equipped;
    }

    public boolean isEquipped() {
        return this.equipped;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return this.room;
    }
}
