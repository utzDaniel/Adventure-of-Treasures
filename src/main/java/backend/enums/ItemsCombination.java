package backend.enums;

import java.util.Arrays;
import java.util.Optional;

public enum ItemsCombination {
    //espeficidar os itens necessario para combinar , uma vez que no futuro poderar ter
    // 2xMadeira assim poderar dar a quantidade necessaria para combinar

    MAPA("mapa", 1, 2),//1 2 papel, livro
    ESCADA("escada", 2, 3),//2 3 madeiras, martelo, pregos
    TOCHA("tocha", 3, 4);//3 4 madeira, pederneira, faca e frasco

    private final String label;
    private final int combine;
    private final int amountCombination;

    ItemsCombination(String label, int combine, int amountCombination) {
        this.label = label;
        this.combine = combine;
        this.amountCombination = amountCombination;
    }

    public String getLabel() {
        return label;
    }

    public static int getAmountCombination(int combine) {
        return Arrays.stream(ItemsCombination.values())
                .filter(item -> item.combine == combine)
                .mapToInt(item -> item.amountCombination)
                .findFirst().orElse(-1);
    }

    public static Optional<ItemsCombination> getItemCombined(int combine) {
        return Arrays.stream(ItemsCombination.values())
                .filter(itemsCombination -> itemsCombination.combine == combine)
                .findFirst();
    }
}

