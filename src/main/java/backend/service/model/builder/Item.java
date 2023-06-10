package backend.service.model.builder;

import frontend.interfaces.IAction;
import backend.controller.interfaces.ICoordinate;
import backend.service.interfaces.IItem;

import javax.swing.*;

public abstract class Item implements IAction {

    private String name;
    private String description;
    private int weight;
    private ICoordinate coordinate;
    private ImageIcon icon;
    private boolean removable;
    private boolean visible;

    protected Item() {
    }

    public ICoordinate getLocation() {
        return ICoordinate.getInstance(this.coordinate);
    }

    public void setLocation(ICoordinate coordinate) {
        this.coordinate = ICoordinate.getInstance(coordinate);
    }

    public ImageIcon getIcon() {
        return this.icon;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getWeight() {
        return this.weight;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public boolean isInvisible() {
        return !this.visible;
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

    protected void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    protected void setRemovable(boolean removable) {
        this.removable = removable;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + this.name +
                ", description='" + this.description +
                ", weight=" + this.weight +
                ", X=" + this.coordinate.getX() +
                ", Y=" + this.coordinate.getY() +
                ", image=" + this.icon +
                ", removable=" + this.removable +
                ", visible=" + this.visible +
                '}';
    }

    public boolean equipped() {
        return this instanceof ItemEquipable item && item.isEquipped();
    }
}
