package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.interfaces.ICommand;
import backend.service.model.Item;

public final class UsableCommand implements ICommand {

    private final Item item;
    private final MacroCommand commands;

    public UsableCommand(Item item, MacroCommand commands) {
        this.item = item;
        this.commands = commands;
    }

    @Override
    public TypeMessage execute() {
        var type = this.commands.execute();
        if (!type.isSuccess()) return type;

        this.item.warn();

        return getEventTypeMessage();
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
