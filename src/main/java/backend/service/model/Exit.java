package backend.service.model;

import backend.service.enums.Move;

public record Exit(Move move,
                   int idMapGame) {
    @Override
    public String toString() {
        return """
                {
                    "move": %s,
                    "idMapGame": %s
                }
                """.formatted(this.move, this.idMapGame);
    }
}
