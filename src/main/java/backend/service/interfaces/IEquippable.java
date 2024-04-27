package backend.service.interfaces;

public interface IEquippable extends ISpecialization {

    int id();

    boolean isEquip();

    void setEquip(boolean equip);

    int getUpCapacity();
}
