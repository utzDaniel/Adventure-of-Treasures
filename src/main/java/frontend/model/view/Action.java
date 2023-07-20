package frontend.model.view;

import backend.controller.interfaces.ICoordinateDTO;
import backend.controller.interfaces.IItemDTO;
import backend.controller.interfaces.IMessage;
import backend.controller.interfaces.IActionResponse;

import java.util.List;

public record Action(IMessage message,
                     String iconMap,
                     String songMap,
                     String iconPlayer,
                     ICoordinateDTO coordinatePlayer,
                     List<IItemDTO> itens,
                     int indexItens) implements IActionResponse {

}
