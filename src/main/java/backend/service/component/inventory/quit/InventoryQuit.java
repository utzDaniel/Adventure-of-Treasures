package backend.service.component.inventory.quit;

import backend.controller.interfaces.IActionResponse;
import backend.controller.interfaces.IItemDTO;
import backend.controller.interfaces.IResponse;
import backend.service.dto.response.ActionResponse;
import backend.service.exception.InventoryException;
import backend.service.factory.MessageFactory;
import backend.service.interfaces.ICoordinate;
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
        return getActionResponse();
    }

    private IActionResponse getActionResponse() {

        var iconMap = this.player.getCurrentMap().getIcon().toString();
        var songMap = this.player.getCurrentMap().getSong();
        var iconPlayer = player.getIcon().toString();
        var coordinatePlayer = ICoordinate.getInstance(player.getLocation().y() * 10, player.getLocation().x() * 10);

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

        return new ActionResponse(message, iconMap, songMap, iconPlayer, coordinatePlayer, itensDTO, indexItens);
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
