package backend.controller.enums;

public enum TypeMessage {

    GAME_SUCESS_FINISH("Fim do game!", "finish"),
    TAKE_SUCESS_ITEM("Item adicionado na mochila!", "pegar"),
    DOOR_SUCESS_OPEN("Abrindo e entrando pela porta", ""),
    MOVE_SUCESS("Direção e movimentação valida", ""),
    INVENTORY_SUCESS_CLOSED("Inventario foi fechado!", ""),
    INVENTORY_SUCESS_OPEN("Inventario foi aberto", ""),
    DROP_SUCESS("Item removido do inventario", "remover"),
    USABLE_SUCESS_KEY("Chave foi usada e removida do inventario", "chave"),
    USABLE_SUCESS_SHOVEL("Pá foi usada e removida do inventario", "pa"),
    USABLE_SUCESS_LADDER("Escada foi usada e removida do inventario", "escada"),
    EQUIP_SUCESS_SCHOOLBAG("Mochila equipada!", "mochila"),
    UNEQUIP_SUCESS_SCHOOLBAG("Mochila desequipada", "mochila"),
    EQUIP_SUCESS_TORCH("Tocha equipada!", "tocha"),
    UNEQUIP_SUCESS_TORCH("Tocha desequipada", "tocha"),
    COMBINE_SUCESS_TORCH("Item combinados, novo item tocha!", "fogo"),
    COMBINE_SUCESS_MAP("Item combinados, novo item mapa!", "mapa"),
    COMBINE_SUCESS_LADDER("Item combinados, novo item escada!", "construir"),
    DOOR_CLOSED("Porta está fechada!", "erro"),
    DOOR_NOT_EXIT("Porta não existe!", ""),
    INVENTORY_NOT_REMOVE("Item não pode ser removido!", "erro"),
    INVENTORY_NOT_REMOVE_EQUIP("Não pode remover item equipado!", "erro"),
    INVENTORY_FULL("Inventario Cheio!", "erro"),
    INVENTORY_NOT_CLOSED("Inventario está já está fechado!", "erro"),
    INVENTORY_NOT_OPEN("Inventario está já está aberto!", "erro"),
    ITEM_NOT_FOUND("Item não encontrado!", "erro"),
    COMBINE_NOT_COMBINABLE("Itens não são combináveis entre eles!", "erro"),
    COMBINE_INCOMPLETE("Está faltando Itens para realizar a combinação!", "erro"),
    COMBINE_NOT_ALL("Algum dos itens não é combinavel!", "erro"),
    UNEQUIP_ERRO_SCHOOLBAG("Remove itens da mochila, antes de tentar desequipar", "erro"),
    EQUIPABLE_ERRO_TORCH("Equipe a tocha para desbloquear o acesso a porta", "erro"),
    USABLE_NOT_MAP("Esse item não pode ser usado nesse mapa", "erro"),
    USABLE_NOT_LOCAL("Esse item não pode ser usado nesse local", "erro"),
    USABLE_INCOMPLETE("Não foi possível usar esse item, falta algo no inventario!", "erro"),
    MAP_NOT_FOUND("Map não encontrado", ""),
    MAP_NOT_EXIT("Não existe saída para essa direção", ""),
    MAP_FULL_ITEM("Map está cheio, não é possivel remover o item", ""),
    DIRECTION_INVALID("Direção invalida!", "");

    private final String text;
    private final String effect;

    TypeMessage(String text, String effect) {
        this.text = text;
        this.effect = effect;
    }

    public boolean isSucess() {
        return this.name().contains("SUCESS");
    }

    public String getText() {
        return this.text;
    }

    public String getEffect() {
        return String.format("src/main/resources/audio/effects/%s.wav", this.effect);
    }

}
