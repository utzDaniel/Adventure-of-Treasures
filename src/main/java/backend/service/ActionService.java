package backend.service;

import backend.Game;
import backend.controller.enums.TypeMessage;
import backend.controller.interfaces.IActionService;
import backend.controller.interfaces.IServiceResponse;
import backend.service.command.CommandFactory;
import backend.service.component.inventory.quit.InventoryQuit;
import backend.service.dto.response.ServiceResponse;
import backend.service.enums.Direction;
import backend.service.enums.TypeCommand;
import backend.service.mapper.ActionResponseMapper;

import java.util.Objects;

public final class ActionService implements IActionService {


    @Override
    public IServiceResponse refresh() {
        var inventory = Game.player.getInventory();
        var typeMessage = new InventoryQuit(inventory).run();

        if (!typeMessage.isSuccess())
            new ServiceResponse(typeMessage, null);

        var obj = new ActionResponseMapper().apply(Game.player);
        return new ServiceResponse(typeMessage, obj);
    }

    @Override
    public IServiceResponse interact() {
        var typeMessage = CommandFactory.create(TypeCommand.INTERACT).execute();

        if (!typeMessage.isSuccess())
            new ServiceResponse(typeMessage, null);

        var obj = new ActionResponseMapper().apply(Game.player);
        return new ServiceResponse(typeMessage, obj);
    }

    @Override
    public IServiceResponse move(String direction) {
        var direction1 = Direction.getInstance(direction);

        if (Objects.isNull(direction1)) return new ServiceResponse(TypeMessage.DIRECTION_ERROR_INVALID, null);

        var cmd = CommandFactory.createMoveCommand(Game.player, direction1);
        var typeMessage = cmd.execute();

        if (!typeMessage.isSuccess())
            new ServiceResponse(typeMessage, null);

        var obj = new ActionResponseMapper().apply(Game.player);
        return new ServiceResponse(typeMessage, obj);
    }

    @Override
    public IServiceResponse load() {
        var typeMessage = CommandFactory.create(TypeCommand.LOAD).execute();
        if (!typeMessage.isSuccess())
            new ServiceResponse(typeMessage, null);

        var obj = new ActionResponseMapper().apply(Game.player);
        return new ServiceResponse(typeMessage, obj);
    }

    @Override
    public IServiceResponse save() {
        var typeMessage = CommandFactory.create(TypeCommand.SAVE).execute();
        return new ServiceResponse(typeMessage, null);
    }
}
