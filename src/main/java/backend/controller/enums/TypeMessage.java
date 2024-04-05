package backend.controller.enums;

public enum TypeMessage {

    GAME_FINISH(true, "Fim do game!", "finish"),
    SAVE(true, "Jogo salvo!", ""),
    LOAD(true, "Jogo carregado!", ""),
    BACKUP(true, "Backup carregado!", ""),
    DOOR_OPEN(true, "Abrindo e entrando pela porta", ""),
    NPC_INTERACT(true, "Interação realizada com NPC", ""),
    MOVE(true, "Movimentação valida", ""),
    INVENTORY_CLOSED(true, "Inventário foi fechado!", ""),
    INVENTORY_OPEN(true, "Inventário foi aberto", ""),
    REMOVE_ITEM_INVENTORY(true, "Item removido do inventário", ""),
    ADD_ITEM_MAP(true, "Item adicionado no map", ""),
    DROP_ITEM(true, "Item removido do inventário e adicionado no map", "remover"),
    REMOVE_ITEM_MAP(true, "Item removido do map", ""),
    ADD_ITEM_INVENTORY(true, "Item adicionado no inventário!", ""),
    TAKE_ITEM(true, "Item adicionado na mochila!", "pegar"),
    USABLE(true, "Item foi usado!", ""),
    USABLE_KEY(true, "A chave abriu a porta do templo, e removida do inventário", "chave"),
    USABLE_SHOVEL(true, "A pá foi usada e removida do inventário, você achou uma chave enterrada", "pa"),
    USABLE_LADDER(true, "A escada foi colocada na parede do templo e removida do inventário", "escada"),
    EQUIP_SCHOOLBAG(true, "Mochila equipada!", "mochila"),
    UNEQUIPPED(true, "Item foi desequipado", ""),
    UNEQUIPPED_SCHOOLBAG(true, "Mochila desequipada", "mochila"),
    UNEQUIPPED_ERROR_SCHOOLBAG(false, "Remove itens da mochila, antes de tentar desequipar", "erro"),
    EQUIP(true, "Item foi equipado!", ""),
    EQUIP_TORCH(true, "Tocha equipada e agora você consegue entrar no porão do templo!", "tocha"),
    UNEQUIPPED_TORCH(true, "Tocha desequipada", "tocha"),
    UNEQUIPPED_ERROR(false, "Erro ao desequipar item", "erro"),
    COMBINE(true, "Itens combinados e removidos, novo item adicionado ao inventário!", ""),
    COMBINE_TORCH(true, "Itens combinados e removidos, novo item tocha adicionado ao inventário!", "fogo"),
    COMBINE_MAP(true, "Itens combinados e removidos, novo item mapa adicionado ao inventário!", "mapa"),
    COMBINE_LADDER(true, "Itens combinados e removidos, novo item escada adicionado ao inventário!", "construir"),
    MOVE_BLOCKED(true, "Movimentação bloqueada", ""),
    MAP_NOT_FOUND(true, "Map não encontrado", ""),
    MOVE_NEXT_SCENERY_NOT_EXIT(true, "Não existe saída para essa direção", ""),
    INTERACT_ERROR(false, "Sem interação!", ""),
    DOOR_ERROR_CLOSED(false, "Porta está fechada!", "erro"),
    DOOR_ERROR_FOUND(false, "Porta não existe!", ""),
    NPC_ERROR_FOUND(false, "Sem interação com o NPC!", ""),
    NPC_ERROR_INCOMPLETE(true, "Interação realizada com o NPC, mas incompleta", ""),
    REMOVE_ITEM_ERROR(false, "Item não pode ser removido!", "erro"),
    REMOVE_ITEM_ERROR_EQUIP(false, "Não pode remover item equipado!", "erro"),
    REMOVE_ITEM(true, "Item pode ser removido!", ""),
    INVENTORY_ERROR_FULL(false, "Inventário cheio!", "erro"),
    INVENTORY_ITEM_ERROR(false, "Item não está no inventário!", "erro"),
    INVENTORY_ERROR_CLOSED(false, "Inventário já está está fechado!", "erro"),
    INVENTORY_ERROR_OPEN(false, "Inventário já está está aberto!", "erro"),
    ITEM_ERROR_FOUND(false, "Item não encontrado!", "erro"),
    ITEM_ERROR_EQUIPPABLE(false, "Item não é do tipo equipável!", "erro"),
    ITEM_ERROR_USABLE(false, "Item não é do tipo usável!", "erro"),
    ITEM_ERROR_COMBINABLE(false, "Nenhum dos itens é combináveis!", "erro"),
    COMBINE_ERROR_COMBINABLE(false, "Itens não são combináveis entre eles!", "erro"),
    COMBINE_ERROR_INCOMPLETE(false, "Está faltando itens para realizar a combinação!", "erro"),
    COMBINE_ERROR_INVALID(false, "Combinação invalida!", "erro"),
    COMBINE_ERROR_ALL(false, "Algum dos itens não é combinável!", "erro"),
    USABLE_ERROR_MAP(false, "Esse item não pode ser usado nesse mapa", "erro"),
    USABLE_ERROR_COORDINATE(false, "Esse item não pode ser usado nesse local", "erro"),
    USABLE_ERROR_ENABLE(false, "Esse item não pode ser usado, pois está desabilitado", "erro"),
    MAP_ADD_ERROR_FULL(false, "Map está cheio, não é possível remover o item", ""),
    DIRECTION_ERROR_INVALID(false, "Direção inválida!", ""),
    COMMAND_ERROR(false, "Comando inválido!", ""),
    BACKUP_ERROR(false, "Erro ao fazer backup jogo!", ""),
    SAVE_ERROR(false, "Erro ao salvar jogo!", ""),
    LOAD_ERROR(false, "Erro ao carregar jogo!", "");

    private final boolean success;
    private final String text;
    private final String effect;

    TypeMessage(boolean success, String text, String effect) {
        this.success = success;
        this.text = text;
        this.effect = effect;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public String getText() {
        return this.text;
    }

    public String getEffect() {
        return String.format("src/main/resources/audio/effects/%s.wav", this.effect);
    }

}
