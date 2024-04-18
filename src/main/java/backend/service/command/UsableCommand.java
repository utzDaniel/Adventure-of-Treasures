package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.interfaces.ICommand;
import backend.service.interfaces.IHandler;
import backend.service.model.Inventory;
import backend.service.model.Item;

public final class UsableCommand implements ICommand {

    private final Item item;
    private final IHandler<Item> usableHandler;
    private final CommandTool commands;

    public UsableCommand(Item item, Inventory inventory, IHandler<Item> usableHandler) {
        this.item = item;
        this.usableHandler = usableHandler;
        this.commands = new CommandTool();
        this.commands.addCommand(new RemoveItemInventoryCommand(item, inventory));
    }

    @Override
    public TypeMessage execute() {
        var msg = this.usableHandler.handle(this.item);
        if (msg.isPresent()) return msg.get();

        var type = this.commands.execute();
        if (!type.isSuccess()) return type;

        return getEventTypeMessage();
    }

    @Override
    public void undo() {
        this.commands.undo();
    }

    private TypeMessage getEventTypeMessage() {
        return switch (this.item.getId()) {
            case 1 -> TypeMessage.USABLE_KEY;
            case 2 -> TypeMessage.USABLE_LADDER;
            case 11 -> TypeMessage.USABLE_SHOVEL;
            default -> TypeMessage.USABLE;
        };
    }

}
