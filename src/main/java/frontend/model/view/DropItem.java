package frontend.model.view;

import backend.controller.interfaces.IDropItemResponse;
import backend.controller.interfaces.IItemDTO;
import backend.controller.interfaces.IMessage;

import java.util.List;

public record DropItem(IMessage message,
                       int capacity,

                       int maxCapacity,
                       List<IItemDTO> itens,
                       int indexItem) implements IDropItemResponse {


}
