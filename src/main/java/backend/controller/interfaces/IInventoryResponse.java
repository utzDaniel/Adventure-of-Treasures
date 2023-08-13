package backend.controller.interfaces;

import java.util.List;
import java.util.Map;

public interface IInventoryResponse {

    Map<Integer, List<ISpecialization>> specialization();

    List<String> information();

    List<IItemDTO> itens();

}
