package frontend.model.vo;

import backend.service.interfaces.ICoordinate;
import backend.controller.interfaces.IItemDTO;

public class ItemVO implements IItemDTO {
    private String icon;
    private CoordinateVO coordinate;
    private String name;
    private String description;
    private String effect;
    private int weight;
    private String specialization;
    private boolean equipped;

    public ItemVO() {
    }

    public ItemVO(String icon, ICoordinate coordinate, String name, String description, String effect, int weight, String specialization,  boolean equipped) {
        this.icon = icon;
        this.coordinate = new CoordinateVO(coordinate.x(), coordinate.y());
        this.name = name;
        this.description = description;
        this.effect = effect;
        this.weight = weight;
        this.specialization = specialization;
        this.equipped = equipped;
    }

    @Override
    public String icon() {
        return this.icon;
    }

    @Override
    public ICoordinate coordinate() {
        return this.coordinate;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public String description() {
        return this.description;
    }

    @Override
    public String effect() {
        return this.effect;
    }

    @Override
    public int weight() {
        return this.weight;
    }

    @Override
    public String specialization() {
        return this.specialization;
    }

    @Override
    public boolean isEquipped() {
        return this.equipped;
    }

}
