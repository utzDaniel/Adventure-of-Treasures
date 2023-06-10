package backend.service.component;

import backend.service.model.Player;
import backend.service.model.builder.Item;
import backend.dto.ItemDTO;
import backend.dto.OpenInventoryDTO;
import backend.util.JsonConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import backend.controller.interfaces.IItem;
import backend.controller.interfaces.IOpenInventory;

import java.util.ArrayList;
import java.util.List;

public class OpenInventory {

    private final Player player;

    public OpenInventory() {
        this.player = Player.getInstance();
    }

    public String run() {
        try {
            var openInventoryDTO = getOpenInventory();
            return JsonConverter.getJson(openInventoryDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private IOpenInventory getOpenInventory() {
        boolean open = this.player.getInventory().openInventory();
        int capacity = this.player.getInventory().getCapacity();
        int maxCapacity = this.player.getInventory().getMaxCapacity();
        List<IItem> itensDTO = null;
        List<Item> itens = new ArrayList<>(this.player.getInventory().getItemVisible());
        itensDTO = new ArrayList<>(itens.stream()
                .map(item -> new ItemDTO(item.getIcon().toString(), item.getLocation(), item.getName(),
                        item.getDescription(), item.getEffect(), item.getWeight(), item.getClass().getName(), item.equipped()))
                .toList());
        return new OpenInventoryDTO(open, capacity, maxCapacity, itensDTO);
    }

}
