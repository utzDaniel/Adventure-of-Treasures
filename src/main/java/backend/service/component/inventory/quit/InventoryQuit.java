package backend.service.component.inventory.quit;

import backend.controller.interfaces.IInventoryQuitResponse;
import backend.controller.interfaces.IItemDTO;
import backend.controller.interfaces.IResponse;
import backend.service.dto.response.InventoryQuitResponse;
import backend.service.exception.InventoryException;
import backend.service.factory.MessageFactory;
import backend.service.mapper.ItemDTOMapper;
import backend.service.model.Player;

import java.util.ArrayList;
import java.util.List;

public final class InventoryQuit {

    private final Player player;

    public InventoryQuit() {
        this.player = Player.getInstance();
    }

    public IResponse run() {
        return getInventoryQuitResponse();
    }

    private IInventoryQuitResponse getInventoryQuitResponse() {

        var iconMap = this.player.getCurrentMap().getIcon().toString();

        var indexItens = 1;
        var exeption = isExeption();

        var message = new MessageFactory().create(exeption);
        List<IItemDTO> itensDTO = null;

        if (message.sucess()) {
            this.player.getInventory().setOpenInventory();
            message = new MessageFactory().create("Inventario fechado", "");
            var itens = new ArrayList<>(this.player.getCurrentMap().getItemVisible());
            itensDTO = new ArrayList<>(itens.stream()
                    .map(item -> new ItemDTOMapper().apply(item))
                    .toList());
        }

        return new InventoryQuitResponse(message, iconMap, itensDTO, indexItens);
    }

    private Exception isExeption() {
        try {
            boolean isOpen = this.player.getInventory().openInventory();
            if (!isOpen) throw new InventoryException("Inventario já está fechado!");
        } catch (Exception e) {
            return e;
        }
        return null;
    }

}
