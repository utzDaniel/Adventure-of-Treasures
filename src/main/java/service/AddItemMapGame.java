package service;

import exception.MapGameException;
import model.Coordinate;
import model.Player;
import model.builder.item.Item;
import model.builder.map.MapGame;

import java.awt.*;

public class AddItemMapGame {

    public static final int STEP = 10; //TODO colocar no settings arquivo
    public final int POSITION_MINIMUM = 10; //TODO colocar no settings arquivo
    public final int POSITION_X_MAXIMUM = 56; //TODO colocar no settings arquivo
    public final int POSITION_Y_MAXIMUM = 78; //TODO colocar no settings arquivo
    private final Item item;
    private final Player player;
    private final Coordinate coordinate;
    private int areaTraveled = 1;
    private final MapGame mapGame;

    public AddItemMapGame(Item item) {
        this.item = item;
        this.player = Player.getInstance();
        this.coordinate = new Coordinate(this.player.getLocation());
        this.mapGame = player.getCurrentMap();
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
                if (!checkLimitPosition()) continue;
                areaTraveled++;
                if (checkCoordinateValid()) return true;
                if (isAreaTraveledComplete()) throw new MapGameException("Não é possivel drop esse item neste mapa!");
            }
        }
        return false;
    }

    private int moveEixo(int distance) {
        return distance % 2 != 0 ? -1 : +1;
    }

    private void updateCoordinate(int move, int axis) {
        if (axis == 1)
            coordinate.setX((coordinate.getX() + move) * STEP);
        else
            coordinate.setY((coordinate.getY() + move) * STEP);
    }

    //TODO colocar a logica dentro da coordinate
    private boolean checkLimitPosition() {
        return Math.min(coordinate.getX(), coordinate.getY()) >= POSITION_MINIMUM &&
                coordinate.getX() <= POSITION_X_MAXIMUM && coordinate.getY() <= POSITION_Y_MAXIMUM;
    }

    private boolean checkCoordinateValid() {
        return this.mapGame.checkLimits(this.coordinate);
    }

    private boolean isAreaTraveledComplete() {
        return areaTraveled >= POSITION_X_MAXIMUM * POSITION_Y_MAXIMUM;
    }

    private void setItemNewCoordinate() {
        int x = this.coordinate.getX() * STEP;
        int y = this.coordinate.getY() * STEP;
        this.item.setLocation(new Point(x, y));
    }
}
