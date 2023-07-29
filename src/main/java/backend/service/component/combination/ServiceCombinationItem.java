package backend.service.component.combination;

import backend.controller.enums.TypeMessage;
import backend.service.interfaces.ICombinable;
import backend.service.model.Inventory;
import backend.service.model.builder.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public final class ServiceCombinationItem {
    private final Inventory inventory;

    public ServiceCombinationItem(Inventory inventory) {
        this.inventory = inventory;
    }

    public TypeMessage run(String... names) {

        var itens = new ArrayList<Item>();
        Arrays.stream(names)
                .forEach(name1 -> itens.add(
                        this.inventory.getItemVisible().stream()
                                .filter(item1 -> item1.getName().equals(name1))
                                .findFirst().get()));

        if (itens.isEmpty()) return TypeMessage.ITEM_NOT_FOUND;

        var itensCombination = new ArrayList<ICombinable>();
        TypeMessage typeMessage = null;

        for (Item item : itens) {
            if (item instanceof ICombinable combinable) {
                itensCombination.add(combinable);
            } else {
                typeMessage = TypeMessage.COMBINE_NOT_ALL;
                break;
            }
        }

        if (Objects.nonNull(typeMessage)) return typeMessage;

        typeMessage = new Combination(itensCombination, this.inventory).run();

        return typeMessage;
    }

}
