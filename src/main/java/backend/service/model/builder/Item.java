package backend.service.model.builder;

import backend.service.interfaces.ICoordinate;

import javax.swing.*;

public abstract class Item {

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
        return """
                {
                    "name": "%s",
                    "description": "%s",
                    "weight": %d,
                    "coordinate": %s,
                    "imagemIcon": "%s",
                    "remove": %b,
                    "visible": %b,
                }
                """.formatted(this.name, this.description, this.weight, this.coordinate.toString(),
                this.icon.toString(), this.removable, this.visible);
    }

    public boolean equipped() {
        return this instanceof ItemEquipable item && item.isEquipped();
    }
}
