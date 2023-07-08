package frontend.mapper;

import backend.controller.interfaces.IEquipItemResponse;
import frontend.model.view.EquipItem;

import java.util.function.Function;

public final class EquipItemMapper implements Function<Object, IEquipItemResponse> {

    @Override
    public IEquipItemResponse apply(Object response) {
        var equipItemResp = (IEquipItemResponse) response;
        return new EquipItem(equipItemResp.message(),
                equipItemResp.capacity(),
                equipItemResp.maxCapacity(),
                equipItemResp.itens());
    }
}
