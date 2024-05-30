package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.interfaces.ICommand;
import backend.service.interfaces.ICommandComposite;

import java.util.ArrayList;
import java.util.List;

public final class CommandComposite implements ICommandComposite {

    private final List<ICommand> children;

    public CommandComposite() {
        this.children = new ArrayList<>();
    }

    @Override
    public void add(ICommand command) {
        this.children.add(command);
    }

    @Override
    public TypeMessage execute() {
        TypeMessage temp = TypeMessage.COMMAND_ERROR;
        for (ICommand cmd : this.children) {
            temp = cmd.execute();
            if (!temp.isSuccess()) {
                break;
            }
        }
        return temp;
    }

}