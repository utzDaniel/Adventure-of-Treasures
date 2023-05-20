package backend.model.builder.item;

import backend.model.interfaces.ICombinable;
import rules.util.CombinableUtil;

import java.util.List;

public final class ItemCombinable extends Item implements ICombinable {

    private CombinableUtil combinableUtil;
    private String effect;

    ItemCombinable() {
    }

    void setUtilCombinable(CombinableUtil combinableUtil) {
        this.combinableUtil = combinableUtil;
    }

    @Override
    public int getCombine() {
        return this.combinableUtil.getCombine();
    }
    @Override
    public String getEffect() {
        return this.effect;
    }
    void setEffect(String filename) {
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
                "utilCombinable=" + this.combinableUtil.getCombine() +
                ", effect='" + this.effect +"} "+ super.toString();
    }
}
