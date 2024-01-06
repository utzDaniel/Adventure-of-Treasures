package backend.controller.interfaces;

public interface IInventoryController {

    IResponse combination(String idItens);

    IResponse use(String idItem);

    IResponse equip(String idItem);

    IResponse drop(String idItem);
    IResponse open();

}
