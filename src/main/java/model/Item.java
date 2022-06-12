package model;

import javax.swing.*;

public abstract class Item {
    protected final String name;
    protected final String description;
    protected final int weight;
    protected int positionItemX;
    protected int positionItemY;
    protected final ImageIcon imagemIcon;
    protected boolean remove;
    protected boolean visible;

    public Item(String name, String description, int weight, int positionItemX, int positionItemY, ImageIcon imagemIcon) {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.positionItemX = positionItemX;
        this.positionItemY = positionItemY;
        this.imagemIcon = imagemIcon;
        this.remove = true;
        this.visible = true;
    }

    public ImageIcon getImagemIcon() {
        return imagemIcon;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {return description;}

    public int getWeight() {
        return this.weight;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getPositionItemX() {
        return positionItemX;
    }

    public void setPositionItemX(int positionItemX) {
        this.positionItemX = positionItemX;
    }

    public int getPositionItemY() {
        return positionItemY;
    }

    public void setPositionItemY(int positionItemY) {
        this.positionItemY = positionItemY;
    }
}
