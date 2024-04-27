package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.interfaces.ICommand;
import backend.service.interfaces.IHandler;
import backend.service.model.Item;
import backend.service.model.Player;

public final class DropItemCommand implements ICommand {

    private final Player player;
    private final Item item;
    private final IHandler<Item> handler;
    private final MacroCommand commands;

    public DropItemCommand(Item item, Player player, IHandler<Item> handler, MacroCommand commands) {
        this.player = player;
        this.item = item;
        this.handler = handler;
        this.commands = commands;
    }

    @Override
    public TypeMessage execute() {
        var msg = this.handler.handle(this.item);
        if (msg.isPresent()) return msg.get();

        this.item.setCoordinate(this.player.getCoordinate());

        var type = this.commands.execute();
        if (!type.isSuccess()) return type;

        return TypeMessage.DROP_ITEM;
    }

}
