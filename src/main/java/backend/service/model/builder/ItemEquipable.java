package backend.service.model.builder;

import backend.service.component.Equip;
import backend.service.component.Unequip;
import backend.service.interfaces.ICombinable;
import backend.service.interfaces.IEquipable;

import java.util.List;

public final class ItemEquipable extends Item implements IEquipable {

    private boolean equipped;
    private String effect;

    ItemEquipable() {
    }

    void setEquipped(boolean equipped) {
        this.equipped = equipped;
    }

    @Override
    public boolean equip() {
        this.equipped = new Equip(this).run();
        return this.equipped;
    }

    @Override
    public boolean unequip() {
        this.equipped = !new Unequip(this).run();
        return !this.equipped;
    }

    @Override
    public boolean isEquipped() {
        return this.equipped;
    }

    @Override
    public boolean action() {
        return this.isEquipped() ?
                this.unequip() : this.equip();
    }

    @Override
    public String getEffect() {
        return this.effect;
    }

    void setEffect(String filename) {
        this.effect = filename;
    }

    @Override
    public boolean action(List<ICombinable> itens) {
        return action();
    }

    @Override
    public String toString() {
        return """
                Item: %s
                {
                    "equipped": %b,
                    "effect": "%s"
                }
                """.formatted(super.toString(), this.equipped, this.effect);
    }

}
