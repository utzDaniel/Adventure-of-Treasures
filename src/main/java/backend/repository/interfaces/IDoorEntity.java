package backend.repository.interfaces;

public interface IDoorEntity {

    int mapGameKey();
    String mapGame();

    int positionX();

    int positionY();

    boolean open();

}
