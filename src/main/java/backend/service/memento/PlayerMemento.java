package backend.service.memento;

import backend.service.interfaces.IFlyweight;

public record PlayerMemento(
        MoveMemento move,
        InventoryMemento inventory) implements IFlyweight {

    @Override
    public String extrinsic() {
        return """
                %s%s
                %s%s
                """.formatted(this.move.extrinsic(), Separator.FIELD,
                        this.inventory.extrinsic(), Separator.PLAYER)
                .replace("\n", "");
    }
}
