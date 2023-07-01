package backend.service.component.combination;

import backend.controller.interfaces.ICombinationItemRequest;
import backend.controller.interfaces.IItemDTO;
import backend.controller.interfaces.IRequest;
import backend.controller.interfaces.IResponse;
import backend.service.dto.response.CombinationItemResponse;
import backend.service.exception.ItemCombinableException;
import backend.service.factory.MessageFactory;
import backend.service.interfaces.ICombinable;
import backend.service.mapper.ItemDTOMapper;
import backend.service.model.Player;
import backend.service.model.builder.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class ServiceCombinationItem {

    private final Player player = Player.getInstance();

    public IResponse run(IRequest request) {
        var combinationItemRequest = (ICombinationItemRequest) request;
        var name = combinationItemRequest.name();

        var itens = new ArrayList<Item>();
        name.forEach(name1 ->itens.add(player.getInventory().getItemVisible().stream()
                    .filter(item1 -> item1.getName().equals(name1)).findFirst().get()));


        var exeption = isExeption(itens);
        var message = new MessageFactory().create(exeption);

        if (message.sucess()) {
            message = new MessageFactory().create("Item combinados!", itens.get(0).getEffect());
        }

        var capacity = player.getInventory().getCapacity();
        var maxCapacity = player.getInventory().getMaxCapacity();
        var indexItem = 1;
        var itensDto = new ArrayList<IItemDTO>(player.getInventory().getItemVisible().stream()
                .map(item -> new ItemDTOMapper().apply(item))
                .collect(Collectors.toList()));
        var iconMap = player.getCurrentMap().getIcon().toString();

        return new CombinationItemResponse(message, capacity, maxCapacity, itensDto, indexItem, iconMap);
    }

    private Exception isExeption(List<Item> itens) {
        try {
            var itensCombination = new ArrayList<ICombinable>();
            itens.forEach(item -> {
                if (item instanceof ICombinable combinable) {
                    itensCombination.add(combinable);
                }else{
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
