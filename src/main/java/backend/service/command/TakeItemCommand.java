package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.enums.Direction;
import backend.service.interfaces.ICommand;
import backend.service.model.Player;
import backend.service.util.CalculateCoordinate;

import java.util.Objects;

public final class TakeItemCommand implements ICommand {

    private final Player player;
    private final CommandTool commands;

    public TakeItemCommand(Player player) {
        this.player = player;
        this.commands = new CommandTool();
    }

    @Override
    public TypeMessage execute() {

        var direction = Direction.getInstance(this.player.getMove().name());
        var coordinate = CalculateCoordinate.next(this.player.getCoordinate(), direction);

        var mapGame = this.player.getCurrentMap();
        var item = mapGame.getItem(coordinate);

        var inventory = this.player.getInventory();

        if (Objects.isNull(item)) return TypeMessage.ITEM_ERRO_FOUND;

        this.commands.addCommand(new AddItemInventoryCommand(item, inventory));
        this.commands.addCommand(new RemoveItemMapGameCommand(item, mapGame));

        var type = this.commands.execute();
        if (!type.isSucess()) return type;

        return TypeMessage.TAKE_ITEM;
    }

    @Override
    public void undo() {
        this.commands.undo();
    }

}
