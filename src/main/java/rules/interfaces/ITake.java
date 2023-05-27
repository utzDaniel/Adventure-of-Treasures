package rules.interfaces;

import java.util.List;

public interface ITake {

    String getIconMap();

    String getIconPlayer();

    String getEffects();
    ICoordinate getCoordinatePlayer();

    List<IItem> getItens();

}
