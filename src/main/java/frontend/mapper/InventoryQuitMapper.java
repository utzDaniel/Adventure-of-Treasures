package frontend.mapper;

import backend.controller.interfaces.IInventoryQuitResponse;
import frontend.model.view.InventoryQuit;

import java.util.function.Function;

public final class InventoryQuitMapper implements Function<Object, IInventoryQuitResponse> {
    @Override
    public IInventoryQuitResponse apply(Object response) {
        var inventoryQuitResp = (IInventoryQuitResponse) response;
        return new InventoryQuit(inventoryQuitResp.message(),
                inventoryQuitResp.iconMap(),
                inventoryQuitResp.itens(),
                inventoryQuitResp.indexItens());
    }
}
