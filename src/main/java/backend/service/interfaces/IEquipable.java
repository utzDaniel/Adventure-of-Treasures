package backend.service.interfaces;

public interface IEquipable extends IEffect {
    void equip();

    void unequip();

    boolean isEquipped();

    String getName();

    String getEffect();
}
