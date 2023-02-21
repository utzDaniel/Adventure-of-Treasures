package model.builder.item;

import model.Coordinate;
import model.interfaces.IAction;
import settings.SettingsItem;

import javax.swing.*;

public abstract class Item implements IAction {

    private String name;
    private String description;
    private int weight;
    private final JLabel jLabel;
    private boolean removable;
    private boolean visible;

    private Coordinate coordinate;

    protected Item() {
        this.jLabel = new JLabel();
        settingsItem();
        this.coordinate = new Coordinate(this.jLabel.getLocation());
    }

    private void settingsItem() {
        SettingsItem settingsItem = new SettingsItem();
        this.jLabel.setIcon(settingsItem.getIcon());
        this.jLabel.setName(settingsItem.getName());
        this.jLabel.setBounds(settingsItem.getRectangle());
        this.jLabel.setVerticalAlignment(SwingConstants.BOTTOM);
    }

    public JLabel getJLabel() {
        return this.jLabel;
    }

    public Coordinate getLocation() {
        return this.coordinate;
    }

    public void setLocation(Coordinate coordinate) {
        this.coordinate.setLocation(coordinate);
        this.jLabel.setLocation(this.coordinate.getPoint());
    }

    public Icon getImage() {
        return this.jLabel.getIcon();
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

    protected void setImage(ImageIcon image) {
        this.jLabel.setIcon(image);
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
                ", positionX=" + this.jLabel.getLocation().x +
                ", positionY=" + this.jLabel.getLocation().y +
                ", image=" + this.jLabel.getIcon() +
                ", removable=" + removable +
                ", visible=" + visible +
                '}';
    }
}
