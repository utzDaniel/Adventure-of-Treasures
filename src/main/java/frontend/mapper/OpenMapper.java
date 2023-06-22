package frontend.mapper;

import backend.controller.interfaces.IOpenResponse;
import backend.controller.interfaces.IResponse;
import frontend.model.view.Open;

import java.util.function.Function;

public final class OpenMapper implements Function<IResponse, IOpenResponse> {
    @Override
    public IOpenResponse apply(IResponse response) {
        var openResponse = (IOpenResponse) response;
        return new Open(openResponse.message(),
                openResponse.iconMap(),
                openResponse.songMap(),
                openResponse.iconPlayer(),
                openResponse.coordinatePlayer(),
                openResponse.itens());
    }
}
