package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.interfaces.ICommand;
import backend.service.model.Item;
import backend.service.model.MapGame;

public final class RemoveItemMapGameCommand implements ICommand {

    private final Item item;
    private final MapGame mapGame;

    public RemoveItemMapGameCommand(Item item, MapGame mapGame) {
        this.item = item;
        this.mapGame = mapGame;
    }

    @Override
    public TypeMessage execute() {
        this.mapGame.removeItem(this.item);
        return TypeMessage.REMOVE_ITEM_MAP;
    }

    @Override
    public void undo() {
        new AddItemMapGameCommand(this.item, this.mapGame).execute();
    }

}
