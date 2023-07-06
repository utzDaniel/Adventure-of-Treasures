package frontend.model.view;

import backend.controller.interfaces.IItemDTO;
import backend.controller.interfaces.IMessage;
import backend.controller.interfaces.IUseItemResponse;

import java.util.List;

public record UseItem(IMessage message,
                      int capacity,

                      int maxCapacity,
                      List<IItemDTO> itens,
                      int indexItem,
                      String iconMap) implements IUseItemResponse {


}
