package backend.service.model.builder;

public final class ItemCombinableBuilder extends ItemBuilder {

    private final ItemCombinable itemCombinable;

    private ItemCombinableBuilder() {
        this.itemCombinable = new ItemCombinable();
        super.item = this.itemCombinable;
    }

    public static ItemCombinableBuilder builder() {
        return new ItemCombinableBuilder();
    }

    public ItemCombinableBuilder combine(int combine) {
        this.itemCombinable.setCombine(combine);
        return this;
    }

    @Override
    public ItemCombinable build() {
        return this.itemCombinable;
    }
}
