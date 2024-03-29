package backend.service.memento;

public record DoorMemento(
        int id,
        boolean open) implements IMemento, Comparable<DoorMemento> {

    @Override
    public String extrinsic() {
        return """
                %d;
                %b;door;
                """.formatted(this.id, this.open).replace("\n", "");

    }

    @Override
    public int compareTo(DoorMemento o) {
        return Integer.compare(this.id, o.id);
    }
}
