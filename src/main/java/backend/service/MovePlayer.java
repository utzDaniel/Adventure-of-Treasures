package backend.service;

import backend.model.Coordinate;
import backend.model.Player;
import backend.model.builder.item.Item;
import backend.model.dto.ItemDTO;
import backend.model.dto.MovePlayerDTO;
import backend.util.JsonConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import rules.interfaces.ICoordinate;
import rules.interfaces.IItemDTO;
import rules.service.NextScenery;

import java.util.ArrayList;
import java.util.List;

public class MovePlayer {

    private final Player player = Player.getInstance();

    public String run(String direction, String json) {
        try {
            ICoordinate coordinate = JsonConverter.getObjetc(json, Coordinate.class);
            var movePlayerDTO = getMovePlayerDTO(direction, coordinate);
            return JsonConverter.getJson(movePlayerDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private MovePlayerDTO getMovePlayerDTO(String direction, ICoordinate coordinate) {
        boolean sucess = false;

        if ("norte".equals(direction)) {
            if (player.getLocation().getY() > 0) {
                player.walk(direction);
            } else {
                sucess = new NextScenery(coordinate).run(direction);
            }
        } else if ("sul".equals(direction)) {

            if (player.getLocation().getY() < coordinate.getY() - 50) {
                player.walk(direction);
            } else {
                sucess = new NextScenery(coordinate).run(direction);
            }

        } else if ("oeste".equals(direction)) {

            if (player.getLocation().getX() > 0) {
                player.walk(direction);
            } else {
                sucess = new NextScenery(coordinate).run(direction);
            }

        } else if ("leste".equals(direction)) {

            if (player.getLocation().getX() < coordinate.getX() - 30) {
                player.walk(direction);
            } else {
                sucess = new NextScenery(coordinate).run(direction);
            }

        }

        String song = null;
        List<IItemDTO> itensDTO = null;

        if (sucess) {
            song = this.player.getCurrentMap().getSong();
            List<Item> itens = new ArrayList<>(this.player.getCurrentMap().getItemVisible());
            itensDTO = new ArrayList<>(itens.stream()
                    .map(item -> new ItemDTO(item.getIcon().toString(), item.getLocation()))
                    .toList());
        }

        return new MovePlayerDTO(player.getCurrentMap().getIcon().toString(), player.getIcon().toString(),
                player.getLocation(), song, itensDTO, 1);
    }


}
