package backend.service.enums;

import backend.service.interfaces.ICoordinate;

import javax.swing.*;

public enum MovePlayer {

    OESTE("oeste", ICoordinate.getInstance(-MovePlayer.STEP, 0), ImagePlayer.ESQUERDA),
    NORTE("norte", ICoordinate.getInstance(0, -MovePlayer.STEP), ImagePlayer.CIMA),
    LESTE("leste", ICoordinate.getInstance(MovePlayer.STEP, 0), ImagePlayer.DIREITA),
    SUL("sul", ICoordinate.getInstance(0, MovePlayer.STEP), ImagePlayer.BAIXO);

    private final String direction;
    public static final int STEP = 10;
    private final ICoordinate coordinate;
    private final ImagePlayer imagePlayer;


    MovePlayer(String direction, ICoordinate coordinate, ImagePlayer imagePlayer) {
        this.direction = direction;
        this.coordinate = coordinate;
        this.imagePlayer = imagePlayer;
    }

    public String getDirection() {
        return this.direction;
    }

    public ICoordinate getCoordinate() {
        return this.coordinate;
    }

    public ImageIcon getImage() {
        return this.imagePlayer.select();
    }

    public void run() {
        this.imagePlayer.run();
    }

}
