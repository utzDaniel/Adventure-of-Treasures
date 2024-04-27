package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.interfaces.ICommand;
import backend.service.interfaces.IHandler;
import backend.service.model.Item;
import backend.service.model.Player;

public final class InteractItemCommand implements ICommand {

    private final Player player;
    private final IHandler<Item> handler;
    private final MacroCommand commands;

    public InteractItemCommand(Player player, IHandler<Item> handler) {
        this.player = player;
        this.handler = handler;
        this.commands = new MacroCommand();
    }

    @Override
    public TypeMessage execute() {
        var coordinate = this.player.getCoordinate();
        coordinate.move(this.player.getDirection().getMove());

        var mapGame = this.player.getCurrentMap();
        var item = mapGame.getItem(coordinate);

        var msg = this.handler.handle(item);
        if (msg.isPresent()) return msg.get();

        var inventory = this.player.getInventory();
        this.commands.add(CommandFactory.createAddItemInventoryCommand(inventory, item));
        this.commands.add(CommandFactory.createRemoveItemMapGameCommand(mapGame, item));

        var type = this.commands.execute();
        if (!type.isSuccess()) return type;

        return TypeMessage.TAKE_ITEM;
    }

}
