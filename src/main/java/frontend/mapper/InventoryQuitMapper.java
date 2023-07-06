package frontend.mapper;

import backend.controller.interfaces.IInventoryQuitResponse;
import backend.controller.interfaces.IResponse;
import frontend.model.view.InventoryQuit;

import java.util.function.Function;

public final class InventoryQuitMapper implements Function<IResponse, IInventoryQuitResponse> {
    @Override
    public IInventoryQuitResponse apply(IResponse response) {
        var inventoryQuitResp = (IInventoryQuitResponse) response;
        return new InventoryQuit(inventoryQuitResp.message(),
                inventoryQuitResp.iconMap(),
                inventoryQuitResp.itens(),
                inventoryQuitResp.indexItens());
    }
}
