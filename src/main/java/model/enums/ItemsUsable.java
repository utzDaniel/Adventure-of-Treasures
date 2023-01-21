package model.enums;

import exception.ItemUsableException;
import model.Player;
import repository.CreateImageMapGame;

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
            player.getCurrentMap().setImagemIcon(new CreateImageMapGame().selectImage(player.getCurrentMap().getName()));
            return true;
        }
    },
    CHAVE("chave") {
        @Override
        public boolean use() {
            var door = player.getCurrentMap().getDoorMap(player.getPositionPlayerX(), player.getPositionPlayerY())
                    .orElseThrow(() -> new ItemUsableException("Não foi possível usar esse item, neste local!"));
            door.setOpen(true);
            return true;
        }
    },
    ESCADA("escada") {
        @Override
        public boolean use() {
            var door = player.getCurrentMap().getDoorMap(player.getPositionPlayerX(), player.getPositionPlayerY())
                    .orElseThrow(() -> new ItemUsableException("Não foi possível usar esse item, neste local!"));
            door.setOpen(true);
            player.getCurrentMap().setImagemIcon(new CreateImageMapGame().selectImage(ItemsUsable.ESCADA.label));
            return true;
        }
    };

    static private final Player player = Player.getInstance();

    private final String label;

    ItemsUsable(String label) {
        this.label = label;
    }

    public abstract boolean use();

    public String getLabel() {
        return this.label;
    }
}
