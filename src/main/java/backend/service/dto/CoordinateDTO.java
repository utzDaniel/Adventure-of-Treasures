package backend.service.dto;

import backend.controller.interfaces.ICoordinateDTO;

public record CoordinateDTO(
        int x,
        int y
) implements ICoordinateDTO {
}
