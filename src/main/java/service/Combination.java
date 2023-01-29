package service;

import exception.ItemCombinableException;
import model.Player;
import model.builder.item.Item;
import model.enums.ItemsCombination;
import model.interfaces.ICombinable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Combination<T extends ICombinable> {

    private final Player player;
    private final List<T> itensCombinable;
    private final List<Item> itens;
    private Item newItem;

    public Combination(List<Item> itens) {
        this.player = Player.getInstance();
        this.itensCombinable = new ArrayList<>();
        this.itens = itens;
        newItem = null;
    }

    public boolean run() {
        checkItensICombinable();
        checkAllEqualsCombination();
        checkEnoughAmountCombine();
        getItemCombination();
        if (Objects.isNull(newItem)) throw new ItemCombinableException("Item não encontrado");
        updateMap();
        removeAllItemCombine();
        setItemViseble();
        updadeInventoryCapacity();
        return true;
    }

    private void checkItensICombinable() {
        var allICombinable = itens.stream()
                .allMatch(item -> item instanceof ICombinable);
        if(!allICombinable)
            throw new ItemCombinableException("Itens não combináveis");
        itens.forEach(item -> this.itensCombinable.add((T)item));
    }

    private void checkAllEqualsCombination() {
        int combine = itensCombinable.get(0).getCombine();
        var isCombine = itensCombinable.stream().allMatch(t -> t.getCombine() == combine);
        if(!isCombine)
            throw new ItemCombinableException("Itens não são combináveis entre eles");
    }

    private void checkEnoughAmountCombine() {
        if (!(itensCombinable.size() == ItemsCombination.getAmountCombination(itensCombinable.get(0).getCombine())))
            throw new ItemCombinableException("Está faltando Itens para realizar a combinação!");
    }

    private void getItemCombination() {
        this.player.getInventory().getItemInvisible().stream()
                .filter(item -> item.getName().equals(
                        ItemsCombination.getItemCombined(itensCombinable.get(0).getCombine()).get().getLabel()))
                .findFirst().ifPresent(item -> newItem = item);
    }

    private void updateMap() {
        if (newItem.getName().equals("mapa")) {
            this.player.getCurrentMap().activate(newItem.getName());
        }
    }

    private void removeAllItemCombine() {
        this.itensCombinable.forEach(itensCombinable ->
                this.player.getInventory().removeItem((Item) itensCombinable));

    }

    private void setItemViseble() {
        this.newItem.setVisible(true);
    }

    private void updadeInventoryCapacity() {
        this.player.getInventory().updadeCapacity(this.newItem.getWeight());
    }
}