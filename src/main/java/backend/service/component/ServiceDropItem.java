package backend.service.component;

import backend.controller.interfaces.IDropItemRequest;
import backend.controller.interfaces.IRequest;
import backend.controller.interfaces.IResponse;
import backend.service.dto.response.DropItemResponse;
import backend.service.factory.MessageFactory;
import backend.service.mapper.ItemDTOMapper;
import backend.service.model.Player;
import backend.service.model.builder.Item;

public class ServiceDropItem {

    private final Player player = Player.getInstance();

    public IResponse run(IRequest request) {
        var dropItemRequest = (IDropItemRequest) request;
        var name = dropItemRequest.name();

        Item item = player.getInventory().getItemVisible().stream()
                .filter(item1 -> item1.getName().equals(name)).findFirst().get();

        var exeption = isExeption(item);
        var message = new MessageFactory().create(exeption);
        if (message.sucess())
            message = new MessageFactory().create("Item removido!", "remover");

        var capacity = player.getInventory().getCapacity();
        var maxCapacity = player.getInventory().getMaxCapacity();
        var indexItem = 1;
        var itemDto = new ItemDTOMapper().apply(item);

        return new DropItemResponse(message, capacity, maxCapacity, itemDto, indexItem);
    }

    private Exception isExeption(Item item) {
        try {
            new DropItem(player, item).run();
        } catch (Exception e) {
            return e;
        }
        return null;
    }
}
