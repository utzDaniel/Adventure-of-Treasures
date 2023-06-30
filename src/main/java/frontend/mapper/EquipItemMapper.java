package frontend.mapper;

import backend.controller.interfaces.IEquipItemResponse;
import backend.controller.interfaces.IResponse;
import frontend.model.view.EquipItem;

import java.util.function.Function;

public final class EquipItemMapper implements Function<IResponse, IEquipItemResponse> {

    @Override
    public IEquipItemResponse apply(IResponse response) {
        var equipItemResp = (IEquipItemResponse) response;
        return new EquipItem(equipItemResp.message(),
                equipItemResp.capacity(),
                equipItemResp.maxCapacity(),
                equipItemResp.item(),
                equipItemResp.indexItem());
    }
}
