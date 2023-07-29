package backend.service.model.builder;

import backend.service.enums.TypeItem;
import backend.service.interfaces.ICoordinate;

import javax.swing.*;
import java.util.List;

public final class Item {

    private int id;
    private String name;
    private String description;
    private int weight;
    private ICoordinate coordinate;
    private ImageIcon icon;
    private List<TypeItem> listType;
    private boolean visible;

    public ICoordinate getLocation() {
        return ICoordinate.getInstance(this.coordinate);
    }

    public void setLocation(ICoordinate coordinate) {
        this.coordinate = ICoordinate.getInstance(coordinate);
    }

    public ImageIcon getIcon() {
        return this.icon;
    }

    public int getId() {
        return this.id;
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

    public boolean isType(TypeItem type) {
        return this.listType.contains(type);
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

    void setId(int id) {
        this.id = id;
    }

    void setName(String name) {
        this.name = name;
    }

    void setDescription(String description) {
        this.description = description;
    }

    void setWeight(int weight) {
        this.weight = weight;
    }

    void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    void setRemovable(List<TypeItem> listType) {
        this.listType = listType;
    }


    @Override
    public String toString() {
        return """
                {
                    "id": "%d",
                    "name": "%s",
                    "description": "%s",
                    "weight": %d,
                    "coordinate": %s,
                    "imagemIcon": "%s",
                    "listType": %s,
                    "visible": %b,
                }
                """.formatted(this.id, this.name,this.description, this.weight, this.coordinate.toString(),
                this.icon.toString(), this.listType, this.visible);
    }

    public boolean equipped() {
        return this.listType.contains(TypeItem.EQUIPABLE); //&& item.isEquipped()
    }
}
