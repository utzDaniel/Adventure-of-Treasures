package backend.service.enums;

import backend.controller.enums.TypeMessage;
import backend.service.component.ActivateMapGame;
import backend.service.model.Player;

import java.util.Objects;

public enum ItemsUsable {
    PA("pa") {
        @Override
        public TypeMessage use() {
            new ActivateMapGame().run("chave");
            return TypeMessage.USABLE_SHOVEL;
        }
    },
    CHAVE("chave") {
        @Override
        public TypeMessage use() {
            var door = player.getCurrentMap().getDoor(player.getCoordinate()).orElse(null);
            if (Objects.isNull(door)) return TypeMessage.USABLE_NOT_LOCAL;
            door.setOpen(true);
            return TypeMessage.USABLE_KEY;
        }
    },
    ESCADA("escada") {
        @Override
        public TypeMessage use() {
            var door = player.getCurrentMap().getDoor(player.getCoordinate()).orElse(null);
            if (Objects.isNull(door)) return TypeMessage.USABLE_NOT_LOCAL;
            door.setOpen(true);
            new ActivateMapGame().run("escada");
            return TypeMessage.USABLE_LADDER;
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
