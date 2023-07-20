package frontend.mapper;

import backend.controller.interfaces.IActionResponse;
import frontend.model.view.Action;

import java.util.function.Function;

public final class ActionMapper implements Function<Object, IActionResponse> {
    @Override
    public IActionResponse apply(Object response) {
        var moveResponse = (IActionResponse) response;
        return new Action(moveResponse.message(),
                moveResponse.iconMap(),
                moveResponse.songMap(),
                moveResponse.iconPlayer(),
                moveResponse.coordinatePlayer(),
                moveResponse.itens(),
                moveResponse.indexItens());
    }

}
