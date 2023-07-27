package frontend.mapper;

import backend.controller.interfaces.IActionResponse;
import backend.controller.interfaces.IResponse;
import frontend.model.view.Action;

import java.util.function.Function;

public final class ActionMapper implements Function<Object, IActionResponse> {
    @Override
    public IActionResponse apply(Object response) {
        var resp = (IResponse) response;
        var action = (IActionResponse) resp.obj();
        return new Action(action.iconMap(),
                action.songMap(),
                action.iconPlayer(),
                action.coordinatePlayer(),
                action.itens(),
                action.indexItens());
    }

}
