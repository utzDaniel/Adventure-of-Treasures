package backend.repository.interfaces;

public interface IDoorEntity {

    int id();
    int idMapGame();
    String mapGame();

    int positionX();

    int positionY();

    boolean open();

}
