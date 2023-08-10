package backend.service.mapper;

import backend.controller.interfaces.IInventoryResponse;
import backend.controller.interfaces.IItemDTO;
import backend.service.dto.response.InventoryResponse;
import backend.service.model.Inventory;

import java.util.ArrayList;
import java.util.function.Function;

public final class InventoryResponseMapper implements Function<Inventory, IInventoryResponse> {

    @Override
    public IInventoryResponse apply(Inventory inventory) {
        int capacity = inventory.getCapacity();
        int maxCapacity = inventory.getMaxCapacity();
        var itens = new ArrayList<>(inventory.getItens());
        var itensDTO = new ArrayList<IItemDTO>(itens.stream()
                .map(item -> new ItemDTOMapper().apply(item))
                .toList());
        return new InventoryResponse(capacity, maxCapacity, itensDTO);
    }
}
