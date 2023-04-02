package backend.model.builder.item;

public class ItemEquipableBuilder extends ItemBuilder {

    private final ItemEquipable itemEquipable;

    private ItemEquipableBuilder() {
        this.itemEquipable = new ItemEquipable();
        super.item = this.itemEquipable;
    }

    static public ItemEquipableBuilder builder() {
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
