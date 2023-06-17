package backend.service.dto;

import backend.controller.interfaces.ICoordinateDTO;
import backend.controller.interfaces.IItemDTO;

public record ItemDTO(String name,
                      String description,
                      String icon,
                      int weight,
                      ICoordinateDTO coordinate,
                      String effect,
                      String specialization,
                      boolean isEquipped) implements IItemDTO {


}
