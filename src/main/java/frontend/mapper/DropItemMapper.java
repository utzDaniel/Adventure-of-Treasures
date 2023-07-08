package frontend.mapper;

import backend.controller.interfaces.IDropItemResponse;
import frontend.model.view.DropItem;

import java.util.function.Function;

public final class DropItemMapper implements Function<Object, IDropItemResponse> {

    @Override
    public IDropItemResponse apply(Object response) {
        var dropItemResponse = (IDropItemResponse) response;
        return new DropItem(dropItemResponse.message(),
                dropItemResponse.capacity(),
                dropItemResponse.maxCapacity(),
                dropItemResponse.itens());
    }
}
