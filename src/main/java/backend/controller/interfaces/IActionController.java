package backend.controller.interfaces;

public interface IActionController {
    IResponse refresh();

    IResponse take();

    IResponse open();

    IResponse move(String direction);

}
