package backend.repository.interfaces;

public interface IMapGameEntity extends IEntity {
    String name();

    String image();

    String song();

    byte[][] limits();

}
