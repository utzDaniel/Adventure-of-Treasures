package backend.controller.interfaces;

public interface IActionService {
    IResponse refresh();

    IResponse take();

    IResponse open();

    IResponse move(String direction);
}
