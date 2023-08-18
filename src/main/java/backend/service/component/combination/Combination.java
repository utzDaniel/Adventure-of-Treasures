package backend.service.component.combination;

import backend.controller.enums.TypeMessage;
import backend.repository.singleton.CombinableRepository;
import backend.repository.singleton.ItemRepository;
import backend.service.component.ActivateMapGame;
import backend.service.component.RemoveItem;
import backend.service.enums.ItemsCombination;
import backend.service.model.Inventory;
import backend.service.model.Item;
import backend.service.model.ItemFactory;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

public final class Combination {

    private final Inventory inventory;
    private final List<Item> itens;
    private Item newItem;

    public Combination(List<Item> itens, Inventory inventory) {
        this.inventory = inventory;
        this.itens = itens;
        newItem = null;
    }

    public TypeMessage run() {

        var listCombinable = CombinableRepository.getInstance().getAll();
        var combinable = listCombinable.stream()
                .filter(v -> v.idItemOri() == itens.get(0).getId())
                .findFirst().orElse(null);

        var listNewCombinable = listCombinable.stream()
                .filter(v -> v.idItemNew() == combinable.idItemNew())
                .toList();

        if (itens.size() != listNewCombinable.size())
            return TypeMessage.COMBINE_INCOMPLETE;

        var isCombine = itens.stream()
                .allMatch(item ->
                        listNewCombinable.stream().anyMatch(v -> v.idItemOri() == item.getId())
                );
        if (!isCombine)
            return TypeMessage.COMBINE_NOT_COMBINABLE;

        newItem = getItem(listNewCombinable.get(0).idItemNew());
        if (Objects.isNull(newItem)) return TypeMessage.ITEM_NOT_FOUND;

        updateMap();
        removeAllItemCombine();
        this.inventory.addItem(newItem);
        var nameUp = newItem.getName().toUpperCase(Locale.ROOT);

        var e = Enum.valueOf(ItemsCombination.class, nameUp);
        return e.getTypeMessage();
    }


    private Item getItem(int idItem) {
        return ItemFactory.create(ItemRepository.getInstance().getById(idItem));
    }

    private void updateMap() {
        if (newItem.getName().equals("mapa")) {
            new ActivateMapGame().run(newItem.getName());
        }
    }

    private void removeAllItemCombine() {
        this.itens.forEach(item -> new RemoveItem(this.inventory, item).run());

    }
}