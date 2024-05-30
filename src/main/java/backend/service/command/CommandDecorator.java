package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.handler.Handler;
import backend.service.interfaces.ICommand;
import backend.service.interfaces.ICommandDecorator;

public final class CommandDecorator<T> implements ICommandDecorator {

    private final ICommand command;
    private final Handler<T> handler;
    private final T object;

    public CommandDecorator(T object, ICommand command, Handler<T> handler) {
        this.command = command;
        this.handler = handler;
        this.object = object;
    }

    @Override
    public TypeMessage execute() {
        var msg = this.handler.handle(this.object);
        return msg.orElseGet(this.command::execute);
    }
}
