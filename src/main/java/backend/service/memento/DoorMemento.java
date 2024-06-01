package backend.service.memento;

import backend.service.interfaces.IFlyweight;

public record DoorMemento(
        int id,
        boolean open) implements IFlyweight, Comparable<DoorMemento> {

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
