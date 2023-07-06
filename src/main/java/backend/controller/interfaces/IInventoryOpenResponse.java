package backend.controller.interfaces;

import java.util.List;

public interface IInventoryOpenResponse extends IResponse {

    int capacity();

    int maxCapacity();

    List<IItemDTO> itens();

}
