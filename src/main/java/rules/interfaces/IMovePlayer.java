package rules.interfaces;

import java.util.List;

public interface IMovePlayer {

    String getIconMap();

    String getIconPlayer();

    ICoordinate getCoordinatePlayer();

    String getSongMap();

    List<IItem> getItens();

    int getIndexItens();
}
