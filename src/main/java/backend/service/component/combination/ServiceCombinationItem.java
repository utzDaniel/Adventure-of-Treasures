package backend.service.component.combination;

import backend.controller.interfaces.IItemDTO;
import backend.controller.interfaces.IResponse;
import backend.service.dto.response.InventoryResponse;
import backend.service.exception.ItemCombinableException;
import backend.service.factory.MessageFactory;
import backend.service.interfaces.ICombinable;
import backend.service.mapper.ItemDTOMapper;
import backend.service.model.Player;
import backend.service.model.builder.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ServiceCombinationItem {

    private final Player player = Player.getInstance();

    public IResponse run(String... names) {

        var itens = new ArrayList<Item>();
        Arrays.stream(names).forEach(name1 -> itens.add(player.getInventory().getItemVisible().stream()
                .filter(item1 -> item1.getName().equals(name1)).findFirst().get()));


        var exeption = isExeption(itens);
        var message = new MessageFactory().create(exeption);

        if (message.sucess()) {
            message = new MessageFactory().create("Item combinados!", ((ICombinable) itens.get(0)).getEffect());
        }

        var capacity = player.getInventory().getCapacity();
        var maxCapacity = player.getInventory().getMaxCapacity();
        var itensDto = new ArrayList<IItemDTO>(player.getInventory().getItemVisible().stream()
                .map(item -> new ItemDTOMapper().apply(item))
                .toList());

        return new InventoryResponse(message, capacity, maxCapacity, itensDto);
    }

    private Exception isExeption(List<Item> itens) {
        try {
            var itensCombination = new ArrayList<ICombinable>();
            itens.forEach(item -> {
                if (item instanceof ICombinable combinable) {
                    itensCombination.add(combinable);
                } else {
                    throw new ItemCombinableException("Algum dos itens não é combinavel!");
                }
            });
            itensCombination.get(0).combination(itensCombination);
        } catch (Exception e) {
            return e;
        }
        return null;
    }
}
