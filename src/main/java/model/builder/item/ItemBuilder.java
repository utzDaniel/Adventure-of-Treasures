package model.builder.item;

import model.interfaces.IBuilderItem;

import javax.swing.*;
import java.awt.*;

abstract class ItemBuilder implements IBuilderItem {

    protected Item item;

    @Override
    public IBuilderItem name(String name) {
        this.item.setName(name);
        return this;
    }

    @Override
    public IBuilderItem description(String description) {
        this.item.setDescription(description);
        return this;
    }

    @Override
    public IBuilderItem weight(int weight) {
        this.item.setWeight(weight);
        return this;
    }

    @Override
    public IBuilderItem point(Point point) {
        this.item.setLocation(point);
        return this;
    }

    @Override
    public IBuilderItem image(String filename) {
        this.item.setImage(new ImageIcon(filename));
        return this;
    }

    @Override
    public IBuilderItem removable(boolean removable) {
        this.item.setRemovable(removable);
        return this;
    }

    @Override
    public IBuilderItem visible(boolean visible) {
        this.item.setVisible(visible);
        return this;
    }

}
