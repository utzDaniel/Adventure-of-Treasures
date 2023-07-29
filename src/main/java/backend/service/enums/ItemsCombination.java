package backend.service.enums;

import backend.controller.enums.TypeMessage;

public enum ItemsCombination {
    //espeficidar os itens necessario para combinar , uma vez que no futuro poderar ter
    // 2xMadeira assim poderar dar a quantidade necessaria para combinar

    MAPA(TypeMessage.COMBINE_SUCESS_MAP),
    ESCADA(TypeMessage.COMBINE_SUCESS_LADDER),
    TOCHA(TypeMessage.COMBINE_SUCESS_TORCH);

    private final TypeMessage typeMessage;

    ItemsCombination(TypeMessage typeMessage) {
        this.typeMessage = typeMessage;
    }

    public TypeMessage getTypeMessage() {
        return typeMessage;
    }
}

