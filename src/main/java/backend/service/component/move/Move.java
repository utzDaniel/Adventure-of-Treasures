package backend.service.component.move;

import backend.controller.exception.MoveException;
import backend.controller.interfaces.*;
import backend.service.dto.response.MoveResponse;
import backend.service.factory.MessageFactory;
import backend.service.interfaces.ICoordinate;
import backend.service.mapper.ItemDTOMapper;
import backend.service.model.Player;

import java.util.ArrayList;
import java.util.List;

public final class Move {

    private final Player player = Player.getInstance();

    public IResponse run(IRequest request) {
        var moveRequest = (IMoveRequest) request;
        return getIMoveResponse(moveRequest);
    }

    private IMoveResponse getIMoveResponse(IMoveRequest moveRequest) {
        var coordinate = ICoordinate.getInstance(moveRequest.width(), moveRequest.height());
        var exeption = isExeption(moveRequest.direction(), coordinate);
        var message = new MessageFactory().create(exeption);
        return getMoveResponse(message);
    }

    private boolean isWalk(String direction, ICoordinate coordinate) {
        return switch (direction) {
            case "norte" -> player.getLocation().y() > 0;
            case "sul" -> player.getLocation().y() < coordinate.y() - 50;
            case "oeste" -> player.getLocation().x() > 0;
            case "leste" -> player.getLocation().x() < coordinate.x() - 30;
            default -> throw new MoveException("Direção invalida!");
        };
    }

    private Exception isExeption(String direction, ICoordinate coordinate) {
        try {
            if (isWalk(direction, coordinate))
                new MovePlayer(this.player, direction).run();
            else
                new MoveScenery(coordinate).run(direction);
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
        var coordinatePlayer = player.getLocation();
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
