package model.enums;

import exception.ItemEquipableException;
import model.Player;

public enum ItemsEquipable {

    MOCHILA("mochila") {
        @Override
        public boolean equip() {
            Player.getInstance().getInventory().updadeMaxCapacity(5);
            return true;
        }

        @Override
        public boolean unequip() {
            if (!(Player.getInstance().getInventory().getCapacity() <= (Player.getInstance().getInventory().getMaxCapacity() - 5)))
                throw new ItemEquipableException("Remove itens da mochila, antes de tentar desequipar");
            Player.getInstance().getInventory().updadeMaxCapacity(-5);
            return true;
        }
    }, TOCHA("tocha") {
        @Override
        public boolean equip() {
            boolean openDoor = Player.getInstance().getCurrentMap().activate("tocha");
            if(!openDoor) throw new ItemEquipableException("Erro ao abrir a door");
            return true;
        }

        @Override
        public boolean unequip() {
            boolean openDoor = Player.getInstance().getCurrentMap().activate("tocha");
            if(!openDoor) throw new ItemEquipableException("Erro ao abrir a door");
            return true;
        }
    };

    private final String label;

    public abstract boolean equip();

    public abstract boolean unequip();

    ItemsEquipable(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
