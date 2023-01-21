package model.enums;

import exception.ItemUsableException;
import model.Door;
import model.builder.item.Item;
import model.Player;
import repository.CreateImageMapGame;

import java.util.Objects;

public enum ItemsUsable {
    PA("pa"){
        @Override
        public boolean use(){
            if (!(Player.getInstance().getInventory().getItem("mapa").isVisible()))
                throw new ItemUsableException("Não foi possível usar esse item, falta algo no inventario!");
            for (Item item : Player.getInstance().getCurrentMap().getItemInvisible()) {
                if (item.getName().equals("chave")) {
                    Player.getInstance().getCurrentMap().setImagemIcon(
                            new CreateImageMapGame().selectImage(Player.getInstance().getCurrentMap().getName()));
                    item.setVisible(true);
                    return true;
                }
                throw new ItemUsableException("Item invisivel não encontrado!");
            }
            throw new ItemUsableException("O mapa não possui itens invisiveis!");
        }},
    CHAVE("chave") {
        @Override
        public boolean use() {
            Door openDoor = Player.getInstance().getCurrentMap().getDoorMap(
                    Player.getInstance().getPositionPlayerX(), Player.getInstance().getPositionPlayerY());
            if (Objects.isNull(openDoor))
                throw new ItemUsableException("Não foi possível usar esse item, neste local!");
            openDoor.setOpen(true);
            return true;
        }
    },
    ESCADA("escada") {
        @Override
        public boolean use() {
            Door openDoor = Player.getInstance().getCurrentMap().getDoorMap(Player.getInstance().getPositionPlayerX(), Player.getInstance().getPositionPlayerY());
            if (Objects.isNull(openDoor))
                throw new ItemUsableException("Não foi possível usar esse item, neste local!");
            openDoor.setOpen(true);
            Player.getInstance().getCurrentMap().setImagemIcon(
                    new CreateImageMapGame().selectImage(ItemsUsable.ESCADA.label));
            return true;
        }
    };

    private final String label;

    ItemsUsable(String label) {
        this.label = label;
    }

    public abstract boolean use();

    public String getLabel() {
        return label;
    }
}
