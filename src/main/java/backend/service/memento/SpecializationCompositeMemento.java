package backend.service.memento;

public record SpecializationCompositeMemento(
        Boolean equip,
        Boolean enabled) implements IMemento {

    @Override
    public String extrinsic() {
        return """
                %b;
                %b;
                """.formatted(this.equip, this.enabled).replace("\n", "");
    }
}
