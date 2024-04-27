package backend.service;

import backend.controller.enums.TypeMessage;
import backend.controller.interfaces.IInventoryService;
import backend.controller.interfaces.IServiceResponse;
import backend.service.command.CommandFactory;
import backend.service.component.inventory.open.InventoryOpen;
import backend.service.dto.response.ServiceResponse;
import backend.service.mapper.InventoryResponseMapper;
import backend.service.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static backend.Game.player;

public final class InventoryService implements IInventoryService {

    @Override
    public IServiceResponse combination(List<Integer> idItems) {
        var inventory = player.getInventory();

        var items = new ArrayList<Item>();
        idItems.forEach(id -> items.add(getItem(id).orElse(null)));

        var typeMessage = TypeMessage.ITEM_ERROR_FOUND;

        if (!items.isEmpty()) {
            var cmd = CommandFactory.createCombinationCommand(inventory, items);
            typeMessage = cmd.execute();
        }

        if (!typeMessage.isSuccess())
            new ServiceResponse(typeMessage, null);

        var obj = new InventoryResponseMapper().apply(inventory);
        return new ServiceResponse(typeMessage, obj);
    }

    @Override
    public IServiceResponse use(Integer idItem) {

        var item = getItem(idItem);
        var typeMessage = TypeMessage.ITEM_ERROR_FOUND;

        if (item.isPresent()) {
            var list = new ArrayList<Item>();
            list.add(item.get());
            var cmd = CommandFactory.createUsableCommand(player, list);
            typeMessage = cmd.execute();
        }

        if (!typeMessage.isSuccess())
            new ServiceResponse(typeMessage, null);

        var obj = new InventoryResponseMapper().apply(player.getInventory());
        return new ServiceResponse(typeMessage, obj);
    }

    @Override
    public IServiceResponse equip(Integer idItem) {
        var inventory = player.getInventory();

        var item = getItem(idItem);
        var typeMessage = TypeMessage.ITEM_ERROR_FOUND;

        if (item.isPresent()) {
            var list = new ArrayList<Item>();
            list.add(item.get());
            var cmd = CommandFactory.createEquippableCommand(inventory, list);
            typeMessage = cmd.execute();
        }

        if (!typeMessage.isSuccess())
            new ServiceResponse(typeMessage, null);


        var obj = new InventoryResponseMapper().apply(inventory);
        return new ServiceResponse(typeMessage, obj);
    }

    @Override
    public IServiceResponse drop(Integer idItem) {
        var inventory = player.getInventory();

        var item = getItem(idItem);
        var typeMessage = TypeMessage.ITEM_ERROR_FOUND;

        if (item.isPresent()) {
            var list = new ArrayList<Item>();
            list.add(item.get());
            var cmd = CommandFactory.createDropItemCommand(player, list);
            typeMessage = cmd.execute();
        }

        if (!typeMessage.isSuccess())
            new ServiceResponse(typeMessage, null);

        var obj = new InventoryResponseMapper().apply(inventory);
        return new ServiceResponse(typeMessage, obj);
    }

    @Override
    public IServiceResponse open() {
        var inventory = player.getInventory();
        var typeMessage = new InventoryOpen(inventory).run();

        if (!typeMessage.isSuccess())
            new ServiceResponse(typeMessage, null);

        var obj = new InventoryResponseMapper().apply(inventory);
        return new ServiceResponse(typeMessage, obj);
    }

    private Optional<Item> getItem(Integer idItem) {
        return player.getInventory().getItems().stream()
                .filter(item1 -> item1.getId() == idItem)
                .findFirst();
    }
}
