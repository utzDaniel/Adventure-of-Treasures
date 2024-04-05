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
import backend.service.memento.BackupMementoFactory;

import java.util.Objects;

public final class ActionService implements IActionService {

    private static final String SAVE_FILENAME = "src/main/resources/save/player.txt";

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
        var typeMessage = new InteractCommand(Game.player).execute();

        if (!typeMessage.isSuccess())
            new ServiceResponse(typeMessage, null);

        var obj = new ActionResponseMapper().apply(Game.player);
        return new ServiceResponse(typeMessage, obj);
    }

    @Override
    public IServiceResponse move(String direction) {
        var direction1 = Direction.getInstance(direction);

        if (Objects.isNull(direction1)) return new ServiceResponse(TypeMessage.DIRECTION_ERROR_INVALID, null);

        var cmd = new MoveCommand(Game.player, direction1);
        var typeMessage = cmd.execute();

        if (!typeMessage.isSuccess())
            new ServiceResponse(typeMessage, null);

        var obj = new ActionResponseMapper().apply(Game.player);
        return new ServiceResponse(typeMessage, obj);
    }

    @Override
    public IServiceResponse load() {
        var typeMessage = new LoadCommand(SAVE_FILENAME).execute();
        if (!typeMessage.isSuccess())
            new ServiceResponse(typeMessage, null);

        var obj = new ActionResponseMapper().apply(Game.player);
        return new ServiceResponse(typeMessage, obj);
    }

    @Override
    public IServiceResponse save() {
        var memento = new BackupMementoFactory().create();
        var typeMessage = new SaveCommand(SAVE_FILENAME, memento).execute();
        return new ServiceResponse(typeMessage, null);
    }
}
