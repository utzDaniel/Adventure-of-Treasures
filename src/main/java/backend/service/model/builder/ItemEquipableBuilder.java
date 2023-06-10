package backend.service.model.builder;

public final class ItemEquipableBuilder extends ItemBuilder {

    private final ItemEquipable itemEquipable;

    private ItemEquipableBuilder() {
        this.itemEquipable = new ItemEquipable();
        super.item = this.itemEquipable;
    }

    public static ItemEquipableBuilder builder() {
        return new ItemEquipableBuilder();
    }

    public ItemEquipableBuilder equipped(boolean equipped) {
        this.itemEquipable.setEquipped(equipped);
        return this;
    }
    public ItemEquipableBuilder effect(String filename) {
        this.itemEquipable.setEffect(filename);
        return this;
    }
    @Override
    public ItemEquipable build() {
        return this.itemEquipable;
    }
}
