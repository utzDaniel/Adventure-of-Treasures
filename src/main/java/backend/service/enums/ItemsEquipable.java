package backend.service.enums;

import backend.controller.enums.TypeMessage;
import backend.service.component.ActivateMapGame;
import backend.service.model.Player;

public enum ItemsEquipable {

    MOCHILA("mochila") {
        @Override
        public TypeMessage equip() {
            player.getInventory().updadeMaxCapacity(UPDATE);
            return TypeMessage.EQUIP_SUCESS_SCHOOLBAG;
        }

        @Override
        public TypeMessage unequip() {
            var capacity = player.getInventory().getCapacity();
            var newCapacity = player.getInventory().getMaxCapacity() - UPDATE;
            if (capacity > newCapacity) return TypeMessage.UNEQUIP_ERRO_SCHOOLBAG;
            player.getInventory().updadeMaxCapacity(-UPDATE);
            return TypeMessage.UNEQUIP_SUCESS_SCHOOLBAG;
        }
    }, TOCHA("tocha") {
        @Override
        public TypeMessage equip() {
            boolean openDoor = new ActivateMapGame().run("tocha");
            if (!openDoor) return TypeMessage.EQUIPABLE_ERRO_TORCH;
            return TypeMessage.EQUIP_SUCESS_TORCH;
        }

        @Override
        public TypeMessage unequip() {
            boolean openDoor = new ActivateMapGame().run("tocha");
            if (!openDoor) return TypeMessage.EQUIPABLE_ERRO_TORCH;
            return TypeMessage.UNEQUIP_SUCESS_TORCH;
        }
    };

    private static final int UPDATE = 5;
    private static final Player player = Player.getInstance();
    private final String label;

    public abstract TypeMessage equip();

    public abstract TypeMessage unequip();

    ItemsEquipable(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
