package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.interfaces.ICommand;
import backend.service.interfaces.ICoordinate;
import backend.service.model.Item;
import backend.service.model.MapGame;
import backend.service.CalculateCoordinate;

public final class AddItemMapGameCommand implements ICommand {

    private final Item item;
    private final ICoordinate oldCoordinate;
    private final MapGame mapGame;

    public AddItemMapGameCommand(Item item, MapGame mapGame) {
        this.item = item;
        this.oldCoordinate = item.getCoordinate();
        this.mapGame = mapGame;
    }

    @Override
    public TypeMessage execute() {
        var coordinate = ICoordinate.getInstance(this.oldCoordinate);
        var areaTraveled = 0;

        do {
            CalculateCoordinate.next(coordinate);
            areaTraveled++;
            if (areaTraveled >= this.mapGame.areaSize())
                return TypeMessage.MAP_ADD_ERROR_FULL;
        } while (this.mapGame.invalidCoordinate(coordinate));

        this.item.setCoordinate(coordinate);
        this.mapGame.addItem(item);
        return TypeMessage.ADD_ITEM_MAP;
    }

    @Override
    public void undo() {
        this.mapGame.removeItem(this.item);
        this.item.setCoordinate(this.oldCoordinate);
    }

}
