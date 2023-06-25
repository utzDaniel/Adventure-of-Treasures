package frontend.model.view;

import backend.controller.interfaces.IDropItemResponse;
import backend.controller.interfaces.IItemDTO;
import backend.controller.interfaces.IMessage;

public record DropItem(IMessage message,
                       int capacity,

                       int maxCapacity,
                       IItemDTO item,
                       int indexItem) implements IDropItemResponse {


}
