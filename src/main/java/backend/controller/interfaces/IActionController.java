package backend.controller.interfaces;

public interface IActionController {

    IResponse refresh();

    IResponse interact();

    IResponse move(String direction);

    IResponse load();

    IResponse save();

}
