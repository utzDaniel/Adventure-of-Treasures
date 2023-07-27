package backend.service;

import backend.controller.interfaces.IActionService;
import backend.controller.interfaces.IServiceResponse;
import backend.service.component.inventory.quit.InventoryQuit;
import backend.service.component.move.MovePlayer;
import backend.service.component.open.Open;
import backend.service.component.take.Take;
import backend.service.dto.response.ServiceResponse;
import backend.service.mapper.ActionResponseMapper;
import backend.service.model.Player;

public final class ActionService<T> implements IActionService {

    private static final Player PLAYER = Player.getInstance();
    /**
     * O service é responsável por conter a lógica de negócio da aplicação.
     * Ele encapsula as operações e regras de negócio que são necessárias para realizar as tarefas desejadas.
     * O service recebe os dados do controller, executa a lógica necessária e se comunica com o repository,
     * se for necessário acessar dados persistentes.
     * O service também pode realizar validações adicionais,
     * orquestrar várias operações do repository e aplicar regras de negócio mais complexas.
     */
    @Override
    public IServiceResponse refresh() {
        var inventory = PLAYER.getInventory();
        var typeMessage = new InventoryQuit(inventory).run();

        if(!typeMessage.isSucess())
            new ServiceResponse(typeMessage,null);

        var obj = new ActionResponseMapper().apply(PLAYER);
        return new ServiceResponse(typeMessage,obj);
    }

    @Override
    public IServiceResponse take() {
        var typeMessage = new Take(PLAYER).run();

        if(!typeMessage.isSucess())
            new ServiceResponse(typeMessage,null);

        var obj = new ActionResponseMapper().apply(PLAYER);
        return new ServiceResponse(typeMessage,obj);
    }

    @Override
    public IServiceResponse open() {
        var typeMessage = new Open(PLAYER).run();

        if(!typeMessage.isSucess())
            new ServiceResponse(typeMessage,null);

        var obj = new ActionResponseMapper().apply(PLAYER);
        return new ServiceResponse(typeMessage,obj);
    }

    @Override
    public IServiceResponse move(String direction) {
        var typeMessage = new MovePlayer(direction, PLAYER).run();

        if(!typeMessage.isSucess())
            new ServiceResponse(typeMessage,null);

        var obj = new ActionResponseMapper().apply(PLAYER);
        return new ServiceResponse(typeMessage,obj);
    }


}
