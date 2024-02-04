package backend.service;

import backend.controller.enums.TypeMessage;
import backend.controller.interfaces.IActionService;
import backend.controller.interfaces.IServiceResponse;
import backend.service.command.TakeItemCommand;
import backend.service.component.inventory.quit.InventoryQuit;
import backend.service.component.move.MovePlayer;
import backend.service.component.open.Open;
import backend.service.dto.response.ServiceResponse;
import backend.service.enums.Direction;
import backend.service.mapper.ActionResponseMapper;
import backend.service.model.Player;

import java.util.Objects;

public final class ActionService implements IActionService {

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

        if (!typeMessage.isSucess())
            new ServiceResponse(typeMessage, null);

        var obj = new ActionResponseMapper().apply(PLAYER);
        return new ServiceResponse(typeMessage, obj);
    }

    @Override
    public IServiceResponse take() {
        var typeMessage = new TakeItemCommand(PLAYER).execute();

        if (!typeMessage.isSucess())
            new ServiceResponse(typeMessage, null);

        var obj = new ActionResponseMapper().apply(PLAYER);
        return new ServiceResponse(typeMessage, obj);
    }

    @Override
    public IServiceResponse open() {
        var typeMessage = new Open(PLAYER).run();

        if (!typeMessage.isSucess())
            new ServiceResponse(typeMessage, null);

        var obj = new ActionResponseMapper().apply(PLAYER);
        return new ServiceResponse(typeMessage, obj);
    }

    @Override
    public IServiceResponse move(String direction) {
        var direction1 = Direction.getInstance(direction);

        if (Objects.isNull(direction1)) return new ServiceResponse(TypeMessage.DIRECTION_INVALID, null);

        var typeMessage = new MovePlayer(direction1, PLAYER).run();

        if (!typeMessage.isSucess())
            new ServiceResponse(typeMessage, null);

        var obj = new ActionResponseMapper().apply(PLAYER);
        return new ServiceResponse(typeMessage, obj);
    }

}
