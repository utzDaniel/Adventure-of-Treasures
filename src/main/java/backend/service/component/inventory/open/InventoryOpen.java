package backend.service.component.inventory.open;

import backend.controller.interfaces.IInventoryOpenResponse;
import backend.controller.interfaces.IItemDTO;
import backend.controller.interfaces.IResponse;
import backend.service.dto.response.InventoryOpenResponse;
import backend.service.exception.InventoryException;
import backend.service.factory.MessageFactory;
import backend.service.mapper.ItemDTOMapper;
import backend.service.model.Player;

import java.util.ArrayList;
import java.util.List;

public final class InventoryOpen {

    private final Player player;

    public InventoryOpen() {
        this.player = Player.getInstance();
    }

    public IResponse run() {
        return getInventoryOpenResponse();
    }

    private IInventoryOpenResponse getInventoryOpenResponse() {
        int capacity = this.player.getInventory().getCapacity();
        int maxCapacity = this.player.getInventory().getMaxCapacity();
        var exeption = isExeption();
        var message = new MessageFactory().create(exeption);
        List<IItemDTO> itensDTO = null;

        if (message.sucess()) {
            this.player.getInventory().setOpenInventory();
            message = new MessageFactory().create("Inventario aberto", "");
            var itens = new ArrayList<>(this.player.getInventory().getItemVisible());
            itensDTO = new ArrayList<>(itens.stream()
                    .map(item -> new ItemDTOMapper().apply(item))
                    .toList());
        }
        return new InventoryOpenResponse(message, capacity, maxCapacity, itensDTO);
    }

    private Exception isExeption() {
        try {
            boolean isOpen = this.player.getInventory().openInventory();
            if (isOpen) throw new InventoryException("Inventario está já está aberto!");
        } catch (Exception e) {
            return e;
        }
        return null;
    }

}
