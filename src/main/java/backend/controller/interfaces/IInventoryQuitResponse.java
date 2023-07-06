package backend.controller.interfaces;

import java.util.List;

public interface IInventoryQuitResponse extends IResponse {

    String iconMap();

    List<IItemDTO> itens();

    int indexItens();

}
