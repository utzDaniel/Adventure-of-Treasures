package backend.controller.interfaces;

import java.util.List;

public interface IInventoryResponse extends IResponse {

    int capacity();

    int maxCapacity();

    List<IItemDTO> itens();

}