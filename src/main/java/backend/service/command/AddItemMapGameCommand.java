package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.CalculateCoordinate;
import backend.service.interfaces.ICommand;
import backend.service.interfaces.ICoordinate;
import backend.service.interfaces.IHandler;
import backend.service.model.Item;
import backend.service.model.MapGame;

public final class AddItemMapGameCommand implements ICommand {

    private final Item item;
    private final MapGame mapGame;
    private final IHandler<Integer> handler;

    public AddItemMapGameCommand(Item item, MapGame mapGame, IHandler<Integer> handler) {
        this.item = item;
        this.mapGame = mapGame;
        this.handler = handler;
    }

    @Override
    public TypeMessage execute() {
        var coordinate = ICoordinate.getInstance(this.item.getCoordinate());
        var areaTraveled = 0;
        do {
            CalculateCoordinate.next(coordinate);
            areaTraveled++;

            var msg = this.handler.handle(areaTraveled);
            if (msg.isPresent()) return msg.get();

        } while (this.mapGame.invalidCoordinate(coordinate));

        this.item.setCoordinate(coordinate);
        this.mapGame.addItem(this.item);
        return TypeMessage.ADD_ITEM_MAP;
    }

}
