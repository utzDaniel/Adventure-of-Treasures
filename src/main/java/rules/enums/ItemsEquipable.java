package rules.enums;

import rules.exception.ItemEquipableException;
import backend.model.Player;

public enum ItemsEquipable {

    MOCHILA("mochila") {
        @Override
        public boolean equip() {
            player.getInventory().updadeMaxCapacity(UPDATE);
            return true;
        }

        @Override
        public boolean unequip() {
            if (!(player.getInventory().getCapacity() <= (player.getInventory().getMaxCapacity() - UPDATE)))
                throw new ItemEquipableException("Remove itens da mochila, antes de tentar desequipar");
            player.getInventory().updadeMaxCapacity(-UPDATE);
            return true;
        }
    }, TOCHA("tocha") {
        @Override
        public boolean equip() {
            boolean openDoor = player.getCurrentMap().activate("tocha");
            if(!openDoor) throw new ItemEquipableException("Erro ao abrir a door");
            return true;
        }

        @Override
        public boolean unequip() {
            boolean openDoor = player.getCurrentMap().activate("tocha");
            if(!openDoor) throw new ItemEquipableException("Erro ao abrir a door");
            return true;
        }
    };

    static private final int UPDATE = 5;
    static private final Player player = Player.getInstance();
    private final String label;

    public abstract boolean equip();

    public abstract boolean unequip();

    ItemsEquipable(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
