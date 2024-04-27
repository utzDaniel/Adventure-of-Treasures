package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.interfaces.ICommand;
import backend.service.interfaces.IHandler;
import backend.service.interfaces.IMove;
import backend.service.model.Player;

public final class InteractCommand implements ICommand {

    private static final String NOT_FOUND = "ERROR_FOUND";
    private final Player player;
    private final IHandler<IMove> handler;

    public InteractCommand(Player player, IHandler<IMove> handler) {
        this.player = player;
        this.handler = handler;
    }

    @Override
    public TypeMessage execute() {
        var msg = this.handler.handle(this.player);
        if (msg.isPresent()) return msg.get();

        var command = CommandFactory.createInteractNPCCommand(this.player);
        var typeMessage = command.execute();

        if (typeMessage.name().contains(NOT_FOUND)) {

            command = CommandFactory.createInteractDoorCommand(this.player);
            typeMessage = command.execute();

            if (typeMessage.name().contains(NOT_FOUND)) {

                command = CommandFactory.createInteractItemCommand(this.player);
                typeMessage = command.execute();

            }
        }
        return typeMessage;
    }

}
