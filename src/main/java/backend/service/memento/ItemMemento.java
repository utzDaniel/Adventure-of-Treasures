package backend.service.memento;

public record ItemMemento(
        int id,
        int x,
        int y,
        SpecializationCompositeMemento specializationCompositeMemento) implements IMemento, Comparable<ItemMemento> {

    @Override
    public String extrinsic() {
        return """
                %d%s
                %d%s
                %d%s
                %s%s
                """.formatted(this.id, Separator.FIELD,
                        this.x, Separator.FIELD,
                        this.y, Separator.FIELD,
                        this.specializationCompositeMemento.extrinsic(), Separator.ITEM)
                .replace("\n", "");
    }

    @Override
    public int compareTo(ItemMemento o) {
        return Integer.compare(this.id, o.id);
    }
}
