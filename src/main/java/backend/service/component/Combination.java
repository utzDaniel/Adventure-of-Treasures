package backend.service.component;

import backend.service.exception.ItemCombinableException;
import backend.service.interfaces.ICombinable;
import backend.service.model.Player;
import backend.service.enums.ItemsCombination;
import backend.service.model.builder.Item;

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
            new ActivateMapGame().run(newItem.getName());
        }
    }

    private void removeAllItemCombine() {
        this.itens.forEach(itensCombinable -> new RemoveItem(this.player.getInventory(), (Item) itensCombinable).run());

    }

    private void setItemViseble() {
        this.newItem.setVisible(true);
    }

    private void updadeInventoryCapacity() {
        this.player.getInventory().updadeCapacity(this.newItem.getWeight());
    }
}