package backend.service.model.builder;

import backend.service.enums.TypeItem;
import backend.service.interfaces.IBuilderItem;
import backend.service.interfaces.ICoordinate;

import javax.swing.*;
import java.util.List;

public final class ItemBuilder implements IBuilderItem {

    private final Item item;

    private ItemBuilder() {
        this.item = new Item();
    }
    public static ItemBuilder builder() {
        return new ItemBuilder();
    }
    @Override
    public IBuilderItem id(int id) {
        this.item.setId(id);
        return this;
    }

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
    public IBuilderItem coordinate(ICoordinate coordinate) {
        this.item.setLocation(coordinate);
        return this;
    }

    @Override
    public IBuilderItem image(String filename) {
        this.item.setIcon(new ImageIcon(filename));
        return this;
    }

    @Override
    public IBuilderItem type(List<TypeItem> listType) {
        this.item.setRemovable(listType);
        return this;
    }

    @Override
    public IBuilderItem visible(boolean visible) {
        this.item.setVisible(visible);
        return this;
    }

    @Override
    public Item build() {
        return this.item;
    }

}
