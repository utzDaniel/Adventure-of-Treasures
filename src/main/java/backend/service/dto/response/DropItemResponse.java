package backend.service.dto.response;

import backend.controller.interfaces.IDropItemResponse;
import backend.controller.interfaces.IItemDTO;
import backend.controller.interfaces.IMessage;

public record DropItemResponse(
        IMessage message,
        int capacity,

        int maxCapacity,
        IItemDTO item,
        int indexItem) implements IDropItemResponse {


}
