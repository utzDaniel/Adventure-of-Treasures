package backend.service.component.drop;

import backend.controller.enums.TypeMessage;
import backend.service.enums.Move;
import backend.service.interfaces.ICoordinate;
import backend.service.model.Area;
import backend.service.model.Item;
import backend.service.model.MapGame;

public class AddItemMapGame {

    private final Item item;
    private final ICoordinate coordinate;
    private int areaTraveled = 1;
    private final MapGame mapGame;

    public AddItemMapGame(MapGame mapGame, Item item, ICoordinate coordinate) {
        this.item = item;
        this.coordinate = coordinate;
        this.mapGame = mapGame;
    }

    public TypeMessage run() {
        int distance = 0;
        do {
            distance++;
        } while (!searchCoordinate(distance));
        setItemNewCoordinate();
        if (isAreaTraveledComplete()) return TypeMessage.MAP_FULL_ITEM;
        return TypeMessage.DROP_SUCESS;
    }

    private boolean searchCoordinate(int distance) {
        int move = moveEixo(distance);
        for (int axis = 1; axis <= 2; axis++) {
            for (int i = 1; i <= distance; i++) {
                updateCoordinate(move, axis);
                if (checkLimitPosition()) continue;
                this.areaTraveled++;
                if (checkCoordinateValid()) return true;
                if (isAreaTraveledComplete()) return false;
            }
        }
        return false;
    }

    private int moveEixo(int distance) {
        return distance % 2 != 0 ? -Move.STEP : +Move.STEP;
    }

    private void updateCoordinate(int move, int axis) {
        if (axis == 1)
            this.coordinate.updateX(move);
        else
            this.coordinate.updateY(move);
    }

    private boolean checkLimitPosition() {
        return Area.isLimit(this.coordinate);
    }

    private boolean checkCoordinateValid() {
        var area = this.mapGame.getArea();
        return area.isBlock(this.coordinate);
    }

    private boolean isAreaTraveledComplete() {
        return this.areaTraveled >= Area.getTotalArea();
    }

    private void setItemNewCoordinate() {
        this.item.setLocation(this.coordinate);
    }
}
