package backend.repository.interfaces;

public interface IItemEntity {

    int mapGameKey();
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

    String effectsCombine();

    String effectsEquipped();

    String effectsUse();

}
