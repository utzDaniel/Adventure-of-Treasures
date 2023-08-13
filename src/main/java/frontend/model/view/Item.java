package frontend.model.view;

import backend.controller.interfaces.IItemDTO;

public record Item(int id,
                   String name,
                   int weight,
                   String description,
                   String image,
                   boolean combinable) implements IItemDTO {

}
