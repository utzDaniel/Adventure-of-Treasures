package frontend.model.view;

import backend.controller.interfaces.ICombinationItemResponse;
import backend.controller.interfaces.IItemDTO;
import backend.controller.interfaces.IMessage;

import java.util.List;

public record CombinationItem(IMessage message,
                              int capacity,

                              int maxCapacity,
                              List<IItemDTO> itens,
                              int indexItem,

                              String iconMap) implements ICombinationItemResponse {


}
