package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.enums.TypeItem;
import backend.service.interfaces.ICommand;
import backend.service.interfaces.ICoordinate;
import backend.service.interfaces.IUsable;
import backend.service.model.Inventory;
import backend.service.model.Item;

public final class UsableCommand implements ICommand {

    private final Item item;
    private final int idMap;
    private final ICoordinate coordinate;
    private final CommandTool commands;


    public UsableCommand(Item item, int idMap, ICoordinate coordinate, Inventory inventory) {
        this.item = item;
        this.idMap = idMap;
        this.coordinate = coordinate;
        this.commands = new CommandTool();
        this.commands.addCommand(new RemoveItemInventoryCommand(item, inventory));
    }

    @Override
    public TypeMessage execute() {
        var spec = this.item.getSpecialization(TypeItem.USABLE);
        if (spec.isEmpty()) return TypeMessage.ITEM_ERRO_USABLE;
        var usable = (IUsable) spec.get();

        if (usable.getIdMap() != this.idMap) return TypeMessage.USABLE_ERRO_MAP;
        if (!usable.getCoordinate().equals(this.coordinate)) return TypeMessage.USABLE_ERRO_COORDINATE;
        if (!usable.isEnabled()) return TypeMessage.USABLE_ERRO_ENABLE;

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
