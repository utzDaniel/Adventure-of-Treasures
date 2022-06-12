package model;

public final class UtilCombinable  {

    private final int combine;

    UtilCombinable(ICombinable iCombinable, int combine) {
        this.combine = combine;
        ListCombination.setAmountCombine(this);
    }

    public int getCombine() {
        return this.combine;
    }
}
