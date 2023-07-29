package backend.service.model.builder;

import backend.service.component.equip.Equip;
import backend.service.component.equip.Unequip;
import backend.service.interfaces.IEquipable;

public final class ItemEquipable extends Item implements IEquipable {

    private boolean equipped;
    ItemEquipable() {
    }

    @Override
    public void setEquipped(boolean equipped) {
        this.equipped = equipped;
    }

    @Override
    public void equip() {
        this.equipped = new Equip(this).run();
    }

    @Override
    public void unequip() {
        this.equipped = !new Unequip(this).run();
    }

    @Override
    public boolean isEquipped() {
        return this.equipped;
    }

    @Override
    public String toString() {
        return """
                Item: %s
                {
                    "equipped": %b
                }
                """.formatted(super.toString(), this.equipped);
    }

}
