package backend.service.component.combination;

import backend.controller.enums.TypeMessage;
import backend.service.component.ActivateMapGame;
import backend.service.component.RemoveItem;
import backend.service.enums.ItemsCombination;
import backend.service.interfaces.ICombinable;
import backend.service.model.Inventory;
import backend.service.model.builder.Item;

import java.util.List;
import java.util.Objects;

public final class Combination {

    private final Inventory inventory;
    private final List<ICombinable> itens;
    private Item newItem;

    public Combination(List<ICombinable> itensCombination, Inventory inventory) {
        this.inventory = inventory;
        this.itens = itensCombination;
        newItem = null;
    }

    public TypeMessage run() {
        TypeMessage typeMessage = null;

        typeMessage = checkAllEqualsCombination();
        if (Objects.nonNull(typeMessage)) return typeMessage;

        typeMessage = checkEnoughAmountCombine();
        if (Objects.nonNull(typeMessage)) return typeMessage;

        var itemCombination = getItemCombination();
        newItem = getItem(itemCombination);
        if (Objects.isNull(newItem)) return TypeMessage.ITEM_NOT_FOUND;

        updateMap();
        removeAllItemCombine();
        setItemViseble();
        updadeInventoryCapacity();
        return itemCombination.getTypeMessage();
    }

    private TypeMessage checkAllEqualsCombination() {
        int combine = itens.get(0).getCombine();
        var isCombine = itens.stream()
                .allMatch(item -> item.getCombine() == combine);
        if (!isCombine)
            return TypeMessage.COMBINE_NOT_COMBINABLE;
        return null;
    }

    private TypeMessage checkEnoughAmountCombine() {
        if (itens.size() == ItemsCombination.getAmountCombination(itens.get(0).getCombine()))
            return null;
        return TypeMessage.COMBINE_INCOMPLETE;
    }

    private ItemsCombination getItemCombination() {
        return ItemsCombination.getItemCombined(itens.get(0).getCombine());
    }


    private Item getItem(ItemsCombination itemsCombination) {
        return this.inventory.getItemInvisible().stream()
                .filter(item -> item.getName().equals(itemsCombination.getLabel()))
                .findFirst().orElse(null);
    }

    private void updateMap() {
        if (newItem.getName().equals("mapa")) {
            new ActivateMapGame().run(newItem.getName());
        }
    }

    private void removeAllItemCombine() {
        this.itens.forEach(itensCombinable -> new RemoveItem(this.inventory, (Item) itensCombinable).run());

    }

    private void setItemViseble() {
        this.newItem.setVisible(true);
    }

    private void updadeInventoryCapacity() {
        this.inventory.updadeCapacity(this.newItem.getWeight());
    }
}