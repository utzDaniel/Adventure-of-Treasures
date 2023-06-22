package frontend.model.view;

import backend.controller.interfaces.ICoordinateDTO;
import backend.controller.interfaces.IItemDTO;
import backend.controller.interfaces.IMessage;
import backend.controller.interfaces.IMoveResponse;

import java.util.List;

public record Move(IMessage message,
                   String iconMap,
                   String songMap,
                   String iconPlayer,
                   ICoordinateDTO coordinatePlayer,
                   List<IItemDTO> itens,
                   int indexItens) implements IMoveResponse {

}
