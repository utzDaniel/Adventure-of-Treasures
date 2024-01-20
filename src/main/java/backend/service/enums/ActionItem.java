package backend.service.enums;

public enum ActionItem {
    COMBINE("COMBINAR", null),
    USE("USAR", Commands.USE, Commands.EVENT_MAP, Commands.EVENT_INVENTORY, Commands.REMOVE_INVENTORY),
    EQUIP("EQUIPAR", Commands.EQUIP, Commands.EVENT_MAP),
    UNEQUIP("DESEQUIPAR", Commands.EQUIP, Commands.EVENT_MAP),
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
