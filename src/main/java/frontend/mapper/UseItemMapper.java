package frontend.mapper;

import backend.controller.interfaces.IUseItemResponse;
import frontend.model.view.UseItem;

import java.util.function.Function;

public final class UseItemMapper implements Function<Object, IUseItemResponse> {

    @Override
    public IUseItemResponse apply(Object response) {
        var useItemResp = (IUseItemResponse) response;
        return new UseItem(useItemResp.message(),
                useItemResp.capacity(),
                useItemResp.maxCapacity(),
                useItemResp.itens());
    }
}
