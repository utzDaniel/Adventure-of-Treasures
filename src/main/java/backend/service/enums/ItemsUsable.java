package backend.service.enums;

import backend.controller.enums.TypeMessage;
import backend.service.component.ActivateMapGame;
import backend.service.model.Player;

import java.util.Objects;

public enum ItemsUsable {
    PA("pa") {
        @Override
        public TypeMessage use() {
            if (!(player.getInventory().getItem("mapa").isVisible()))
                return TypeMessage.USABLE_INCOMPLETE;
            var item = player.getCurrentMap().getItemInvisible().stream()
                    .filter(item1 -> item1.getName().equals("chave"))
                    .findFirst().orElse(null);
            if (Objects.isNull(item)) return TypeMessage.ITEM_NOT_FOUND;
            item.setVisible(true);
            new ActivateMapGame().run("chave");
            return TypeMessage.USABLE_SUCESS_SHOVEL;
        }
    },
    CHAVE("chave") {
        @Override
        public TypeMessage use() {
            var door = player.getCurrentMap().getDoor(player.getLocation()).orElse(null);
            if (Objects.isNull(door)) return TypeMessage.USABLE_NOT_LOCAL;
            door.setOpen(true);
            return TypeMessage.USABLE_SUCESS_KEY;
        }
    },
    ESCADA("escada") {
        @Override
        public TypeMessage use() {
            var door = player.getCurrentMap().getDoor(player.getLocation()).orElse(null);
            if (Objects.isNull(door)) return TypeMessage.USABLE_NOT_LOCAL;
            door.setOpen(true);
            new ActivateMapGame().run("escada");
            return TypeMessage.USABLE_SUCESS_LADDER;
        }
    };

    private static final Player player = Player.getInstance();

    private final String label;

    ItemsUsable(String label) {
        this.label = label;
    }

    public abstract TypeMessage use();

    public String getLabel() {
        return this.label;
    }
}
