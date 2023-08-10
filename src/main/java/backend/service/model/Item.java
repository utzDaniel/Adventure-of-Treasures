package backend.service.model;

import backend.service.enums.TypeItem;
import backend.service.interfaces.ICoordinate;

import java.util.List;

public final class Item {

    private final int id;
    private final String name;
    private final String description;
    private final int weight;
    private final String image;
    private final List<TypeItem> listType;
    private ICoordinate coordinate;

    Item(int id, String name, String description, int weight, String image, List<TypeItem> listType, ICoordinate coordinate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.image = image;
        this.listType = listType;
        this.coordinate = coordinate;
    }

    public String getImage() {
        return this.image;
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

    public ICoordinate getLocation() {
        return ICoordinate.getInstance(this.coordinate);
    }

    public void setLocation(ICoordinate coordinate) {
        this.coordinate = ICoordinate.getInstance(coordinate);
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
                    "image": "%s",
                    "listType": %s
                }
                """.formatted(this.id, this.name, this.description, this.weight, this.coordinate.toString(),
                this.image, this.listType);
    }

    public boolean equipped() {
        return this.listType.contains(TypeItem.EQUIPABLE); //&& item.isEquipped()
    }
}
