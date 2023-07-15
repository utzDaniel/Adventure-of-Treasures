package frontend.mapper;

import backend.controller.interfaces.IInventoryResponse;
import frontend.model.view.Inventory;

import java.util.function.Function;

public final class InventoryMapper implements Function<Object, IInventoryResponse> {
    @Override
    public IInventoryResponse apply(Object response) {
        var inventoryResp = (IInventoryResponse) response;
        return new Inventory(inventoryResp.message(),
                inventoryResp.capacity(),
                inventoryResp.maxCapacity(),
                inventoryResp.itens());
    }
}
