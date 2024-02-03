package backend.service.enums;

public enum ActionItem {
    COMBINE("COMBINAR"),
    USE("USAR"),
    EQUIP("EQUIPAR"),
    UNEQUIP("DESEQUIPAR"),
    REMOVE("REMOVER");

    private final String name;

    ActionItem(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
