package backend.repository.interfaces;

public interface IMapGameEntity {

    int id();
    String name();

    String imagemIcon();

    String song();

    int[][] limits();

}
