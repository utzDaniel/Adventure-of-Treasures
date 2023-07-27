package backend.service.enums;

import backend.controller.enums.TypeMessage;

import java.util.Arrays;

public enum ItemsCombination {
    //espeficidar os itens necessario para combinar , uma vez que no futuro poderar ter
    // 2xMadeira assim poderar dar a quantidade necessaria para combinar

    MAPA("mapa", 1, 2, TypeMessage.COMBINE_SUCESS_MAP),//1 2 papel, livro
    ESCADA("escada", 2, 3, TypeMessage.COMBINE_SUCESS_LADDER),//2 3 madeiras, martelo, pregos
    TOCHA("tocha", 3, 4, TypeMessage.COMBINE_SUCESS_TORCH);//3 4 madeira, pederneira, faca e frasco

    private final String label;
    private final int combine;
    private final int amountCombination;
    private final TypeMessage typeMessage;

    ItemsCombination(String label, int combine, int amountCombination, TypeMessage typeMessage) {
        this.label = label;
        this.combine = combine;
        this.amountCombination = amountCombination;
        this.typeMessage = typeMessage;
    }

    public String getLabel() {
        return label;
    }

    public TypeMessage getTypeMessage() {
        return typeMessage;
    }

    public static int getAmountCombination(int combine) {
        return Arrays.stream(ItemsCombination.values())
                .filter(item -> item.combine == combine)
                .mapToInt(item -> item.amountCombination)
                .findFirst().orElse(-1);
    }

    public static ItemsCombination getItemCombined(int combine) {
        return Arrays.stream(ItemsCombination.values())
                .filter(v -> v.combine == combine)
                .findFirst().orElse(null);
    }
}

