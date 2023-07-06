package backend.service.component.use;

import backend.controller.interfaces.IItemDTO;
import backend.controller.interfaces.IRequest;
import backend.controller.interfaces.IResponse;
import backend.controller.interfaces.IUseItemRequest;
import backend.service.dto.response.UseItemResponse;
import backend.service.factory.MessageFactory;
import backend.service.interfaces.IUsable;
import backend.service.mapper.ItemDTOMapper;
import backend.service.model.Player;
import backend.service.model.builder.Item;

import java.util.ArrayList;

public final class ServiceUseItem {

    private final Player player = Player.getInstance();

    public IResponse run(IRequest request) {
        var useItemRequest = (IUseItemRequest) request;
        var name = useItemRequest.name();

        Item item = player.getInventory().getItemVisible().stream()
                .filter(item1 -> item1.getName().equals(name)).findFirst().get();

        var exeption = isExeption(item);
        var message = new MessageFactory().create(exeption);

        if (message.sucess()) {
            message = new MessageFactory().create("Item usado!", item.getEffect());
        }

        var capacity = player.getInventory().getCapacity();
        var maxCapacity = player.getInventory().getMaxCapacity();
        var indexItem = 1;
        var itensDto = new ArrayList<IItemDTO>(player.getInventory().getItemVisible().stream()
                .map(item1 -> new ItemDTOMapper().apply(item1))
                .toList());
        var iconMap = player.getCurrentMap().getIcon().toString();

        return new UseItemResponse(message, capacity, maxCapacity, itensDto, indexItem, iconMap);
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
