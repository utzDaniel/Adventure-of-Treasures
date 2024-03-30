package backend.service.memento;

public record DoorMemento(
        int id,
        boolean open) implements IMemento, Comparable<DoorMemento> {

    @Override
    public String extrinsic() {
        return """
                %d%s
                %b%s
                """.formatted(this.id, Separator.FIELD,
                        this.open, Separator.DOOR)
                .replace("\n", "");

    }

    @Override
    public int compareTo(DoorMemento o) {
        return Integer.compare(this.id, o.id);
    }
}
