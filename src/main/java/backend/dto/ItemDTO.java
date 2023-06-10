package backend.dto;

import backend.controller.interfaces.ICoordinate;
import backend.controller.interfaces.IItem;

public class ItemDTO implements IItem {
    private String icon;
    private CoordinateDTO coordinate;
    private String name;
    private String description;
    private String effect;
    private int weight;
    private String specialization;
    private boolean equipped;

    public ItemDTO() {
    }

    public ItemDTO(String icon, ICoordinate coordinate, String name, String description, String effect, int weight, String specialization, boolean equipped) {
        this.icon = icon;
        this.coordinate = new CoordinateDTO(coordinate.getX(), coordinate.getY());
        this.name = name;
        this.description = description;
        this.effect = effect;
        this.weight = weight;
        this.specialization = specialization;
        this.equipped = equipped;
    }

    @Override
    public String getIcon() {
        return this.icon;
    }

    @Override
    public ICoordinate getCoordinate() {
        return this.coordinate;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getEffect() {
        return this.effect;
    }

    @Override
    public int getWeight() {
        return this.weight;
    }

    @Override
    public String getSpecialization() {
        return this.specialization;
    }

    @Override
    public boolean isEquipped() {
        return this.equipped;
    }
}
