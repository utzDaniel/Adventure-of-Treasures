package backend.controller.interfaces;

import java.util.List;

public interface ITakeResponse {

    IMessage message();
    String iconMap();

    String iconPlayer();

    ICoordinateDTO coordinatePlayer();

    String effect();

    List<IItemDTO> itens();

}
