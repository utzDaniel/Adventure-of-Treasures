package backend.service.dto.response;

import backend.controller.interfaces.IItemDTO;
import backend.controller.interfaces.IMessage;
import backend.controller.interfaces.IUseItemResponse;

import java.util.List;

public record UseItemResponse(
        IMessage message,
        int capacity,

        int maxCapacity,
        List<IItemDTO> itens) implements IUseItemResponse {


}
