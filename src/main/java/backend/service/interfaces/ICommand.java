package backend.service.interfaces;

import backend.controller.enums.TypeMessage;

@FunctionalInterface
public interface ICommand {

   TypeMessage run();
}
