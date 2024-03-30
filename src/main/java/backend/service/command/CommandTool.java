package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.interfaces.ICommand;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public final class CommandTool implements ICommand {

    private final List<ICommand> commands;
    private final ArrayDeque<ICommand> stack;

    public CommandTool() {
        this.commands = new ArrayList<>();
        this.stack = new ArrayDeque<>();
    }

    public void addCommand(ICommand command) {
        this.commands.add(command);
    }

    @Override
    public TypeMessage execute() {
        this.stack.clear();
        TypeMessage temp = TypeMessage.COMMAND_ERRO;
        for (ICommand cmd : this.commands) {
            temp = cmd.execute();
            this.stack.add(cmd);
            if (!temp.isSuccess()) {
                undo();
                break;
            }
        }
        return temp;
    }

    @Override
    public void undo() {
        while (!this.stack.isEmpty()) this.stack.pollLast().undo();
    }

}