package backend.controller.interfaces;

public interface IActionService {
    IServiceResponse refresh();

    IServiceResponse take();

    IServiceResponse open();
    IServiceResponse interact();

    IServiceResponse move(String direction);

    IServiceResponse load();
    IServiceResponse save();
}
