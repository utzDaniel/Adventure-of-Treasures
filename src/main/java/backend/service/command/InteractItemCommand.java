package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.interfaces.ICommand;
import backend.service.model.Item;
import backend.service.model.Player;

public final class InteractItemCommand implements ICommand {

    private final Item item;
    private final Player player;
    private final MacroCommand commands;

    public InteractItemCommand(Item item, Player player) {
        this.item = item;
        this.player = player;
        this.commands = new MacroCommand();
    }

    @Override
    public TypeMessage execute() {
        var mapGame = this.player.getCurrentMap();
        var inventory = this.player.getInventory();
        this.commands.add(CommandFactory.createAddItemInventoryCommand(inventory, this.item));
        this.commands.add(CommandFactory.createRemoveItemMapGameCommand(mapGame, this.item));

        var type = this.commands.execute();
        if (!type.isSuccess()) return type;

        return TypeMessage.TAKE_ITEM;
    }

}
