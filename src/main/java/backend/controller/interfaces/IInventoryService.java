package backend.controller.interfaces;

public interface IInventoryService {
    IServiceResponse combination(String... names);

    IServiceResponse use(String name);

    IServiceResponse equip(String name);

    IServiceResponse drop(String name);

    IServiceResponse open();
}
