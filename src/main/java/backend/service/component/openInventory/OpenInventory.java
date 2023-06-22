package backend.service.component.openInventory;

import backend.controller.interfaces.IInventoryResponse;
import backend.controller.interfaces.IItemDTO;
import backend.controller.interfaces.IResponse;
import backend.service.dto.response.InventoryResponse;
import backend.service.factory.MessageFactory;
import backend.service.mapper.ItemDTOMapper;
import backend.service.model.Player;
import backend.service.model.builder.Item;

import java.util.ArrayList;

public final class OpenInventory {

    private final Player player;

    public OpenInventory() {
        this.player = Player.getInstance();
    }

    public IResponse run() {
        return getInventoryResponse();
    }

    private IInventoryResponse getInventoryResponse() {
        boolean open = this.player.getInventory().openInventory();
        int capacity = this.player.getInventory().getCapacity();
        int maxCapacity = this.player.getInventory().getMaxCapacity();
        var itens = new ArrayList<Item>(this.player.getInventory().getItemVisible());
        var itensDTO = new ArrayList<IItemDTO>(itens.stream()
                .map(item -> new ItemDTOMapper().apply(item))
                .toList());
        var message = new MessageFactory().create("sucess", "");
        return new InventoryResponse(message, open, capacity, maxCapacity, itensDTO);
    }

}
