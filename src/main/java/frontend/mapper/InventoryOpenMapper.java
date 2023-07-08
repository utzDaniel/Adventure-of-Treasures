package frontend.mapper;

import backend.controller.interfaces.IInventoryOpenResponse;
import frontend.model.view.InventoryOpen;

import java.util.function.Function;

public final class InventoryOpenMapper implements Function<Object, IInventoryOpenResponse> {
    @Override
    public IInventoryOpenResponse apply(Object response) {
        var inventoryOpenResp = (IInventoryOpenResponse) response;
        return new InventoryOpen(inventoryOpenResp.message(),
                inventoryOpenResp.capacity(),
                inventoryOpenResp.maxCapacity(),
                inventoryOpenResp.itens());
    }
}
