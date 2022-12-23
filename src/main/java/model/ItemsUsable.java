package model;

import exception.ItemUsableException;
import repository.CreateImageMapGame;

import java.util.Objects;

public enum ItemsUsable {
    PA("pa"){
        @Override
        public boolean use(Player player){
            if (!(player.getInventory().getItem("mapa").isVisible()))
                throw new ItemUsableException("Não foi possível usar esse item, falta algo no inventario!");
            for (Item item : player.getCurrentMap().getItemInvisible()) {
                if (item.getName().equals("chave")) {
                    player.getCurrentMap().setImagemIcon(
                            new CreateImageMapGame().selectImage(player.getCurrentMap().getName()));
                    item.setVisible(true);
                    return true;
                }
                throw new ItemUsableException("Item invisivel não encontrado!");
            }
            throw new ItemUsableException("O mapa não possui itens invisiveis!");
        }},
    CHAVE("chave") {
        @Override
        public boolean use(Player player) {
            Door openDoor = player.getCurrentMap().getDoorMap(
                    player.getPositionPlayerX(), player.getPositionPlayerY());
            if (Objects.isNull(openDoor))
                throw new ItemUsableException("Não foi possível usar esse item, neste local!");
            openDoor.setOpen(true);
            return true;
        }
    },
    ESCADA("escada") {
        @Override
        public boolean use(Player player) {
            Door openDoor = player.getCurrentMap().getDoorMap(player.getPositionPlayerX(), player.getPositionPlayerY());
            if (Objects.isNull(openDoor))
                throw new ItemUsableException("Não foi possível usar esse item, neste local!");
            openDoor.setOpen(true);
            player.getCurrentMap().setImagemIcon(
                    new CreateImageMapGame().selectImage(ItemsUsable.ESCADA.label));
            return true;
        }
    };

    private final String label;

    ItemsUsable(String label) {
        this.label = label;
    }

    public abstract boolean use(Player player);

    public String getLabel() {
        return label;
    }
}
