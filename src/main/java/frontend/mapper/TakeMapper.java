package frontend.mapper;

import backend.controller.interfaces.ITakeResponse;
import frontend.model.view.Take;

import java.util.function.Function;

public final class TakeMapper implements Function<Object, ITakeResponse> {

    @Override
    public ITakeResponse apply(Object response) {
        var takeResponse = (ITakeResponse) response;
        return new Take(takeResponse.message(),
                takeResponse.iconMap(),
                takeResponse.iconPlayer(),
                takeResponse.coordinatePlayer(),
                takeResponse.itens());
    }
}
