package backend.service.component.open;

import backend.controller.interfaces.IActionResponse;
import backend.controller.interfaces.IItemDTO;
import backend.controller.interfaces.IResponse;
import backend.service.dto.response.ActionResponse;
import backend.service.factory.MessageFactory;
import backend.service.infra.Cache;
import backend.service.interfaces.ICoordinate;
import backend.service.mapper.ItemDTOMapper;
import backend.service.model.Door;
import backend.service.model.Player;
import backend.service.model.builder.Item;
import backend.service.model.builder.MapGame;

import java.util.ArrayList;
import java.util.Objects;

public final class Open {

    private final Player player = Player.getInstance();

    //TODO deixar o conversão para o controller
    public IResponse run() {
        ICoordinate coordinate = player.getLocation();
        return getActionResponse(coordinate);
    }

    private IActionResponse getActionResponse(ICoordinate coordinate) {

        //TODO finish game
        var finish = isFinish(coordinate);
        if (Objects.nonNull(finish)) return finish;

        var door = player.getCurrentMap().getDoor(player.getLocation()).orElse(null);
        var exeption = isExeption(door);
        var message = new MessageFactory().create(exeption);
        int indexItens = 1;

        if (message.sucess()) {
            message = new MessageFactory().create("Porta aberta!", "");
            MapGame mapGame = Cache.getMapGame(door.getMapGame());
            updatePositionPlayer(mapGame);
            player.setCurrentMap(mapGame);
        }

        var iconMap = player.getCurrentMap().getIcon().toString();
        var songMap = this.player.getCurrentMap().getSong();
        var iconPlayer = player.getIcon().toString();
        var coordinatePlayer = ICoordinate.getInstance(player.getLocation().y() * 10, player.getLocation().x() * 10);
        var itens = new ArrayList<Item>(this.player.getCurrentMap().getItemVisible());
        var itensDTO = new ArrayList<IItemDTO>(itens.stream()
                .map(item -> new ItemDTOMapper().apply(item))
                .toList());

        return new ActionResponse(message, iconMap, songMap, iconPlayer, coordinatePlayer, itensDTO, indexItens);
    }

    private IActionResponse isFinish(ICoordinate coordinate) {
        if (coordinate.x() == 710 && coordinate.y() == 280
                && Objects.nonNull(player.getInventory().getItem("tesouro"))) {
            return new ActionResponse(null, null, "src/main/resources/audio/effects/finish.wav", null, null, null, 0);
        }
        return null;
    }

    private Exception isExeption(Door door) {
        try {
            new OpenDoor(door).run();
        } catch (Exception e) {
            return e;
        }
        return null;
    }

    private void updatePositionPlayer(MapGame mapGame) {
        var nameMap = player.getCurrentMap().getName();
        var door = mapGame.getDoor(nameMap).get();
        player.setLocation(door.getCoordinate());
    }

}
