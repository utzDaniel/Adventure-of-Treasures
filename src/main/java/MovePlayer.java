import javax.swing.*;

public class MovePlayer {

    private final String direction;
    private final boolean positionX;
    private final int step;
    private final ImageIcon imageIcon;

    public MovePlayer(String direction, boolean positionX, int step, ImageIcon imageIcon){
        this.direction = direction;
        this.positionX = positionX;
        this.step = step;
        this.imageIcon = imageIcon;
    }

    public String getDirection() {
        return direction;
    }

    public boolean isPositionX() {
        return positionX;
    }

    public int getStep() {
        return step;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

}