package backend.controller.interfaces;

public interface IInventoryService {
    IResponse combination(String... names);

    IResponse use(String name);

    IResponse equip(String name);

    IResponse drop(String name);

    IResponse open();
}
