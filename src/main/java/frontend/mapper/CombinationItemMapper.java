package frontend.mapper;

import backend.controller.interfaces.ICombinationItemResponse;
import backend.controller.interfaces.IResponse;
import frontend.model.view.CombinationItem;

import java.util.function.Function;

public final class CombinationItemMapper implements Function<IResponse, ICombinationItemResponse> {

    @Override
    public ICombinationItemResponse apply(IResponse response) {
        var combinationItemResp = (ICombinationItemResponse) response;
        return new CombinationItem(combinationItemResp.message(),
                combinationItemResp.capacity(),
                combinationItemResp.maxCapacity(),
                combinationItemResp.itens(),
                combinationItemResp.indexItem(),
                combinationItemResp.iconMap());
    }
}
