package backend.controller.enums;

public enum TypeMessage {

    GAME_SUCESS_FINISH(true, "Fim do game!", "finish"),
    TAKE_SUCESS_ITEM(true, "Item adicionado na mochila!", "pegar"),
    DOOR_SUCESS_OPEN(true, "Abrindo e entrando pela porta", ""),
    MOVE_SUCESS(true, "Direção e movimentação valida", ""),
    INVENTORY_SUCESS_CLOSED(true, "Inventário foi fechado!", ""),
    INVENTORY_SUCESS_OPEN(true, "Inventário foi aberto", ""),
    REMOVE_INVENTORY(true, "Item removido do inventário", "remover"),
    USABLE_KEY(true, "Chave foi usada", "chave"),
    EVENT_MAP_KEY(true, "A porta do templo foi aberta", ""),
    EVENT_MAP_REMOVE_USABLE_KEY(true, "A chave abriu a porta do templo, e removida do inventário", "chave"),
    USABLE_SHOVEL(true, "Pá foi usada", "pa"),
    EVENT_MAP_SHOVEL(true, "Você achou uma chave enterrada", ""),
    EVENT_INVENTORY_KEY(true, "Chave adicionada ao inventário", ""),
    EVENT_INVENTORY_EVENT_MAP_REMOVE_USABLE_SHOVEL(true, "A pá foi usada e removida do inventário, você achou uma chave enterrada", "pa"),
    USABLE_LADDER(true, "Escada foi usada", "escada"),
    EVENT_MAP_LADDER(true, "Escada colocada na parede do templo", ""),
    EVENT_MAP_REMOVE_USABLE_LADDER(true, "A escada foi colocada na parede do templo e removida do inventário", "escada"),
    EQUIP_SCHOOLBAG(true, "Mochila equipada!", "mochila"),
    UNEQUIP_SCHOOLBAG(true, "Mochila desequipada", "mochila"),
    UNEQUIP_ERRO_SCHOOLBAG(false, "Remove itens da mochila, antes de tentar desequipar", "erro"),
    EQUIP_TORCH(true, "Tocha equipada!", "tocha"),
    EVENT_MAP_TORCH(true, "Agora você consegue entrar no porão do templo!", ""),
    EQUIP_EVENT_MAP_TORCH(true, "Tocha equipada e agora você consegue entrar no porão do templo!", "tocha"),
    UNEQUIP_TORCH(true, "Tocha desequipada", "tocha"),
    COMBINE_SUCESS_TORCH(true, "Item combinados, novo item tocha!", "fogo"),
    COMBINE_SUCESS_MAP(true, "Item combinados, novo item mapa!", "mapa"),
    COMBINE_SUCESS_LADDER(true, "Item combinados, novo item escada!", "construir"),
    MOVE_BLOCKED(true, "Movimentação bloqueada", ""),
    MOVE_MAP_NOT_FOUND(true, "Map não encontrado", ""),
    MOVE_MAP_NOT_EXIT(true, "Não existe saída para essa direção", ""),
    DOOR_CLOSED(false, "Porta está fechada!", "erro"),
    DOOR_NOT_EXIT(false, "Porta não existe!", ""),
    REMOVE_INVENTORY_ERRO(false, "Item não pode ser removido!", "erro"),
    REMOVE_INVENTORY_ERRO_EQUIP(false, "Não pode remover item equipado!", "erro"),
    INVENTORY_ERRO_FULL(false, "Inventário cheio!", "erro"),
    INVENTORY_NOT_CLOSED(false, "Inventário já está está fechado!", "erro"),
    INVENTORY_NOT_OPEN(false, "Inventário já está está aberto!", "erro"),
    ITEM_ERRO_FOUND(false, "Item não encontrado!", "erro"),
    ITEM_NOT_EQUIPABLE(false, "Item não é do tipo equipável!", "erro"),
    ITEM_NOT_USABLE(false, "Item não é do tipo usável!", "erro"),
    COMBINE_NOT_COMBINABLE(false, "Itens não são combináveis entre eles!", "erro"),
    COMBINE_INCOMPLETE(false, "Está faltando Itens para realizar a combinação!", "erro"),
    COMBINE_NOT_ALL(false, "Algum dos itens não é combinável!", "erro"),
    EVENT_ERRO_MAP(false, "Erro ao buscar mapa relacionada ao evento", "erro"),
    EVENT_ERRO_DOOR(false, "Erro ao buscar a porta relacionada ao evento", "erro"),
    USABLE_ERRO_MAP(false, "Esse item não pode ser usado nesse mapa", "erro"),
    USABLE_ERRO_COORDINATE(false, "Esse item não pode ser usado nesse local", "erro"),
    USABLE_ERRO_ENABLE(false, "Esse item não pode ser usado, pois está desabilitado", "erro"),
    USABLE_ERRO_INCOMPLETE(false, "Não foi possível usar esse item, falta algo no inventário!", "erro"),
    USABLE_NOT_LOCAL(false, "Esse item não pode ser usado nesse local", "erro"),
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
