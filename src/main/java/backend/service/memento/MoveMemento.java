package backend.service.memento;

public record MoveMemento(
        String movementImage,
        int idMapGame,
        String direction,
        int x,
        int y,
        String path) implements IMemento {

    @Override
    public String extrinsic() {
        return """
                %s;
                %d;
                %s;
                %d;
                %d;
                %s;
                """.formatted(this.movementImage, this.idMapGame, this.direction, this.x, this.y, this.path)
                .replace("\n", "");
    }
}
