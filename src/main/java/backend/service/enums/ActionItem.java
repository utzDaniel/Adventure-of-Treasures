package backend.service.enums;

import backend.controller.enums.TypeMessage;
import backend.service.interfaces.ICommand;

public enum ActionItem {
    COMBINE("COMBINAR", null),
    USE("USAR", null),
    EQUIP("EQUIPAR", null),
    UNEQUIP("DESEQUIPAR", null),
    REMOVE("REMOVER", null);

    private final String name;
    private final ICommand command;

    ActionItem(String name, ICommand command){
        this.name = name;
        this.command = command;
    }

    public String getName() {
        return this.name;
    }

    public TypeMessage run() {
        return this.command.run();
    }
}
