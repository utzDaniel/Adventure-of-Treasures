package service;

import exception.ItemCombinableException;
import model.Player;
import model.builder.item.Item;
import model.enums.ItemsCombination;
import model.interfaces.ICombinable;

import java.util.List;
import java.util.Objects;

public final class Combination {

    private final Player player;
    private final List<ICombinable> itens;
    private Item newItem;

    public Combination(List<ICombinable> itensCombination) {
        this.player = Player.getInstance();
        this.itens = itensCombination;
        newItem = null;
    }

    public boolean run() {
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

    private void checkAllEqualsCombination() {
        int combine = itens.get(0).getCombine();
        var isCombine = itens.stream()
                .allMatch(item -> item.getCombine() == combine);
        if (!isCombine)
            throw new ItemCombinableException("Itens não são combináveis entre eles");
    }

    private void checkEnoughAmountCombine() {
        if (!(itens.size() == ItemsCombination.getAmountCombination(itens.get(0).getCombine())))
            throw new ItemCombinableException("Está faltando Itens para realizar a combinação!");
    }

    private void getItemCombination() {
        this.player.getInventory().getItemInvisible().stream()
                .filter(item -> item.getName().equals(
                        ItemsCombination.getItemCombined(itens.get(0).getCombine()).get().getLabel()))
                .findFirst().ifPresent(item -> newItem = item);
    }

    private void updateMap() {
        if (newItem.getName().equals("mapa")) {
            this.player.getCurrentMap().activate(newItem.getName());
        }
    }

    private void removeAllItemCombine() {
        this.itens.forEach(itensCombinable ->
                this.player.getInventory().removeItem((Item) itensCombinable));

    }

    private void setItemViseble() {
        this.newItem.setVisible(true);
    }

    private void updadeInventoryCapacity() {
        this.player.getInventory().updadeCapacity(this.newItem.getWeight());
    }
}