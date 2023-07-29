package backend.repository.interfaces;

public interface IDoorEntity {

    int id();
    int idMapGame();
    int idMapGameExit();

    int positionX();

    int positionY();

    boolean open();

}
