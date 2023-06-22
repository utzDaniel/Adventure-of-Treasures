package frontend.mapper;

import backend.controller.interfaces.IInventoryResponse;
import backend.controller.interfaces.IResponse;
import frontend.model.view.Inventory;

import java.util.function.Function;

public final class InventoryMapper implements Function<IResponse, IInventoryResponse> {
    @Override
    public IInventoryResponse apply(IResponse response) {
        var inventoryResponse = (IInventoryResponse) response;
        return new Inventory(inventoryResponse.message(),
                inventoryResponse.isOpen(),
                inventoryResponse.capacity(),
                inventoryResponse.maxCapacity(),
                inventoryResponse.itens());
    }
}
