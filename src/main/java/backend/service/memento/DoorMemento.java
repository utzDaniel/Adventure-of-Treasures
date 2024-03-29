package backend.service.memento;

public record DoorMemento(
        int id,
        boolean open) implements IMemento {

    @Override
    public String extrinsic() {
        return """
                %d;
                %b;door;
                """.formatted(this.id, this.open).replace("\n", "");

    }
}
