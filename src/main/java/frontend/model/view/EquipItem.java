package frontend.model.view;

import backend.controller.interfaces.IEquipItemResponse;
import backend.controller.interfaces.IItemDTO;
import backend.controller.interfaces.IMessage;

import java.util.List;

public record EquipItem(IMessage message,
                        int capacity,

                        int maxCapacity,
                        List<IItemDTO> itens) implements IEquipItemResponse {


}
