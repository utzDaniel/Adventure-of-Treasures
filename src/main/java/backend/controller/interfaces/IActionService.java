package backend.controller.interfaces;

public interface IActionService {
    IServiceResponse refresh();

    IServiceResponse interact();

    IServiceResponse move(String direction);
    IServiceResponse load();

    IServiceResponse save();
}
