package backend.service.interfaces;

public interface IEventMap extends ISpecialization {

    int getIdMap();

    int getIdDoor();

    boolean isActive();

    void setActive(boolean active);
}
