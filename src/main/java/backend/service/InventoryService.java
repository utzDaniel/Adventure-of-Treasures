package backend.service;

import backend.controller.enums.TypeMessage;
import backend.controller.interfaces.IInventoryService;
import backend.controller.interfaces.IServiceResponse;
import backend.service.command.CommandTool;
import backend.service.component.combination.ServiceCombinationItem;
import backend.service.component.drop.ServiceDropItem;
import backend.service.component.inventory.open.InventoryOpen;
import backend.service.dto.response.ServiceResponse;
import backend.service.enums.ActionItem;
import backend.service.mapper.InventoryResponseMapper;
import backend.service.model.Item;
import backend.service.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class InventoryService implements IInventoryService {
    /**
     * O service é responsável por conter a lógica de negócio da aplicação.
     * Ele encapsula as operações e regras de negócio que são necessárias para realizar as tarefas desejadas.
     * O service recebe os dados do controller, executa a lógica necessária e se comunica com o repository,
     * se for necessário acessar dados persistentes.
     * O service também pode realizar validações adicionais,
     * orquestrar várias operações do repository e aplicar regras de negócio mais complexas.
     */
    private static final Player PLAYER = Player.getInstance();

    @Override
    public IServiceResponse combination(List<Integer> idItens) {
        var inventory = PLAYER.getInventory();

        var itens = new ArrayList<Item>();
        idItens.forEach(id -> itens.add(getItem(id).orElse(null)));

        var typeMessage = TypeMessage.ITEM_ERRO_FOUND;

        if (!itens.isEmpty())
            typeMessage = new ServiceCombinationItem(inventory).run(itens);

        if (!typeMessage.isSucess())
            new ServiceResponse(typeMessage, null);

        var obj = new InventoryResponseMapper().apply(inventory);
        return new ServiceResponse(typeMessage, obj);
    }

    @Override
    public IServiceResponse use(Integer idItem) {
        var inventory = PLAYER.getInventory();
        var idMap = PLAYER.getCurrentMap().getId();
        var coordinate = PLAYER.getCoordinate();

        var item = getItem(idItem);
        var typeMessage = TypeMessage.ITEM_ERRO_FOUND;

        if (item.isPresent()) {
            var cmd = new CommandTool(ActionItem.USE.getCommands(), item.get(), inventory, idMap, coordinate);
            typeMessage = cmd.execute();
        }

        if (!typeMessage.isSucess())
            new ServiceResponse(typeMessage, null);

        var obj = new InventoryResponseMapper().apply(inventory);
        return new ServiceResponse(typeMessage, obj);
    }

    @Override
    public IServiceResponse equip(Integer idItem) {
        var inventory = PLAYER.getInventory();
        var idMap = PLAYER.getCurrentMap().getId();
        var coordinate = PLAYER.getCoordinate();

        var item = getItem(idItem);
        var typeMessage = TypeMessage.ITEM_ERRO_FOUND;

        if (item.isPresent()) {
            var cmd = new CommandTool(ActionItem.EQUIP.getCommands(), item.get(), inventory, idMap, coordinate);
            typeMessage = cmd.execute();
        }

        if (!typeMessage.isSucess())
            new ServiceResponse(typeMessage, null);

        var obj = new InventoryResponseMapper().apply(inventory);
        return new ServiceResponse(typeMessage, obj);
    }

    @Override
    public IServiceResponse drop(Integer idItem) {
        var inventory = PLAYER.getInventory();

        var item = getItem(idItem);
        var typeMessage = TypeMessage.ITEM_ERRO_FOUND;

        if (item.isPresent())
            typeMessage = new ServiceDropItem(inventory).run(item.get());

        if (!typeMessage.isSucess())
            new ServiceResponse(typeMessage, null);

        var obj = new InventoryResponseMapper().apply(inventory);
        return new ServiceResponse(typeMessage, obj);
    }

    @Override
    public IServiceResponse open() {
        var inventory = PLAYER.getInventory();
        var typeMessage = new InventoryOpen(inventory).run();

        if (!typeMessage.isSucess())
            new ServiceResponse(typeMessage, null);

        var obj = new InventoryResponseMapper().apply(inventory);
        return new ServiceResponse(typeMessage, obj);
    }

    private Optional<Item> getItem(Integer idItem) {
        return PLAYER.getInventory().getItens().stream()
                .filter(item1 -> item1.getId() == idItem)
                .findFirst();
    }
}
