package backend.service.dto.response;

import backend.controller.interfaces.IInventoryResponse;
import backend.controller.interfaces.IItemDTO;
import backend.controller.interfaces.IMessage;

import java.util.List;

public record InventoryResponse(
        IMessage message,
        int capacity,
        int maxCapacity,
        List<IItemDTO> itens) implements IInventoryResponse {


}