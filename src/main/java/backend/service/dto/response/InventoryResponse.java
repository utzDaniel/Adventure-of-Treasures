package backend.service.dto.response;

import backend.controller.interfaces.IInventoryResponse;
import backend.controller.interfaces.IItemDTO;

import java.util.List;

public record InventoryResponse(int capacity,
        int maxCapacity,
        List<IItemDTO> itens) implements IInventoryResponse {


}
