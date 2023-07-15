package backend.service.component.move;

import backend.controller.interfaces.*;
import backend.service.dto.response.MoveResponse;
import backend.service.exception.MoveException;
import backend.service.factory.MessageFactory;
import backend.service.interfaces.ICoordinate;
import backend.service.mapper.ItemDTOMapper;
import backend.service.model.Area;
import backend.service.model.Player;

import java.util.ArrayList;
import java.util.List;

public final class Move {
    private final Player player = Player.getInstance();
    public IResponse run(String direction) {
        return getIMoveResponse(direction);
    }

    private IMoveResponse getIMoveResponse(String direction) {
        var exeption = isExeption(direction);
        var message = new MessageFactory().create(exeption);
        return getMoveResponse(message);
    }


    private boolean isWalk(String direction) {
        return switch (direction) {
            case "norte" -> player.getLocation().x() > Area.minX();
            case "sul" -> player.getLocation().x() < Area.maxX();
            case "oeste" -> player.getLocation().y() > Area.minY();
            case "leste" -> player.getLocation().y() < Area.maxY();
            default -> throw new MoveException("Direção invalida!");
        };
    }

    private Exception isExeption(String direction) {
        try {
            if (isWalk(direction))
                new MovePlayer(this.player, direction).run();
            else
                new MoveScenery(this.player, direction).run();
        } catch (Exception e) {
            return e;
        }
        return null;
    }

    private MoveResponse getMoveResponse(IMessage message) {
        var newMessage = message;

        var iconMap = player.getCurrentMap().getIcon().toString();
        String songMap = this.player.getCurrentMap().getSong();
        var iconPlayer = player.getIcon().toString();
        var coordinatePlayer = ICoordinate.getInstance(player.getLocation().y()*10, player.getLocation().x() *10);
        List<IItemDTO> itens = new ArrayList<>(this.player.getCurrentMap().getItemVisible().stream()
                .map(i -> new ItemDTOMapper().apply(i))
                .toList());
        int indexItens = 1;

        if (message.sucess()) {
            var map = player.getCurrentMap().getName();
            var text = String.format("Vamos para %s", map);
            newMessage = new MessageFactory().create(text, "");
        }
        return new MoveResponse(newMessage, iconMap, songMap, iconPlayer, coordinatePlayer, itens, indexItens);
    }

}
