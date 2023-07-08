package frontend.mapper;

import backend.controller.interfaces.ICombinationItemResponse;
import frontend.model.view.CombinationItem;

import java.util.function.Function;

public final class CombinationItemMapper implements Function<Object, ICombinationItemResponse> {

    @Override
    public ICombinationItemResponse apply(Object response) {
        var combinationItemResp = (ICombinationItemResponse) response;
        return new CombinationItem(combinationItemResp.message(),
                combinationItemResp.capacity(),
                combinationItemResp.maxCapacity(),
                combinationItemResp.itens());
    }
}
