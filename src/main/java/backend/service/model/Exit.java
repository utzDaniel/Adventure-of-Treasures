package backend.service.model;

import backend.service.enums.Move;

public record Exit(Move move,
                   String mapGame) {
    @Override
    public String toString() {
        return """
                {
                    "move": %s,
                    "mapGame": %s
                }
                """.formatted(this.move, this.mapGame);
    }
}
