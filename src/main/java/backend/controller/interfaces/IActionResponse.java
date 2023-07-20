package backend.controller.interfaces;

import java.util.List;

public interface IActionResponse extends IResponse {

    String iconMap();

    String songMap();

    String iconPlayer();

    ICoordinateDTO coordinatePlayer();

    List<IItemDTO> itens();

    int indexItens();
}
