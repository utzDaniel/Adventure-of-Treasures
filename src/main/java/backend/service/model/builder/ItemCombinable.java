package backend.service.model.builder;

import backend.service.interfaces.ICombinable;

import java.util.List;

public final class ItemCombinable extends Item implements ICombinable {

    private int combine;
    private String effect;

    ItemCombinable() {
    }

    void setCombine(int combine) {
        this.combine = combine;
    }

    @Override
    public int getCombine() {
        return this.combine;
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
        return """
                Item: %s
                {
                    "combine": %d,
                    "effect": "%s"
                }
                """.formatted(super.toString(), this.combine, this.effect);
    }
}
