package backend.service.dto.response;

import backend.controller.interfaces.IEquipItemResponse;
import backend.controller.interfaces.IItemDTO;
import backend.controller.interfaces.IMessage;

public record EquipItemResponse(
        IMessage message,
        int capacity,

        int maxCapacity,
        IItemDTO item,
        int indexItem) implements IEquipItemResponse {


}
