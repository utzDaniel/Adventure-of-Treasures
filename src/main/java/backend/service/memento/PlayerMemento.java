package backend.service.memento;

public record PlayerMemento(
        MoveMemento move,
        InventoryMemento inventory) implements IMemento {

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
