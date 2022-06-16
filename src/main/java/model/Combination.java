package model;

import repository.CreateImageMapGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Combination {

    private final Player player;
    private final List<ICombinable> itensCombinable;

    public Combination(Player player) {
        this.player = player;
        this.itensCombinable = new ArrayList<>();
    }


    public boolean execute(List<Item> itens) {
        return validItemCombinable(itens) &&
                validCombination(this.itensCombinable) &&
                validAmountCombine(this.itensCombinable) &&
                atualizarIventario(this.itensCombinable);
    }

    // Metodo deve ser retirado pois, ja deveria ter verificado se o item é do tipo model.ItemCombinable antes de chamar o metodo
    public boolean validItemCombinable(List<Item> itens) {
        for (Item item : itens) {
            if (item instanceof ICombinable) {
                this.itensCombinable.add((ICombinable) item);
            } else {
                return false;
            }
        }
        return true;
    }

    //private
    public boolean validCombination(List<ICombinable> itensCombinable) {
        for (int i = 1; i < itensCombinable.size(); i++) {
            if (itensCombinable.get(0).getCombine() != itensCombinable.get(i).getCombine()) {
                return false;
            }
        }
        return true;
    }

    //alterar metodo para realizar um outro tipo de validacao, pois esta sendo validado pela quantidade
    private boolean validAmountCombine(List<ICombinable> itensCombinable) {
        return itensCombinable.size() == ListCombination.getAmountCombine(itensCombinable.get(0).getCombine());
    }

    public Item retornarItemDeCombinacao(List<ICombinable> itensCombinable) {
        int index = itensCombinable.get(0).getCombine() - 1;
        for (Item item : this.player.getItemInvisible()) {
            if (item.getName().equals(ItemsCombination.values()[index].getLabel())) {
                return item;
            }
        }
        return null;
    }

    //private
    public boolean atualizarIventario(List<ICombinable> itensCombinable) {
        Item itemVisible = retornarItemDeCombinacao(itensCombinable);
        if (Objects.isNull(itemVisible)) {
            return false;
        }
        //alterar  no proprio item ao criar
        if (itemVisible.getName().equals("mapa")) {
            CreateImageMapGame imageMapGame = new CreateImageMapGame();
            ((ItemNotRemove) itemVisible).getMapGame().setImagemIcon(imageMapGame.selectImage(itemVisible.getName()));
        }
        removeItensCombine(itensCombinable.get(0).getCombine());
        itemViseble(itemVisible);
        updadeCapacity(itemVisible);
        return true;
    }

    private void removeItensCombine(int combine) {
        this.player.getInventory().removeItensCombine(combine);
    }

    private void itemViseble(Item item) {
        item.setVisible(true);
    }

    private void updadeCapacity(Item item) {
        this.player.getInventory().updadeInventory(item);
    }
}

enum ItemsCombination {
    MAPA("mapa"),
    ESCADA("escada"),
    TOCHA("tocha");

    private final String label;

    ItemsCombination(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}