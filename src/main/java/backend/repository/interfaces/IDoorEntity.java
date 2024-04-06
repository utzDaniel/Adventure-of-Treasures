package backend.repository.interfaces;

public interface IDoorEntity extends IEntity {

    int idMapInside();

    int insideX();

    int insideY();

    int idMapOutside();

    int outsideX();

    int outsideY();

    boolean open();

}
