package frontend.model.view;

import backend.controller.interfaces.IItemDTO;
import backend.controller.interfaces.IMessage;
import backend.controller.interfaces.IUseItemResponse;

public record UseItem(IMessage message,
                      int capacity,

                      int maxCapacity,
                      IItemDTO item,
                      int indexItem,
                      String iconMap) implements IUseItemResponse {


}
