package backend.controller.interfaces;

import java.util.List;

public interface IInventoryResponse {

    int capacity();

    int maxCapacity();

    List<IItemDTO> itens();

}
