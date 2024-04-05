package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.interfaces.ICommand;
import backend.service.model.Player;

public final class InteractCommand implements ICommand {

    private static final String NOT_FOUND = "ERROR_FOUND";
    private final Player player;
    private ICommand command;

    public InteractCommand(Player player) {
        this.player = player;
    }

    @Override
    public TypeMessage execute() {
        var coordinate = this.player.getCoordinate();
        coordinate.move(this.player.getDirection().getMove());
        if(!player.getCurrentMap().isInteract(coordinate)){
            return TypeMessage.INTERACT_ERROR;
        }

        this.command = new InteractNPCCommand(this.player);
        var msg = this.command.execute();
        if (msg.name().contains(NOT_FOUND)) {
            this.command = new InteractDoorCommand(this.player);
            msg = this.command.execute();
            if (msg.name().contains(NOT_FOUND)) {
                this.command = new InteractItemCommand(this.player);
                msg = this.command.execute();
            }
        }
        return msg;
    }


    @Override
    public void undo() {
        this.command.undo();
    }
}
