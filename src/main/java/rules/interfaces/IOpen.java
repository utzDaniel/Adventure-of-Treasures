package rules.interfaces;

import java.util.List;

public interface IOpen {

    String getIconMap();

    String getIconPlayer();

    String getSongMap();
    ICoordinate getCoordinatePlayer();

    List<IItem> getItens();

}
