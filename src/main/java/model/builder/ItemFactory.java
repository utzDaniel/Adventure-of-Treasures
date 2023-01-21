package model.builder;

import model.builder.item.*;
import model.interfaces.IBuilderItem;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;

public class ItemFactory {

    private String [] dados;

    public Item create(String[] dados) {
        this.dados = dados;
        return type();
    }

    private Item type() {
        return switch (dados[0]) {
            case "11111110001" -> createItemCombinable();
            case "11111101001" -> createItemEquipable();
            case "11111100100" -> createItemNotRemove();
            case "11111100011" -> createItemUsable();
            default -> null;
        };
    }

    private void inicial(IBuilderItem item) {
        item.name(dados[1])
                .description(dados[2])
                .weight(parseInt(dados[3]))
                .positionX(parseInt(dados[4]))
                .positionY(parseInt(dados[5]))
                .image(dados[6])
                .removable(parseBoolean(dados[11]))
                .visible(true);
    }

    private Item createItemCombinable() {
        var itemCombinable = ItemCombinableBuilder.builder();
        inicial(itemCombinable);
        return itemCombinable.combine(parseInt(dados[7]))
                .build();
    }

    private Item createItemEquipable() {
        var itemEquipable = ItemEquipableBuilder.builder();
        inicial(itemEquipable);
        return itemEquipable.equipped(false)
                .build();
    }

    private Item createItemUsable() {
        var itemUsable = ItemUsableBuilder.builder();
        inicial(itemUsable);
        return itemUsable.localUse(dados[10])
                .build();
    }

    private Item createItemNotRemove() {
        var itemNotRemove = ItemMissionBuilder.builder();
        inicial(itemNotRemove);
        return itemNotRemove.mapGame(dados[9])
                .build();
    }
}
