package backend.service.component.use;

import backend.controller.interfaces.IItemDTO;
import backend.controller.interfaces.IResponse;
import backend.service.dto.response.InventoryResponse;
import backend.service.factory.MessageFactory;
import backend.service.interfaces.IUsable;
import backend.service.mapper.ItemDTOMapper;
import backend.service.model.Player;
import backend.service.model.builder.Item;

import java.util.ArrayList;

public final class ServiceUseItem {

    private final Player player = Player.getInstance();

    public IResponse run(String name) {

        Item item = player.getInventory().getItemVisible().stream()
                .filter(item1 -> item1.getName().equals(name)).findFirst().get();

        var exeption = isExeption(item);
        var message = new MessageFactory().create(exeption);

        if (message.sucess()) {
            message = new MessageFactory().create("Item usado!", ((IUsable) item).getEffect());
        }
        var capacity = player.getInventory().getCapacity();
        var maxCapacity = player.getInventory().getMaxCapacity();
        var itensDto = new ArrayList<IItemDTO>(player.getInventory().getItemVisible().stream()
                .map(item1 -> new ItemDTOMapper().apply(item1))
                .toList());

        return new InventoryResponse(message, capacity, maxCapacity, itensDto);
    }

    private Exception isExeption(Item item) {
        try {
            if (item instanceof IUsable usable) {
                usable.use();
            }
        } catch (Exception e) {
            return e;
        }
        return null;
    }
}
