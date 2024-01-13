package backend.service.component.take;

import backend.controller.enums.TypeMessage;
import backend.service.enums.Move;
import backend.service.interfaces.ICoordinate;
import backend.service.model.Player;
import backend.service.model.Item;

import java.util.Objects;

public final class Take {

    private final Player player;

    public Take(Player player) {
        this.player = player;
    }

    public TypeMessage run() {

        var direction = this.player.getMove();

        var coordinate = newCoordinate(direction);

        var item = getItem(coordinate);

        var inventory = this.player.getInventory();

        if (Objects.isNull(item))
            return TypeMessage.ITEM_NOT_FOUND;

        if (!inventory.hasSpace(item.getWeight()))
            return TypeMessage.INVENTORY_FULL;

        inventory.addItem(item);
        var mapGame = this.player.getCurrentMap();
        mapGame.removeItem(item);

        return TypeMessage.TAKE_SUCESS_ITEM;
    }

    private ICoordinate newCoordinate(Move move) {
        var coordinate = this.player.getCoordinate();
        return move.coordinateByScenery(coordinate);
    }

    private Item getItem(ICoordinate coordinate) {
        var mapGame = player.getCurrentMap();
        return mapGame.getItem(coordinate);
    }
}
