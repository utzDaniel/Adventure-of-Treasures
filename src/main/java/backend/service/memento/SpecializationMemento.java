package backend.service.memento;

import backend.service.interfaces.IFlyweight;

public record SpecializationMemento(
        Boolean equip,
        Boolean enabled) implements IFlyweight {

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
