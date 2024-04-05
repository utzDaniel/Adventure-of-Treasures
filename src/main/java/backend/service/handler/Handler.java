package backend.service.handler;

import backend.controller.enums.TypeMessage;
import backend.service.interfaces.IHandler;

import java.util.Objects;
import java.util.Optional;

public abstract class Handler<T> implements IHandler<T> {

    protected IHandler<T> nextHandler;

    @Override
    public final IHandler<T> setNextHandler(IHandler<T> handler) {
        this.nextHandler = handler;
        return handler;
    }

    @Override
    public final Optional<TypeMessage> handle(T request) {
        Optional<TypeMessage> result = hook(request);

        if (result.isPresent()) {
            return result;
        }

        if (Objects.nonNull(this.nextHandler)) {
            return this.nextHandler.handle(request);
        }

        return Optional.empty();
    }

    protected abstract Optional<TypeMessage> hook(T request);

}
