package backend.service;

import backend.Game;
import backend.controller.enums.TypeMessage;
import backend.controller.interfaces.IInventoryService;
import backend.controller.interfaces.IServiceResponse;
import backend.service.command.CombinationCommand;
import backend.service.command.DropItemCommand;
import backend.service.command.EquipableCommand;
import backend.service.command.UsableCommand;
import backend.service.component.inventory.open.InventoryOpen;
import backend.service.dto.response.ServiceResponse;
import backend.service.mapper.InventoryResponseMapper;
import backend.service.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class InventoryService implements IInventoryService {

    @Override
    public IServiceResponse combination(List<Integer> idItens) {
        var inventory = Game.player.getInventory();

        var itens = new ArrayList<Item>();
        idItens.forEach(id -> itens.add(getItem(id).orElse(null)));

        var typeMessage = TypeMessage.ITEM_ERRO_FOUND;

        if (!itens.isEmpty()) {
            var cmd = new CombinationCommand(itens, inventory);
            typeMessage = cmd.execute();
        }

        if (!typeMessage.isSucess())
            new ServiceResponse(typeMessage, null);

        if (!itens.isEmpty() && typeMessage.isSucess())
            itens.get(0).warn();

        var obj = new InventoryResponseMapper().apply(inventory);
        return new ServiceResponse(typeMessage, obj);
    }

    @Override
    public IServiceResponse use(Integer idItem) {
        var inventory = Game.player.getInventory();
        var idMap = Game.player.getCurrentMap().getId();
        var coordinate = Game.player.getCoordinate();

        var item = getItem(idItem);
        var typeMessage = TypeMessage.ITEM_ERRO_FOUND;

        if (item.isPresent()) {
            var cmd = new UsableCommand(item.get(), idMap, coordinate, inventory);
            typeMessage = cmd.execute();
        }

        if (!typeMessage.isSucess())
            new ServiceResponse(typeMessage, null);
        else item.ifPresent(Item::warn);

        var obj = new InventoryResponseMapper().apply(inventory);
        return new ServiceResponse(typeMessage, obj);
    }

    @Override
    public IServiceResponse equip(Integer idItem) {
        var inventory = Game.player.getInventory();

        var item = getItem(idItem);
        var typeMessage = TypeMessage.ITEM_ERRO_FOUND;

        if (item.isPresent()) {
            var cmd = new EquipableCommand(item.get(), inventory);
            typeMessage = cmd.execute();
        }

        if (!typeMessage.isSucess())
            new ServiceResponse(typeMessage, null);
        else item.ifPresent(Item::warn);

        var obj = new InventoryResponseMapper().apply(inventory);
        return new ServiceResponse(typeMessage, obj);
    }

    @Override
    public IServiceResponse drop(Integer idItem) {
        var inventory = Game.player.getInventory();

        var item = getItem(idItem);
        var typeMessage = TypeMessage.ITEM_ERRO_FOUND;

        if (item.isPresent()) {
            var cmd = new DropItemCommand(item.get(), Game.player);
            typeMessage = cmd.execute();
        }

        if (!typeMessage.isSucess())
            new ServiceResponse(typeMessage, null);

        var obj = new InventoryResponseMapper().apply(inventory);
        return new ServiceResponse(typeMessage, obj);
    }

    @Override
    public IServiceResponse open() {
        var inventory = Game.player.getInventory();
        var typeMessage = new InventoryOpen(inventory).run();

        if (!typeMessage.isSucess())
            new ServiceResponse(typeMessage, null);

        var obj = new InventoryResponseMapper().apply(inventory);
        return new ServiceResponse(typeMessage, obj);
    }

    private Optional<Item> getItem(Integer idItem) {
        return Game.player.getInventory().getItens().stream()
                .filter(item1 -> item1.getId() == idItem)
                .findFirst();
    }
}
