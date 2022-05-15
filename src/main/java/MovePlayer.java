import javax.swing.*;

public record MovePlayer(String direction, boolean positionX, int step, ImageIcon imageIcon) {

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