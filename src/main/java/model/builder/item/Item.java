package model.builder.item;

import model.interfaces.IAction;

import javax.swing.*;

public abstract class Item implements IAction {

    private String name;
    private String description;
    private int weight;
    private int positionX;
    private int positionY;
    private ImageIcon image;
    private boolean removable;
    private boolean visible;

    protected Item() {}

    //TODO colocar no JLabel?
    public int getPositionX() {
        return this.positionX;
    }

    public int getPositionY() {
        return this.positionY;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }// TODO JLabel atualizar automaticamente com referencia

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }// TODO JLabel atualizar automaticamente com referencia

    public ImageIcon getImage() {
        return this.image;
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

    protected void setName(String name) {
        this.name = name;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    protected void setWeight(int weight) {
        this.weight = weight;
    }

    protected void setImage(ImageIcon image) {
        this.image = image;
    }

    protected void setRemovable(boolean removable) {
        this.removable = removable;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", weight=" + weight +
                ", positionX=" + positionX +
                ", positionY=" + positionY +
                ", image=" + image +
                ", removable=" + removable +
                ", visible=" + visible +
                '}';
    }
}
