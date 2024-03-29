package backend.service.memento;

public record ItemMemento(
        int id,
        int x,
        int y,
        SpecializationCompositeMemento specializationCompositeMemento) implements IMemento {

    @Override
    public String extrinsic() {
        return """
                %d;
                %d;
                %d;
                %sitem;
                """.formatted(this.id, this.x, this.y, this.specializationCompositeMemento.extrinsic())
                .replace("\n", "");
    }
}
