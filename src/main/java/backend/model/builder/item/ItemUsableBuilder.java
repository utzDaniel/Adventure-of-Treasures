package backend.model.builder.item;

public final class ItemUsableBuilder extends ItemBuilder {

    private final ItemUsable itemUsable;

    private ItemUsableBuilder() {
        this.itemUsable = new ItemUsable();
        super.item = this.itemUsable;
    }

    static public ItemUsableBuilder builder() {
        return new ItemUsableBuilder();
    }

    public ItemUsableBuilder localUse(String localUse) {
        this.itemUsable.setLocalUse(localUse);
        return this;
    }
    public ItemUsableBuilder effect(String filename) {
        this.itemUsable.setEffect(filename);
        return this;
    }
    @Override
    public ItemUsable build() {
        return this.itemUsable;
    }
}
