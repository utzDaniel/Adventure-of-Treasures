package backend.controller.interfaces;

public interface IInventoryController {

    IResponse combination(String names);

    IResponse use(String name);

    IResponse equip(String name);

    IResponse drop(String name);
    IResponse open();

}
