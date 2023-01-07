package model;

import model.interfaces.ICombinable;
import service.util.UtilCombinable;

import javax.swing.*;
import java.util.List;

public class ItemCombinable extends Item implements ICombinable {

    private final UtilCombinable utilCombinable;

    public ItemCombinable(String name, String description, int weight, int combine, int positionItemX, int positionItemY, ImageIcon imagemIcon) {
        super(name, description, weight, positionItemX, positionItemY,imagemIcon);
        this.utilCombinable = new UtilCombinable(combine);
    }

    @Override
    public int getCombine() {
        return this.utilCombinable.getCombine();
    }

    @Override
    public boolean action(Item item, Player player) {
        return false;
    }

    @Override
    public boolean action(List<Item> itens, Player player) {
        return this.combination(itens, player);
    }
}
