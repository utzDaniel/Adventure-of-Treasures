package backend.service.move;

import backend.controller.exception.MoveException;
import backend.controller.interfaces.IItemDTO;
import backend.controller.interfaces.IMessage;
import backend.controller.interfaces.IMoveResponse;
import backend.controller.util.JsonConverter;
import backend.service.dto.response.MoveResponse;
import backend.service.factory.MessageFactory;
import backend.service.interfaces.ICoordinate;
import backend.service.mapper.ItemDTOMapper;
import backend.service.model.Coordinate;
import backend.service.model.Player;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.List;

public final class MovePlayer {

    private final Player player = Player.getInstance();

    public String run(String direction, String json) {
        try {
            ICoordinate coordinate = JsonConverter.getObjetc(json, Coordinate.class);
            var moveResponse = getIMoveResponse(direction, coordinate);
            return JsonConverter.getJson(moveResponse);
        } catch (JsonProcessingException e) {
            throw new MoveException(e.getMessage());
        }
    }

    private IMoveResponse getIMoveResponse(String direction, ICoordinate coordinate) {

        var exeption = isExeption(direction, coordinate);
        var message = new MessageFactory().create(exeption);
        return getMoveResponse(message);
    }

    private boolean isWalk(String direction, ICoordinate coordinate) {
        return switch (direction) {
            case "norte" -> player.getLocation().y() > 0;
            case "sul" -> player.getLocation().y() < coordinate.y() - 50;
            case "oeste" -> player.getLocation().x() > 0;
            case "leste" -> player.getLocation().x() < coordinate.x() - 30;
            default ->  throw new MoveException("Direção invalida!");
        };
    }

    private Exception isExeption(String direction, ICoordinate coordinate) {
        try {
            if (isWalk(direction, coordinate)) {
                new Walk(this.player, direction).run();
            }
            new NextScenery(coordinate).run(direction);
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
