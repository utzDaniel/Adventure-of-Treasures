package model.enums;

import model.Coordinate;

import javax.swing.*;

public enum MovePlayer {

    OESTE("oeste", new Coordinate(-MovePlayer.STEP, 0), ImagePlayer.ESQUERDA),
    NORTE("norte", new Coordinate(0, -MovePlayer.STEP), ImagePlayer.CIMA),
    LESTE("leste", new Coordinate(MovePlayer.STEP, 0), ImagePlayer.DIREITA),
    SUL("sul", new Coordinate(0, MovePlayer.STEP), ImagePlayer.BAIXO);

    private final String direction;
    public static final int STEP = 10;
    private final Coordinate coordinate;
    private final ImagePlayer imagePlayer;


    MovePlayer(String direction, Coordinate coordinate, ImagePlayer imagePlayer) {
        this.direction = direction;
        this.coordinate = coordinate;
        this.imagePlayer = imagePlayer;
    }

    public String getDirection() {
        return this.direction;
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public ImageIcon getImage() {
        return this.imagePlayer.select();
    }

    public void run() {
        this.imagePlayer.run();
    }

}
