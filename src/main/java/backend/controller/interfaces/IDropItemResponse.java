package backend.controller.interfaces;

import java.util.List;

public interface IDropItemResponse extends IResponse {

    int capacity();

    int maxCapacity();

    List<IItemDTO> itens();

}
