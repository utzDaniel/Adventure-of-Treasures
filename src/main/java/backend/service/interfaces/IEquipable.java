package backend.service.interfaces;

public interface IEquipable {
    void equip();

    void unequip();

    void setEquipped(boolean equipped);

    boolean isEquipped();

    String getName();
}
