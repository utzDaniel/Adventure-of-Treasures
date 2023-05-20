package backend.model.dto;

import backend.model.Coordinate;
import rules.interfaces.ICoordinate;
import rules.interfaces.IItemDTO;

public class ItemDTO implements IItemDTO {
    private String icon;
    private Coordinate coordinate;

    public ItemDTO() {
    }

    public ItemDTO(String icon, ICoordinate coordinate) {
        this.icon = icon;
        this.coordinate = (Coordinate)coordinate;
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
