package backend.controller.enums;

public enum TypeMessage {

    GAME_SUCESS_FINISH(true, "Fim do game!", "finish"),
    TAKE_SUCESS_ITEM(true, "Item adicionado na mochila!", "pegar"),
    DOOR_SUCESS_OPEN(true, "Abrindo e entrando pela porta", ""),
    MOVE_SUCESS(true, "Direção e movimentação valida", ""),
    INVENTORY_SUCESS_CLOSED(true, "Inventário foi fechado!", ""),
    INVENTORY_SUCESS_OPEN(true, "Inventário foi aberto", ""),
    REMOVE_INVENTORY(true, "Item removido do inventário", "remover"),
    USABLE(true, "Item foi usado!", ""),
    USABLE_KEY(true, "A chave abriu a porta do templo, e removida do inventário", "chave"),
    USABLE_SHOVEL(true, "A pá foi usada e removida do inventário, você achou uma chave enterrada", "pa"),
    USABLE_LADDER(true, "A escada foi colocada na parede do templo e removida do inventário", "escada"),
    EQUIPABLE_ERRO(false, "Item não é equipável!", "erro"),
    EQUIP_SCHOOLBAG(true, "Mochila equipada!", "mochila"),
    UNEQUIP(true, "Item foi desequipado", ""),
    UNEQUIP_SCHOOLBAG(true, "Mochila desequipada", "mochila"),
    UNEQUIP_ERRO_SCHOOLBAG(false, "Remove itens da mochila, antes de tentar desequipar", "erro"),
    EQUIP(true, "Item foi equipado!", ""),
    EQUIP_TORCH(true, "Tocha equipada e agora você consegue entrar no porão do templo!", "tocha"),
    UNEQUIP_TORCH(true, "Tocha desequipada", "tocha"),
    UNEQUIP_ERRO(false, "Erro ao desequipar item", "erro"),
    COMBINE(true, "Itens combinados e removidos, novo item adicionado ao inventário!", ""),
    COMBINE_TORCH(true, "Itens combinados e removidos, novo item tocha adicionado ao inventário!", "fogo"),
    COMBINE_MAP(true, "Itens combinados e removidos, novo item mapa adicionado ao inventário!", "mapa"),
    COMBINE_LADDER(true, "Itens combinados e removidos, novo item escada adicionado ao inventário!", "construir"),
    MOVE_BLOCKED(true, "Movimentação bloqueada", ""),
    MOVE_MAP_NOT_FOUND(true, "Map não encontrado", ""),
    MOVE_MAP_NOT_EXIT(true, "Não existe saída para essa direção", ""),
    DOOR_CLOSED(false, "Porta está fechada!", "erro"),
    DOOR_NOT_EXIT(false, "Porta não existe!", ""),
    REMOVE_ITEM_ERRO(false, "Item não pode ser removido!", "erro"),
    REMOVE_ITEM_ERRO_EQUIP(false, "Não pode remover item equipado!", "erro"),
    REMOVE_ITEM(true, "Item pode ser removido!", ""),
    INVENTORY_ERRO_FULL(false, "Inventário cheio!", "erro"),
    INVENTORY_ITEM_ERRO(false, "Item não está no inventário!", "erro"),
    INVENTORY_ADD(true, "Item adicionado no inventário!", ""),
    INVENTORY_NOT_CLOSED(false, "Inventário já está está fechado!", "erro"),
    INVENTORY_NOT_OPEN(false, "Inventário já está está aberto!", "erro"),
    ITEM_ERRO_FOUND(false, "Item não encontrado!", "erro"),
    ITEM_NOT_EQUIPABLE(false, "Item não é do tipo equipável!", "erro"),
    ITEM_NOT_USABLE(false, "Item não é do tipo usável!", "erro"),
    ITEM_NOT_COMBINABLE(false, "Nenhum dos itens é combináveis!", "erro"),
    COMBINE_ERRO_COMBINABLE(false, "Itens não são combináveis entre eles!", "erro"),
    COMBINE_ERRO_INCOMPLETE(false, "Está faltando itens para realizar a combinação!", "erro"),
    COMBINE_ERRO_INVALID(false, "Combinação invalida!", "erro"),
    COMBINE_ERRO_ALL(false, "Algum dos itens não é combinável!", "erro"),
    USABLE_ERRO_MAP(false, "Esse item não pode ser usado nesse mapa", "erro"),
    USABLE_ERRO_COORDINATE(false, "Esse item não pode ser usado nesse local", "erro"),
    USABLE_ERRO_ENABLE(false, "Esse item não pode ser usado, pois está desabilitado", "erro"),
    MAP_FULL_ITEM(false, "Map está cheio, não é possível remover o item", ""),
    DIRECTION_INVALID(false, "Direção inválida!", ""),
    COMMAND_ERRO(false, "Command inválido!", "");

    private final boolean sucess;
    private final String text;
    private final String effect;

    TypeMessage(boolean sucess, String text, String effect) {
        this.sucess = sucess;
        this.text = text;
        this.effect = effect;
    }

    public boolean isSucess() {
        return this.sucess;
    }

    public String getText() {
        return this.text;
    }

    public String getEffect() {
        return String.format("src/main/resources/audio/effects/%s.wav", this.effect);
    }

}
