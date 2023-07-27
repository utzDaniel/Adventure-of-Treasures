package frontend.mapper;

import backend.controller.interfaces.IInventoryResponse;
import backend.controller.interfaces.IResponse;
import frontend.model.view.Inventory;

import java.util.function.Function;

public final class InventoryMapper implements Function<Object, IInventoryResponse> {
    @Override
    public IInventoryResponse apply(Object response) {
        var resp = (IResponse) response;
        var inventory = (IInventoryResponse) resp.obj();
        return new Inventory(inventory.capacity(),
                inventory.maxCapacity(),
                inventory.itens());
    }
}
