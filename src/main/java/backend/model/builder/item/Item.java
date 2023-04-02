package backend.model.builder.item;

import frontend.interfaces.IAction;
import frontend.settings.SettingsItem;
import rules.interfaces.ICoordinate;

import javax.swing.*;

public abstract class Item implements IAction {

    private String name;
    private String description;
    private int weight;
    private final JLabel jLabel;
    private boolean removable;
    private boolean visible;

    protected Item() {
        this.jLabel = new JLabel();
        settingsItem();
    }

    //TODO remover o JLabel, é resposabilidade da view

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

    public ICoordinate getLocation() {
        return ICoordinate.getInstance(this.jLabel.getLocation());
    }

    public void setLocation(ICoordinate coordinate) {
        this.jLabel.setLocation(coordinate.getPoint());
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
                "name='" + this.name +
                ", description='" + this.description +
                ", weight=" + this.weight +
                ", X=" + this.jLabel.getLocation().x +
                ", Y=" + this.jLabel.getLocation().y +
                ", image=" + this.jLabel.getIcon() +
                ", removable=" + this.removable +
                ", visible=" + this.visible +
                '}';
    }
}
