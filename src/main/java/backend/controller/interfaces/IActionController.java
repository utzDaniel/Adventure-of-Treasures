package backend.controller.interfaces;

public interface IActionController {
    IResponse refresh();

    IResponse take();

    IResponse open();
    IResponse interact();

    IResponse move(String direction);

    IResponse load();
    IResponse save();

}
