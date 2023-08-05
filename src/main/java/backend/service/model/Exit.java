package backend.service.model;

import backend.service.enums.Direction;

public record Exit(Direction direction,
                   int idMap) {
    @Override
    public String toString() {
        return """
                {
                    "direction": %s,
                    "idMap": %s
                }
                """.formatted(this.direction, this.idMap);
    }
}
