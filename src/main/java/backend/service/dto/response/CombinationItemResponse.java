package backend.service.dto.response;

import backend.controller.interfaces.ICombinationItemResponse;
import backend.controller.interfaces.IItemDTO;
import backend.controller.interfaces.IMessage;

import java.util.List;

public record CombinationItemResponse(
        IMessage message,
        int capacity,
        int maxCapacity,
        List<IItemDTO> itens) implements ICombinationItemResponse {


}
