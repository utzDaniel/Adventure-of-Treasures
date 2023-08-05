package backend.service.mapper;

import backend.controller.interfaces.IActionResponse;
import backend.controller.interfaces.IItemDTO;
import backend.service.dto.response.ActionResponse;
import backend.service.interfaces.ICoordinate;
import backend.service.model.Player;

import java.util.ArrayList;
import java.util.function.Function;

public final class ActionResponseMapper implements Function<Player, IActionResponse> {

    @Override
    public IActionResponse apply(Player player) {

        var itens = new ArrayList<>(player.getCurrentMap().getItemVisible());
        var itensDTO = new ArrayList<IItemDTO>(itens.stream()
                .map(item -> new ItemDTOMapper().apply(item))
                .toList());

        var iconMap = player.getCurrentMap().getImage();
        var songMap = player.getCurrentMap().getSong();
        var iconPlayer = player.getImage();
        int step = 10;
        var x = player.getLocation().y() * step;
        var y = player.getLocation().x() * step;
        var coordinatePlayer = ICoordinate.getInstance(x, y);
        var indexItens = 1;
        return new ActionResponse(iconMap, songMap, iconPlayer, coordinatePlayer, itensDTO, indexItens);
    }
}
