package backend.model.builder.item;

import rules.util.CombinableUtil;

public final class ItemCombinableBuilder extends ItemBuilder {

    private final ItemCombinable itemCombinable;

    private ItemCombinableBuilder() {
        this.itemCombinable = new ItemCombinable();
        super.item = this.itemCombinable;
    }

    static public ItemCombinableBuilder builder() {
        return new ItemCombinableBuilder();
    }

    public ItemCombinableBuilder combine(int combine) {
        this.itemCombinable.setUtilCombinable(new CombinableUtil(combine));
        return this;
    }

    public ItemCombinableBuilder effect(String filename) {
        this.itemCombinable.setEffect(filename);
        return this;
    }

    @Override
    public ItemCombinable build() {
        return this.itemCombinable;
    }
}
