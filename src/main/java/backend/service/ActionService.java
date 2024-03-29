package backend.service;

import backend.Game;
import backend.controller.enums.TypeMessage;
import backend.controller.interfaces.IActionService;
import backend.controller.interfaces.IServiceResponse;
import backend.service.command.InteractCommand;
import backend.service.command.LoadCommand;
import backend.service.command.MoveCommand;
import backend.service.command.SaveCommand;
import backend.service.component.inventory.quit.InventoryQuit;
import backend.service.dto.response.ServiceResponse;
import backend.service.enums.Direction;
import backend.service.mapper.ActionResponseMapper;

import java.util.Objects;

public final class ActionService implements IActionService {

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
        var inventory = Game.player.getInventory();
        var typeMessage = new InventoryQuit(inventory).run();

        if (!typeMessage.isSucess())
            new ServiceResponse(typeMessage, null);

        var obj = new ActionResponseMapper().apply(Game.player);
        return new ServiceResponse(typeMessage, obj);
    }

    @Override
    public IServiceResponse interact() {
        var typeMessage = new InteractCommand(Game.player).execute();

        if (!typeMessage.isSucess())
            new ServiceResponse(typeMessage, null);

        var obj = new ActionResponseMapper().apply(Game.player);
        return new ServiceResponse(typeMessage, obj);
    }

    @Override
    public IServiceResponse move(String direction) {
        var direction1 = Direction.getInstance(direction);

        if (Objects.isNull(direction1)) return new ServiceResponse(TypeMessage.DIRECTION_ERRO_INVALID, null);

        var cmd = new MoveCommand(Game.player, direction1);
        var typeMessage = cmd.execute();

        if (!typeMessage.isSucess())
            new ServiceResponse(typeMessage, null);

        var obj = new ActionResponseMapper().apply(Game.player);
        return new ServiceResponse(typeMessage, obj);
    }

    @Override
    public IServiceResponse load() {
        var typeMessage = new LoadCommand().execute();
        if (!typeMessage.isSucess())
            new ServiceResponse(typeMessage, null);

        var obj = new ActionResponseMapper().apply(Game.player);
        return new ServiceResponse(typeMessage, obj);
    }

    @Override
    public IServiceResponse save() {
        var typeMessage = new SaveCommand().execute();
        return new ServiceResponse(typeMessage, null);
    }
}
