package backend.service;

import backend.model.Player;
import backend.model.builder.item.Item;
import backend.model.dto.ItemDTO;
import backend.model.dto.TakeDTO;
import backend.util.JsonConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import rules.exception.InventoryException;
import rules.interfaces.IItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Take {

    private final Player player;

    public Take() {
        this.player = Player.getInstance();
    }

    public String run() {
        try {
            var takeDTO = getTakeDTO();
            return JsonConverter.getJson(takeDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private TakeDTO getTakeDTO() {
        List<IItem> itensDTO = null;
        try {
            if (take()) {
                List<Item> itens = new ArrayList<>(this.player.getCurrentMap().getItemVisible());
                itensDTO = new ArrayList<>(itens.stream()
                        .map(item -> new ItemDTO(item.getIcon().toString(), item.getLocation(), item.getName(),
                                item.getDescription(), item.getEffect(), item.getWeight(), item.getClass().getName(), item.equipped()))
                        .toList());

                return new TakeDTO(player.getCurrentMap().getIcon().toString(), player.getIcon().toString(),
                        player.getLocation(), "pegar", itensDTO);

            } else {
                return new TakeDTO(player.getCurrentMap().getIcon().toString(), player.getIcon().toString(),
                        player.getLocation(), null, itensDTO);
            }

        } catch (InventoryException ex) {
            //TODO inventario cheio
            return new TakeDTO(player.getCurrentMap().getIcon().toString(), player.getIcon().toString(),
                    player.getLocation(), "erro", itensDTO);
        }
    }

    private boolean take() {
        Item item = this.player.lookItem();
        if (Objects.isNull(item)) return false;
        player.takeItem(item);
        return true;
    }
}
