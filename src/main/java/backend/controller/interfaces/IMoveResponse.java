package backend.controller.interfaces;

import java.util.List;

public interface IMoveResponse extends IResponse {

    String iconMap();

    String songMap();

    String iconPlayer();

    ICoordinateDTO coordinatePlayer();

    List<IItemDTO> itens();

    int indexItens();
}
