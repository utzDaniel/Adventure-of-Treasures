package service;

import exception.MapGameException;
import model.Area;
import model.Coordinate;
import model.Player;
import model.builder.item.Item;
import model.builder.map.MapGame;

public class AddItemMapGame {

    private final Item item;
    private final Coordinate coordinate;
    private int areaTraveled = 1;
    private final MapGame mapGame;

    public AddItemMapGame(Item item,Coordinate coordinate) {
        this.item = item;
        this.coordinate = coordinate;
        this.mapGame = Player.getInstance().getCurrentMap();
    }

    public boolean run() {
        int distance = 0;
        do {
            distance++;
        } while (!searchCoordinate(distance));
        setItemNewCoordinate();
        return true;
    }

    private boolean searchCoordinate(int distance) {
        int move = moveEixo(distance);
        for (int axis = 1; axis <= 2; axis++) {
            for (int i = 1; i <= distance; i++) {
                updateCoordinate(move, axis);
                if (checkLimitPosition()) continue;
                this.areaTraveled++;
                if (checkCoordinateValid()) return true;
                if (isAreaTraveledComplete()) throw new MapGameException("Não é possivel drop esse item neste mapa!");
            }
        }
        return false;
    }

    private int moveEixo(int distance) {
        return distance % 2 != 0 ? -Area.STEP : +Area.STEP;
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
        return this.mapGame.checkLimits(this.coordinate);
    }

    private boolean isAreaTraveledComplete() {
        return this.areaTraveled >= Area.getTotalArea();
    }

    private void setItemNewCoordinate() {
        this.item.setLocation(this.coordinate);
    }
}
