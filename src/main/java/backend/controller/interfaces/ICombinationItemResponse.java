package backend.controller.interfaces;

import java.util.List;

public interface ICombinationItemResponse extends IResponse {

    int capacity();

    int maxCapacity();

    List<IItemDTO> itens();

    int indexItem();

    String iconMap();

}
