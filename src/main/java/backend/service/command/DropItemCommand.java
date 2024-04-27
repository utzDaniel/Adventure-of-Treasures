package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.interfaces.ICommand;
import backend.service.model.Item;
import backend.service.model.Player;

public final class DropItemCommand implements ICommand {

    private final Player player;
    private final Item item;
    private final MacroCommand commands;

    public DropItemCommand(Item item, Player player, MacroCommand commands) {
        this.player = player;
        this.item = item;
        this.commands = commands;
    }

    @Override
    public TypeMessage execute() {
        this.item.setCoordinate(this.player.getCoordinate());

        var type = this.commands.execute();
        if (!type.isSuccess()) return type;

        return TypeMessage.DROP_ITEM;
    }

}
