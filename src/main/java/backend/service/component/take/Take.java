package backend.service.component.take;

import backend.controller.enums.TypeMessage;
import backend.service.enums.Move;
import backend.service.interfaces.ICoordinate;
import backend.service.model.Player;
import backend.service.model.builder.Item;

import java.util.Locale;
import java.util.Objects;

public final class Take {

    private final Player player;

    public Take(Player player) {
        this.player = player;
    }

    public TypeMessage run() {

        var direction = this.player.getDirection();
        var move = getMove(direction);

        var coordinate = createCoordinate(move);

        var item = getItem(coordinate);

        var inventory = this.player.getInventory();

        if (Objects.isNull(item))
            return TypeMessage.ITEM_NOT_FOUND;

        var newCapacity = item.getWeight() + inventory.getCapacity();
        if (newCapacity > inventory.getMaxCapacity())
            return TypeMessage.INVENTORY_FULL;

        inventory.addItem(item);
        var mapGame = this.player.getCurrentMap();
        mapGame.removeItem(item);

        return TypeMessage.TAKE_SUCESS_ITEM;
    }

    private Move getMove(String direction) {
        return Enum.valueOf(Move.class, direction.toUpperCase(Locale.ROOT));
    }

    private ICoordinate createCoordinate(Move move) {
        var coordinate = this.player.getLocation();
        coordinate.move(move.getCoordinate());
        return coordinate;
    }

    private Item getItem(ICoordinate coordinate) {
        var mapGame = player.getCurrentMap();
        return mapGame.getItem(coordinate);
    }
}
