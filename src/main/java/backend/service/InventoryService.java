package backend.service;

import backend.controller.interfaces.IInventoryService;
import backend.controller.interfaces.IResponse;
import backend.controller.interfaces.IServiceResponse;
import backend.service.component.combination.ServiceCombinationItem;
import backend.service.component.drop.ServiceDropItem;
import backend.service.component.equip.ServiceEquipItem;
import backend.service.component.inventory.open.InventoryOpen;
import backend.service.component.inventory.quit.InventoryQuit;
import backend.service.component.use.ServiceUseItem;
import backend.service.dto.response.ServiceResponse;
import backend.service.mapper.ActionResponseMapper;
import backend.service.mapper.InventoryResponseMapper;
import backend.service.model.Player;

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
    public IServiceResponse combination(String... names) {
        var inventory = PLAYER.getInventory();
        var typeMessage = new ServiceCombinationItem(inventory).run(names);

        if(!typeMessage.isSucess())
            new ServiceResponse(typeMessage,null);

        var obj = new InventoryResponseMapper().apply(inventory);
        return new ServiceResponse(typeMessage,obj);
    }

    @Override
    public IServiceResponse use(String name) {
        var inventory = PLAYER.getInventory();
        var map = PLAYER.getCurrentMap().getName();
        var typeMessage = new ServiceUseItem(inventory).run(name, map);

        if(!typeMessage.isSucess())
            new ServiceResponse(typeMessage,null);

        var obj = new InventoryResponseMapper().apply(inventory);
        return new ServiceResponse(typeMessage,obj);
    }

    @Override
    public IServiceResponse equip(String name) {
        var inventory = PLAYER.getInventory();
        var typeMessage = new ServiceEquipItem(inventory).run(name);

        if(!typeMessage.isSucess())
            new ServiceResponse(typeMessage,null);

        var obj = new InventoryResponseMapper().apply(inventory);
        return new ServiceResponse(typeMessage,obj);
    }

    @Override
    public IServiceResponse drop(String name) {
        var inventory = PLAYER.getInventory();
        var typeMessage = new ServiceDropItem(inventory).run(name);

        if(!typeMessage.isSucess())
            new ServiceResponse(typeMessage,null);

        var obj = new InventoryResponseMapper().apply(inventory);
        return new ServiceResponse(typeMessage,obj);
    }

    @Override
    public IServiceResponse open() {
        var inventory = PLAYER.getInventory();
        var typeMessage = new InventoryOpen(inventory).run();

        if(!typeMessage.isSucess())
            new ServiceResponse(typeMessage,null);

        var obj = new InventoryResponseMapper().apply(inventory);
        return new ServiceResponse(typeMessage,obj);
    }
}
