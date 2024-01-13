package backend.service.interfaces;

import backend.controller.enums.TypeMessage;

import java.util.Optional;

public interface ICommand {

    Optional<TypeMessage> execute();

    void undo();
}
