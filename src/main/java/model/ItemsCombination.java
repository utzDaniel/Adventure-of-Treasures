package model;

public enum ItemsCombination {
    //espeficidar os itens necessario para combinar , uma vez que no futuro poderar ter
    // 2xMadeira assim poderar dar aquantidade necessaria para combinar

    MAPA("mapa", 1, 2),//1 2 papel, livro
    ESCADA("escada", 2, 3),//2 3 madeiras, martelo , pregos
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
        for (int i = 0; i < ItemsCombination.values().length; i++) {
            if (ItemsCombination.values()[i].combine == combine) {
                return ItemsCombination.values()[i].amountCombination;
            }
        }
        return -1;
    }
    public static ItemsCombination getItemCombined(int combine) {
        for (int i = 0; i < ItemsCombination.values().length; i++) {
            if (ItemsCombination.values()[i].combine == combine) {
                return ItemsCombination.values()[i];
            }
        }
        return null;
    }
}

