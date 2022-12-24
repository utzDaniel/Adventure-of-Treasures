package service;

import exception.ItemCombinableException;
import model.*;
import model.enums.ItemsCombination;
import model.interfaces.ICombinable;
import repository.CreateImageMapGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Combination<T extends ICombinable> {

    private final Player player;
    private final List<T> itensCombinable;
    private final List<Item> itens;
    private Item newItem;

    public Combination(Player player, List<Item> itens) {
        this.player = player;
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
        for (Item item : itens) {
            if (item instanceof ICombinable) {
                this.itensCombinable.add((T) item);
            } else throw new ItemCombinableException("Itens não combináveis");
        }
    }

    private void checkAllEqualsCombination() {
        for (int i = 1; i < itensCombinable.size(); i++)
            if (itensCombinable.get(0).getCombine() != itensCombinable.get(i).getCombine())
                throw new ItemCombinableException("Itens não são combináveis entre eles");

    }

    private void checkEnoughAmountCombine() {
        if (!(itensCombinable.size() == ItemsCombination.getAmountCombination(itensCombinable.get(0).getCombine())))
            throw new ItemCombinableException("Está faltando Itens para realizar a combinação!");
    }

    private void getItemCombination() {
        for (Item item : this.player.getInventory().getItemInvisible())
            if (item.getName().equals(Objects.requireNonNull(ItemsCombination
                    .getItemCombined(itensCombinable.get(0).getCombine())).getLabel()))
                newItem = item;
    }

    private void updateMap() {
        if (newItem.getName().equals("mapa")) {
            CreateImageMapGame imageMapGame = new CreateImageMapGame();
            ((ItemNotRemove) newItem).getMapGame().setImagemIcon(imageMapGame.selectImage(newItem.getName()));
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