package backend.repository.interfaces;

public interface IItemEntity extends IEntity {

    String name();

    String description();

    int weight();

    int positionX();

    int positionY();

    String image();

}
