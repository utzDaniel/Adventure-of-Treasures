package backend.service.model.builder;

import backend.service.interfaces.ICombinable;

public final class ItemCombinable extends Item implements ICombinable {

    private int combine;

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
    public String toString() {
        return """
                Item: %s
                {
                    "combine": %d
                }
                """.formatted(super.toString(), this.combine);
    }
}
