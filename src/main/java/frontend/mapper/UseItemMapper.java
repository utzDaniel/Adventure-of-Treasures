package frontend.mapper;

import backend.controller.interfaces.IResponse;
import backend.controller.interfaces.IUseItemResponse;
import frontend.model.view.UseItem;

import java.util.function.Function;

public final class UseItemMapper implements Function<IResponse, IUseItemResponse> {

    @Override
    public IUseItemResponse apply(IResponse response) {
        var useItemResp = (IUseItemResponse) response;
        return new UseItem(useItemResp.message(),
                useItemResp.capacity(),
                useItemResp.maxCapacity(),
                useItemResp.itens(),
                useItemResp.indexItem(),
                useItemResp.iconMap());
    }
}
