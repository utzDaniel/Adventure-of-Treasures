package backend.service.component.take;

import backend.controller.interfaces.IActionResponse;
import backend.controller.interfaces.IItemDTO;
import backend.controller.interfaces.IResponse;
import backend.service.dto.response.ActionResponse;
import backend.service.enums.MovePlayer;
import backend.service.factory.MessageFactory;
import backend.service.interfaces.ICoordinate;
import backend.service.mapper.ItemDTOMapper;
import backend.service.model.Player;
import backend.service.model.builder.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Take {

    private final Player player;

    public Take() {
        this.player = Player.getInstance();
    }

    public IResponse run() {
        return getActionResponse();
    }

    private IActionResponse getActionResponse() {
        var item = getItem();
        var iconMap = player.getCurrentMap().getIcon().toString();
        var iconPlayer = player.getIcon().toString();
        var songMap = player.getCurrentMap().getSong();

        var coordinatePlayer = ICoordinate.getInstance(player.getLocation().y() * 10, player.getLocation().x() * 10);
        var exeption = isExeption(item);
        var message = new MessageFactory().create(exeption);
        var indexItens = 1;

        List<IItemDTO> itens = null;
        if (message.sucess()) {
            message = new MessageFactory().create("Item adicionado a mochila!", "src/main/resources/audio/effects/pegar.wav");
            List<Item> itensMap = new ArrayList<>(this.player.getCurrentMap().getItemVisible());
            itens = new ArrayList<>(itensMap.stream()
                    .map(item1 -> new ItemDTOMapper().apply(item1))
                    .toList());
        }
        return new ActionResponse(message, iconMap, songMap, iconPlayer, coordinatePlayer, itens, indexItens);
    }

    private Exception isExeption(Item item) {
        try {
            new TakeItem(this.player, item).run();
        } catch (Exception e) {
            return e;
        }
        return null;
    }

    private Item getItem() {
        var direction = player.getDirection();
        var coordinate = player.getLocation();
        var mapGame = player.getCurrentMap();
        Arrays.stream(MovePlayer.values())
                .filter(move -> move.getDirection().equals(direction))
                .map(MovePlayer::getCoordinate)
                .findFirst()
                .ifPresent(coordinate::move);
        return mapGame.getItem(coordinate);
    }
}
