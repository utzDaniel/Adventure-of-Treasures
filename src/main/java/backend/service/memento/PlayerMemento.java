package backend.service.memento;

public record PlayerMemento(
        MoveMemento move,
        InventoryMemento inventory) implements IMemento {

    @Override
    public String extrinsic() {
        return """
                %s
                %splayer;
                """.formatted(this.move.extrinsic(), this.inventory.extrinsic())
                .replace("\n", "");
    }
}
