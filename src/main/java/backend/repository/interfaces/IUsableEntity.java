package backend.repository.interfaces;

public interface IUsableEntity extends IEntity {

    int idItem();

    int idMap();

    int positionX();

    int positionY();

    boolean enabled();

}
