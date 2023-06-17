package backend.controller.interfaces;

import java.util.List;

public interface IOpenResponse{

    IMessage message();

    String iconMap();

    String songMap();

    String iconPlayer();

    ICoordinateDTO coordinatePlayer();

    List<IItemDTO> itens();

}
