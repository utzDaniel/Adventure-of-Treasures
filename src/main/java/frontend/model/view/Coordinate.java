package frontend.model.view;

import backend.controller.interfaces.ICoordinateDTO;

public record Coordinate(int x, int y) implements ICoordinateDTO {

}
