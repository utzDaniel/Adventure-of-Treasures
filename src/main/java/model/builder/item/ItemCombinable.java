package model.builder.item;

import model.interfaces.ICombinable;
import util.UtilCombinable;

import java.util.List;

public class ItemCombinable extends Item implements ICombinable {

    private UtilCombinable utilCombinable;

    protected ItemCombinable() {
    }

    protected void setUtilCombinable(UtilCombinable utilCombinable) {
        this.utilCombinable = utilCombinable;
    }

    @Override
    public int getCombine() {
        return this.utilCombinable.getCombine();
    }

    @Override
    public boolean action(Item item) {
        return false;
    }

    @Override
    public boolean action(List<Item> itens) {
        return this.combination(itens);
    }

    @Override
    public String toString() {
        return "ItemCombinable{" +
                "utilCombinable=" + utilCombinable.getCombine() +
                "} " + super.toString();
    }
}
