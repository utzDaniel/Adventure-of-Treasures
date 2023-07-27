package frontend.model.view;

import backend.controller.interfaces.IInventoryResponse;
import backend.controller.interfaces.IItemDTO;

import java.util.List;

public record Inventory(int capacity,
                        int maxCapacity,
                        List<IItemDTO> itens) implements IInventoryResponse {
}
