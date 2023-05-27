package frontend.model.vo;

import rules.interfaces.ICoordinate;
import rules.interfaces.IItem;

public class ItemVO implements IItem {
    private String icon;
    private CoordinateVO coordinate;

    public ItemVO() {
    }

    public ItemVO(String icon, ICoordinate coordinate) {
        this.icon = icon;
        this.coordinate = new CoordinateVO(coordinate.getX(),coordinate.getY());
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
