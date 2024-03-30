package backend.service.memento;

public record SpecializationCompositeMemento(
        Boolean equip,
        Boolean enabled) implements IMemento {

    @Override
    public String extrinsic() {
        return """
                %b%s
                %b
                """.formatted(this.equip, Separator.FIELD,
                        this.enabled)
                .replace("\n", "");
    }
}
