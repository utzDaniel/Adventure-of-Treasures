package frontend.model.view;

import backend.controller.interfaces.IInventoryOpenResponse;
import backend.controller.interfaces.IItemDTO;
import backend.controller.interfaces.IMessage;

import java.util.List;

public record InventoryOpen(IMessage message,
                            int capacity,
                            int maxCapacity,
                            List<IItemDTO> itens) implements IInventoryOpenResponse {
}
