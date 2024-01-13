package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.enums.Commands;
import backend.service.interfaces.ICommand;
import backend.service.model.Inventory;
import backend.service.model.Item;

import java.util.*;

public final class CommandTool {

    private final Map<Commands, ICommand> commandMap;
    private final Commands[] commads;
    private final Set<TypeMessage> types;
    private final ArrayDeque<ICommand> stack;

    public CommandTool(Commands[] commads, Item item, Inventory inventory) {
        this.commads = commads;
        this.types = new HashSet<>();
        this.stack = new ArrayDeque<>();
        this.commandMap = new EnumMap<>(Commands.class);
        this.commandMap.put(Commands.EQUIPAR, new EquipableCommand(item, inventory));
        this.commandMap.put(Commands.EVENTO, new EventCommand(item));

    }

    public TypeMessage execute() {
        this.types.clear();
        this.stack.clear();

        Optional<TypeMessage> temp;

        for (Commands commad : this.commads) {
            var cmd = this.commandMap.get(commad);
            temp = cmd.execute();
            this.stack.add(cmd);
            if (temp.isPresent()) {
                this.types.add(temp.get());
                if (!temp.get().isSucess()) {
                    undo();
                    break;
                }
            }

        }
        return TypeMessageCombination.combine(this.types).orElse(TypeMessage.COMMAND_ERRO);
    }

    private void undo() {
        while (!this.stack.isEmpty()) this.stack.pollLast().undo();
    }



}
