package backend.service.dto.response;

import backend.controller.interfaces.IInventoryResponse;
import backend.controller.interfaces.IItemDTO;
import backend.controller.interfaces.ISpecialization;

import java.util.List;
import java.util.Map;

public record InventoryResponse(Map<Integer, List<ISpecialization>> specialization,
                                List<String> information,
                                List<IItemDTO> itens) implements IInventoryResponse {


}
