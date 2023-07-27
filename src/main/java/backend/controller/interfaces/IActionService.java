package backend.controller.interfaces;

public interface IActionService {
    IServiceResponse refresh();

    IServiceResponse take();

    IServiceResponse open();

    IServiceResponse move(String direction);
}
