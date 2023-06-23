package frontend.model.view;

import backend.controller.interfaces.ICoordinateDTO;
import backend.controller.interfaces.IItemDTO;
import backend.controller.interfaces.IMessage;
import backend.controller.interfaces.ITakeResponse;

import java.util.List;

public record Take(IMessage message,
                   String iconMap,
                   String iconPlayer,
                   ICoordinateDTO coordinatePlayer,
                   List<IItemDTO> itens) implements ITakeResponse {


}
