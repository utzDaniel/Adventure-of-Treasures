package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.interfaces.ICommand;
import backend.service.interfaces.IHandler;
import backend.service.interfaces.IMove;
import backend.service.model.Door;
import backend.service.model.Item;
import backend.service.model.NPC;
import backend.service.model.Player;

public final class InteractCommand implements ICommand {

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

        var interact = this.player.getCurrentMap()
                .getInteract(this.player.getCoordinate(), this.player.getDirection()).orElse(null);

        if (interact instanceof NPC npc) {
            return CommandFactory.createInteractCommand(npc, this.player).execute();
        } else if (interact instanceof Door door) {
            return CommandFactory.createInteractCommand(door, this.player).execute();
        } else if (interact instanceof Item item) {
            return CommandFactory.createInteractCommand(item, this.player).execute();
        } else
            return TypeMessage.INTERACT_ERROR;
    }

}
