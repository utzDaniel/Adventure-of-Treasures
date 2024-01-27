package backend.service.interfaces;

import backend.controller.enums.TypeMessage;

public interface ICommand {

    TypeMessage execute();

    void undo();
}
