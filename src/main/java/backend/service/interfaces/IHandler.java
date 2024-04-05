package backend.service.interfaces;

import backend.controller.enums.TypeMessage;

import java.util.Optional;

public interface IHandler<T> {

    IHandler<T> setNextHandler(IHandler<T> handler);

    Optional<TypeMessage> handle(T request);

}
