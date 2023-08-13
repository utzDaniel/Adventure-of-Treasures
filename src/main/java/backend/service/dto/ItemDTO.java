package backend.service.dto;

import backend.controller.interfaces.IItemDTO;

public record ItemDTO(int id,
                      String name,
                      int weight,
                      String description,
                      String image,
                      boolean combinable) implements IItemDTO {

}
