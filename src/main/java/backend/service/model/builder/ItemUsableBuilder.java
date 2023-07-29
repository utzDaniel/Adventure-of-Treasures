package backend.service.model.builder;

public final class ItemUsableBuilder extends ItemBuilder {

    private final ItemUsable itemUsable;

    private ItemUsableBuilder() {
        this.itemUsable = new ItemUsable();
        super.item = this.itemUsable;
    }

    public static ItemUsableBuilder builder() {
        return new ItemUsableBuilder();
    }

    public ItemUsableBuilder localUse(String localUse) {
        this.itemUsable.setLocalUse(localUse);
        return this;
    }

    @Override
    public ItemUsable build() {
        return this.itemUsable;
    }
}
