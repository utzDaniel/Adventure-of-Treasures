package frontend.mapper;

import backend.controller.interfaces.IDropItemResponse;
import backend.controller.interfaces.IResponse;
import frontend.model.view.DropItem;

import java.util.function.Function;

public final class DropItemMapper implements Function<IResponse, IDropItemResponse> {

    @Override
    public IDropItemResponse apply(IResponse response) {
        var dropItemResponse = (IDropItemResponse) response;
        return new DropItem(dropItemResponse.message(),
                dropItemResponse.capacity(),
                dropItemResponse.maxCapacity(),
                dropItemResponse.itens());
    }
}
