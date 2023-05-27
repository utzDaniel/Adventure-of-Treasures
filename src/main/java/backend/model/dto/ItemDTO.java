package backend.model.dto;

import rules.interfaces.ICoordinate;
import rules.interfaces.IItem;

public class ItemDTO implements IItem {
    private String icon;
    private CoordinateDTO coordinate;

    public ItemDTO() {
    }

    public ItemDTO(String icon, ICoordinate coordinate) {
        this.icon = icon;
        this.coordinate = new CoordinateDTO(coordinate.getX(),coordinate.getY());
    }

    @Override
    public String getIcon() {
        return this.icon;
    }

    @Override
    public ICoordinate getCoordinate() {
        return this.coordinate;
    }


}
