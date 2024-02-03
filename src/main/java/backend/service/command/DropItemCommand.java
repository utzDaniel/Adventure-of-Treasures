package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.interfaces.ICommand;
import backend.service.model.Item;
import backend.service.model.Player;

public final class DropItemCommand implements ICommand {

    private final Player player;
    private final Item item;
    private final CommandTool commands;

    public DropItemCommand(Item item, Player player) {
        this.player = player;
        this.item = item;
        this.commands = new CommandTool();
    }

    @Override
    public TypeMessage execute() {
        this.item.setCoordinate(this.player.getCoordinate());
        this.commands.addCommand(new RemoveItemInventoryCommand(this.item, this.player.getInventory()));
        this.commands.addCommand(new AddItemMapGameCommand(this.item, this.player.getCurrentMap()));

        var type = this.commands.execute();
        if (!type.isSucess()) return type;

        return TypeMessage.DROP_ITEM;
    }

    @Override
    public void undo() {
        this.commands.undo();
    }
}
