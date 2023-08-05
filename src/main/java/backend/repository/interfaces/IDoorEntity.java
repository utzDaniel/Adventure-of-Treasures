package backend.repository.interfaces;

public interface IDoorEntity extends IEntity {

    int idMapOri();

    int idMapDor();

    int positionX();

    int positionY();

    boolean open();

}
