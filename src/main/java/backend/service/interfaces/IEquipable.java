package backend.service.interfaces;

public interface IEquipable extends IEffect {
    void equip();

    void unequip();

    void setEquipped(boolean equipped);

    boolean isEquipped();

    String getName();

    String getEffect();
}
