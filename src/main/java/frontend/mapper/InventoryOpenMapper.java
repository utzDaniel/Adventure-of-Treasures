package frontend.mapper;

import backend.controller.interfaces.IInventoryOpenResponse;
import backend.controller.interfaces.IResponse;
import frontend.model.view.InventoryOpen;

import java.util.function.Function;

public final class InventoryOpenMapper implements Function<IResponse, IInventoryOpenResponse> {
    @Override
    public IInventoryOpenResponse apply(IResponse response) {
        var inventoryOpenResp = (IInventoryOpenResponse) response;
        return new InventoryOpen(inventoryOpenResp.message(),
                inventoryOpenResp.capacity(),
                inventoryOpenResp.maxCapacity(),
                inventoryOpenResp.itens());
    }
}
