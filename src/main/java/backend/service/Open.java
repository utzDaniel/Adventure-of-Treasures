package backend.service;

import backend.model.Player;
import backend.model.builder.item.Item;
import backend.model.dto.ItemDTO;
import backend.model.dto.OpenDTO;
import backend.util.JsonConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import rules.exception.MapGameException;
import rules.interfaces.ICoordinate;
import rules.interfaces.IItem;
import rules.service.NextDoor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Open {

    private final Player player = Player.getInstance();

    public String run() {
        try {
            ICoordinate coordinate = player.getLocation();
            var openDTO = getOpenDTO(coordinate);
            return JsonConverter.getJson(openDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private OpenDTO getOpenDTO(ICoordinate coordinate) {
        if (coordinate.getX() == 710 && coordinate.getY() == 280
                && Objects.nonNull(player.getInventory().getItem("tesouro"))) {
            return new OpenDTO(null, null, null, "finish", null);
        }
        List<IItem> itensDTO = null;
        try {

            new NextDoor().run();
            String song = this.player.getCurrentMap().getSong();
            List<Item> itens = new ArrayList<>(this.player.getCurrentMap().getItemVisible());
            itensDTO = new ArrayList<>(itens.stream()
                    .map(item -> new ItemDTO(item.getIcon().toString(), item.getLocation(), item.getName(),
                            item.getDescription(), item.getEffect(), item.getWeight(), item.getClass().getName(), item.equipped()))
                    .toList());
            return new OpenDTO(player.getCurrentMap().getIcon().toString(), player.getIcon().toString(),
                    player.getLocation(), song, itensDTO);

        } catch (MapGameException ex) {
            //TODO porta fechada
            return new OpenDTO(player.getCurrentMap().getIcon().toString(), player.getIcon().toString(),
                    player.getLocation(), "erro", itensDTO);
        }
    }

}
