package backend.service.interfaces;

public interface IEquipable extends IEffect {
    boolean equip();

    boolean unequip();

    boolean isEquipped();

    String getName();

    String getEffect();
}
