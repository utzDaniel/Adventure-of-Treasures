package backend.service.component.equip;

import backend.controller.interfaces.IEquipItemRequest;
import backend.controller.interfaces.IRequest;
import backend.controller.interfaces.IResponse;
import backend.service.dto.response.EquipItemResponse;
import backend.service.factory.MessageFactory;
import backend.service.interfaces.IEquipable;
import backend.service.mapper.ItemDTOMapper;
import backend.service.model.Player;
import backend.service.model.builder.Item;

public class ServiceEquipItem {

    private final Player player = Player.getInstance();

    public IResponse run(IRequest request) {
        var equipItemRequest = (IEquipItemRequest) request;
        var name = equipItemRequest.name();

        Item item = player.getInventory().getItemVisible().stream()
                .filter(item1 -> item1.getName().equals(name)).findFirst().get();

        var exeption = isExeption(item);
        var message = new MessageFactory().create(exeption);

        if (message.sucess()) {
            String msg = ((IEquipable) item).isEquipped() ? "Item equipado!" : "Item desequipado!";
            message = new MessageFactory().create(msg, item.getEffect());
        }

        var capacity = player.getInventory().getCapacity();
        var maxCapacity = player.getInventory().getMaxCapacity();
        var indexItem = 1;
        var itemDto = new ItemDTOMapper().apply(item);

        return new EquipItemResponse(message, capacity, maxCapacity, itemDto, indexItem);
    }

    private Exception isExeption(Item item) {
        try {
            if (item instanceof IEquipable equipable) {
                if (equipable.isEquipped()) {
                    equipable.unequip();
                } else {
                    equipable.equip();
                }
            }
        } catch (Exception e) {
            return e;
        }
        return null;
    }
}
