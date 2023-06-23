package frontend.mapper;

import backend.controller.interfaces.IResponse;
import backend.controller.interfaces.ITakeResponse;
import frontend.model.view.Take;

import java.util.function.Function;

public final class TakeMapper implements Function<IResponse, ITakeResponse> {

    @Override
    public ITakeResponse apply(IResponse response) {
        var takeResponse = (ITakeResponse) response;
        return new Take(takeResponse.message(),
                takeResponse.iconMap(),
                takeResponse.iconPlayer(),
                takeResponse.coordinatePlayer(),
                takeResponse.itens());
    }
}
