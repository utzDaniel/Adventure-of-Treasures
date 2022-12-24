package model.enums;

import exception.ItemEquipableException;
import model.Player;

public enum ItemsEquipable {

    MOCHILA("mochila") {
        @Override
        public boolean equip(Player player) {
            player.getInventory().updadeMaxCapacity(5);
            return true;
        }

        @Override
        public boolean unequip(Player player) {
            if (!(player.getInventory().getCapacity() <= (player.getInventory().getMaxCapacity() - 5)))
                throw new ItemEquipableException("Remove itens da mochila, antes de tentar desequipar");
            player.getInventory().updadeMaxCapacity(-5);
            return true;
        }
    }, TOCHA("tocha") {
        @Override
        public boolean equip(Player player) {
            boolean openDoor = player.getCurrentMap().activate("tocha");
            if(!openDoor) throw new ItemEquipableException("Erro ao abrir a door");
            return true;
        }

        @Override
        public boolean unequip(Player player) {
            boolean openDoor = player.getCurrentMap().activate("tocha");
            if(!openDoor) throw new ItemEquipableException("Erro ao abrir a door");
            return true;
        }
    };

    private final String label;

    public abstract boolean equip(Player player);

    public abstract boolean unequip(Player player);

    ItemsEquipable(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
