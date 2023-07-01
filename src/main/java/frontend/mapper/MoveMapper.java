package frontend.mapper;

import backend.controller.interfaces.IMoveResponse;
import backend.controller.interfaces.IResponse;
import frontend.model.view.Move;

import java.util.function.Function;

public final class MoveMapper implements Function<IResponse, IMoveResponse> {
    @Override
    public IMoveResponse apply(IResponse response) {
        var moveResponse = (IMoveResponse) response;
        return new Move(moveResponse.message(),
                moveResponse.iconMap(),
                moveResponse.songMap(),
                moveResponse.iconPlayer(),
                moveResponse.coordinatePlayer(),
                moveResponse.itens(),
                moveResponse.indexItens());
    }
}