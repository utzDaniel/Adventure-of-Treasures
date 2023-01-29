package model.builder.item;

import util.UtilCombinable;

public class ItemCombinableBuilder extends ItemBuilder {

    private final ItemCombinable itemCombinable;

    private ItemCombinableBuilder() {
        this.itemCombinable = new ItemCombinable();
        super.item = this.itemCombinable;
    }

    static public ItemCombinableBuilder builder() {
        return new ItemCombinableBuilder();
    }

    public ItemCombinableBuilder combine(int combine) {
        this.itemCombinable.setUtilCombinable(new UtilCombinable(combine));
        return this;
    }

    @Override
    public ItemCombinable build() {
        return this.itemCombinable;
    }
}
