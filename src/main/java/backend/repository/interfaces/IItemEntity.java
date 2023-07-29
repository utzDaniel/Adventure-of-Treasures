package backend.repository.interfaces;

public interface IItemEntity {

    int id();
    int idMapGame();
    String name();

    String description();

    int weight();

    int positionX();

    int positionY();

    String imagemIcon();

    int combine();

    boolean equipped();

    String mapGame();

    String localUse();

    boolean remove();

    boolean visible();

}
