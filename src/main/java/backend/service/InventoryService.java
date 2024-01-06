package backend.service;

import backend.controller.interfaces.IInventoryService;
import backend.controller.interfaces.IServiceResponse;
import backend.service.component.combination.ServiceCombinationItem;
import backend.service.component.drop.ServiceDropItem;
import backend.service.component.equip.ServiceEquipItem;
import backend.service.component.inventory.open.InventoryOpen;
import backend.service.component.use.ServiceUseItem;
import backend.service.dto.response.ServiceResponse;
import backend.service.mapper.InventoryResponseMapper;
import backend.service.model.Player;

import java.util.List;

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
        var typeMessage = new ServiceCombinationItem(inventory).run(idItens);

        if (!typeMessage.isSucess())
            new ServiceResponse(typeMessage, null);

        var obj = new InventoryResponseMapper().apply(inventory);
        return new ServiceResponse(typeMessage, obj);
    }

    @Override
    public IServiceResponse use(Integer idItem) {
        var inventory = PLAYER.getInventory();
        var idMap = PLAYER.getCurrentMap().getId();
        var typeMessage = new ServiceUseItem(inventory).run(idItem, idMap);

        if (!typeMessage.isSucess())
            new ServiceResponse(typeMessage, null);

        var obj = new InventoryResponseMapper().apply(inventory);
        return new ServiceResponse(typeMessage, obj);
    }

    @Override
    public IServiceResponse equip(Integer idItem) {
        var inventory = PLAYER.getInventory();
        var typeMessage = new ServiceEquipItem(inventory).run(idItem);

        if (!typeMessage.isSucess())
            new ServiceResponse(typeMessage, null);

        var obj = new InventoryResponseMapper().apply(inventory);
        return new ServiceResponse(typeMessage, obj);
    }

    @Override
    public IServiceResponse drop(Integer idItem) {
        var inventory = PLAYER.getInventory();
        var typeMessage = new ServiceDropItem(inventory).run(idItem);

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
}
