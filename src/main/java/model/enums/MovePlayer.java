package model.enums;

import javax.swing.*;

public enum MovePlayer {

    OESTE("oeste",-MovePlayer.STEP,0, ImagePlayer.ESQUERDA),
    NORTE("norte",0,-MovePlayer.STEP,ImagePlayer.CIMA),
    LESTE("leste", MovePlayer.STEP,0, ImagePlayer.DIREITA),
    SUL("sul",0, MovePlayer.STEP, ImagePlayer.BAIXO);

    private final String direction;
    private final int toMoveX;
    private final int toMoveY;
    private final ImagePlayer imagePlayer;
    public static final int STEP = 10;

    MovePlayer(String direction, int toMoveX, int toMoveY, ImagePlayer imagePlayer) {
        this.direction = direction;
        this.toMoveX = toMoveX;
        this.toMoveY = toMoveY;
        this.imagePlayer = imagePlayer;
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
        return this.imagePlayer.select();
    }

    public void run() {
        this.imagePlayer.run();
    }

}
