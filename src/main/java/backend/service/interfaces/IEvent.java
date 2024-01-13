package backend.service.interfaces;

public interface IEvent extends ISpecialization {

    int getIdMap();

    int getIdDoor();

    boolean isActive();

    void setActive(boolean active);
}
