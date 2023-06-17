package backend.service.model;

public record Exit(String direction,
                   String mapGame) {
    @Override
    public String toString() {
        return """
                {
                    "direction": %s,
                    "mapGame": %s
                }
                """.formatted(this.direction, this.mapGame);
    }
}
