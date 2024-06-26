package backend.service.memento;

import backend.service.interfaces.IFlyweight;

public record MoveMemento(
        String movementImage,
        int idMapGame,
        String direction,
        int x,
        int y,
        String path) implements IFlyweight {

    @Override
    public String extrinsic() {
        return """
                %s%s
                %d%s
                %s%s
                %d%s
                %d%s
                %s
                """.formatted(this.movementImage, Separator.FIELD,
                        this.idMapGame, Separator.FIELD,
                        this.direction, Separator.FIELD,
                        this.x, Separator.FIELD,
                        this.y, Separator.FIELD,
                        this.path)
                .replace("\n", "");
    }
}
