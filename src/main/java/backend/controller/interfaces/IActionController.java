package backend.controller.interfaces;

public interface IActionController {

    IResponse inventoryQuit();

    IResponse combination(String names);

    IResponse use(String name);

    IResponse equip(String name);

    IResponse drop(String name);

    IResponse inventoryOpen();

    IResponse take();

    IResponse open();

    IResponse move(String direction);

}
