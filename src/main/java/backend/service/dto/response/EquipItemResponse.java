package backend.service.dto.response;

import backend.controller.interfaces.IEquipItemResponse;
import backend.controller.interfaces.IItemDTO;
import backend.controller.interfaces.IMessage;

import java.util.List;

public record EquipItemResponse(
        IMessage message,
        int capacity,

        int maxCapacity,
        List<IItemDTO> itens) implements IEquipItemResponse {


}
