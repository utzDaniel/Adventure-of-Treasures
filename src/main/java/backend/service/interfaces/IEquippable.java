package backend.service.interfaces;

public interface IEquippable extends ISpecialization {

    boolean isEquip();

    void setEquip(boolean equip);

    int getUpCapacity();
}
