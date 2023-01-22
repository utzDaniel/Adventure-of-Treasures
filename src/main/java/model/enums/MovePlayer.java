package model.enums;

import repository.ImagePlayer;

import javax.swing.*;

public enum MovePlayer {

    OESTE("oeste",-MovePlayer.STEP,0, ImagePlayer.ESQUERDA.select()),
    NORTE("norte",0,-MovePlayer.STEP,ImagePlayer.CIMA.select()),
    LESTE("leste", MovePlayer.STEP,0, ImagePlayer.DIREITA.select()),
    SUL("sul",0, MovePlayer.STEP, ImagePlayer.BAIXO.select());

    private final String direction;
    private final int toMoveX;
    private final int toMoveY;
    private final ImageIcon imageIcon;
    public static final int STEP = 10;

    MovePlayer(String direction, int toMoveX, int toMoveY, ImageIcon imageIcon) {
        this.direction = direction;
        this.toMoveX = toMoveX;
        this.toMoveY = toMoveY;
        this.imageIcon = imageIcon;
    }

    public String getDirection() {
        return this.direction;
    }

    public int getToMoveX() {
        return this.toMoveX;
    }

    public int getToMoveY() {
        return this.toMoveY;
    }

    public ImageIcon getImageIcon() {
        return this.imageIcon;
    }
}
