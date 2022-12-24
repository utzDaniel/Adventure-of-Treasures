package model.enums;

import repository.CreateImagePlayer;

import javax.swing.*;

public enum MovePlayer {

    OESTE("oeste",-MovePlayer.STEP,0, new CreateImagePlayer().selectImage(Direction.OESTE.getLabel())),
    NORTE("norte",0,-MovePlayer.STEP, new CreateImagePlayer().selectImage(Direction.NORTE.getLabel())),
    LESTE("leste", MovePlayer.STEP,0, new CreateImagePlayer().selectImage(Direction.LESTE.getLabel())),
    SUL("sul",0, MovePlayer.STEP, new CreateImagePlayer().selectImage(Direction.SUL.getLabel()));

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
