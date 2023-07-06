package backend.service.dto.response;

import backend.controller.interfaces.IInventoryQuitResponse;
import backend.controller.interfaces.IItemDTO;
import backend.controller.interfaces.IMessage;

import java.util.List;

public record InventoryQuitResponse(
        IMessage message,
        String iconMap,
        List<IItemDTO> itens,
        int indexItens) implements IInventoryQuitResponse {


}
