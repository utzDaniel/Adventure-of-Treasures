package model;

public final class Door {

    private final int positionInsideX;
    private final int positionInsideY;
    private final int positionOutsideX;
    private final int positionOutsideY;
    private boolean open;

    public Door(int positionInsideX, int positionInsideY, int positionOutsideX, int positionOutsideY, boolean open){
        this.positionInsideX = positionInsideX;
        this.positionInsideY = positionInsideY;
        this.positionOutsideX = positionOutsideX;
        this.positionOutsideY = positionOutsideY;
        this.open = open;
    }

    public int getPositionInsideX() {
        return positionInsideX;
    }

    public int getPositionInsideY() {
        return positionInsideY;
    }

    public int getPositionOutsideX() {
        return positionOutsideX;
    }

    public int getPositionOutsideY() {
        return positionOutsideY;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isOpen() {
        return open;
    }

}
