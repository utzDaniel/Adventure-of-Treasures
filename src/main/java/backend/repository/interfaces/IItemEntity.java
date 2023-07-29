package backend.repository.interfaces;

import backend.service.enums.TypeItem;

import java.util.List;

public interface IItemEntity {

    int id();
    int idMapGame();
    String name();

    String description();

    int weight();

    int positionX();

    int positionY();

    String imagemIcon();

    List<TypeItem> type();

    boolean visible();

}
