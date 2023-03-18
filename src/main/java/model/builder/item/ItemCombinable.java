package model.builder.item;

import model.interfaces.ICombinable;
import util.UtilCombinable;

import java.util.List;

public class ItemCombinable extends Item implements ICombinable {

    private UtilCombinable utilCombinable;
    private String effect;

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
    public String getEffect() {
        return this.effect;
    }
    protected void setEffect(String filename) {
        this.effect = filename;
    }

    @Override
    public boolean action() {
        return false;
    }

    @Override
    public boolean action(List<ICombinable> itensCombination) {
        return this.combination(itensCombination);
    }

    @Override
    public String toString() {
        return "ItemCombinable{" +
                "utilCombinable=" + this.utilCombinable.getCombine() +
                ", effect='" + this.effect +"} "+ super.toString();
    }
}
