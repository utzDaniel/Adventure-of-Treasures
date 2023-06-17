package backend.controller.interfaces;

import java.util.List;

public interface IInventoryResponse {

    boolean isOpen();
    int capacity();
    int maxCapacity();

    List<IItemDTO> itens();

}
