package frontend.model.view;

import backend.controller.interfaces.IInventoryResponse;
import backend.controller.interfaces.IItemDTO;
import backend.controller.interfaces.IMessage;

import java.util.List;

public record Inventory(IMessage message,
                        boolean isOpen,
                        int capacity,
                        int maxCapacity,
                        List<IItemDTO> itens) implements IInventoryResponse {
}
