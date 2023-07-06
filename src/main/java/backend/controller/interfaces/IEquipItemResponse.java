package backend.controller.interfaces;

import java.util.List;

public interface IEquipItemResponse extends IResponse {

    int capacity();

    int maxCapacity();

    List<IItemDTO> itens();

    int indexItem();

}
