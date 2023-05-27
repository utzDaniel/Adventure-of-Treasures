package backend.service;

import backend.model.Player;
import backend.model.dto.OpenInventoryDTO;
import backend.util.JsonConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import rules.interfaces.IOpenInventory;

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
        return new OpenInventoryDTO(this.player.getInventory().openInventory());
    }

}
