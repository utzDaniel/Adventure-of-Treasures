package backend.service.model.builder;

import backend.service.interfaces.ICombinable;

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
