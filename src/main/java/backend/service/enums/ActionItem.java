package backend.service.enums;

public enum ActionItem {
    COMBINE("COMBINAR", null),
    USE("USAR", null),
    EQUIP("EQUIPAR", Commands.EQUIPAR, Commands.EVENTO),
    UNEQUIP("DESEQUIPAR", Commands.EQUIPAR, Commands.EVENTO),
    REMOVE("REMOVER", null);

    private final String name;
    private final Commands[] commands;

    ActionItem(String name, Commands... commands) {
        this.name = name;
        this.commands = commands;
    }

    public String getName() {
        return this.name;
    }

    public Commands[] getCommands() {
        return this.commands;
    }
}
