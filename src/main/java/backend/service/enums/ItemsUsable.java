package backend.service.enums;

import backend.service.exception.ItemUsableException;
import backend.service.component.ActivateMapGame;
import backend.service.model.Player;

public enum ItemsUsable {
    PA("pa") {
        @Override
        public boolean use() throws ItemUsableException {
            if (!(player.getInventory().getItem("mapa").isVisible()))
                throw new ItemUsableException("Não foi possível usar esse item, falta algo no inventario!");
            var item = player.getCurrentMap().getItemInvisible().stream()
                    .filter(item1 -> item1.getName().equals("chave"))
                    .findFirst().orElseThrow(() -> new ItemUsableException("O mapa não possui itens invisiveis!"));//TODO colocar log de erro
            item.setVisible(true);
            new ActivateMapGame().run("chave");
            return true;
        }
    },
    CHAVE("chave") {
        @Override
        public boolean use() {
            var door = player.getCurrentMap().getDoor(player.getLocation())
                    .orElseThrow(() -> new ItemUsableException("Não foi possível usar esse item, neste local!"));
            door.setOpen(true);
            return true;
        }
    },
    ESCADA("escada") {
        @Override
        public boolean use() {
            var door = player.getCurrentMap().getDoor(player.getLocation())
                    .orElseThrow(() -> new ItemUsableException("Não foi possível usar esse item, neste local!"));
            door.setOpen(true);
            new ActivateMapGame().run("escada");
            return true;
        }
    };

    private static final Player player = Player.getInstance();

    private final String label;

    ItemsUsable(String label) {
        this.label = label;
    }

    public abstract boolean use();

    public String getLabel() {
        return this.label;
    }
}