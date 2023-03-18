package model.builder;

import model.Coordinate;
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

    private IBuilderItem inicial(IBuilderItem item) {
        return item.name(dados[1])
                .description(dados[2])
                .weight(parseInt(dados[3]))
                .coordinate(new Coordinate(parseInt(dados[4]),parseInt(dados[5])))
                .image(dados[6])
                .removable(parseBoolean(dados[11]))
                .visible(parseBoolean(dados[12]));
    }

    private Item createItemCombinable() {
        return inicial(ItemCombinableBuilder
                .builder()
                .combine(parseInt(dados[7]))
                .effect(dados[13]))
                .build();
    }

    private Item createItemEquipable() {
        return inicial(ItemEquipableBuilder
                .builder()
                .equipped(false)
                .effect(dados[14]))
                .build();
    }

    private Item createItemUsable() {
        return inicial(ItemUsableBuilder
                .builder()
                .localUse(dados[10])
                .effect(dados[15]))
                .build();
    }

    private Item createItemNotRemove() {
        return inicial(ItemMissionBuilder
                .builder()
                .mapGame(dados[9]))
                .build();
    }
}