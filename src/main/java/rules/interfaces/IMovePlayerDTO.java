package rules.interfaces;

import backend.model.Coordinate;
import backend.model.dto.ItemDTO;

import java.util.List;

public interface IMovePlayerDTO {

    String getIconMap();

    String getIconPlayer();

    ICoordinate getCoordinatePlayer();

    String getSongMap();

    List<IItemDTO> getItens();

    int getIndexItens();
}
