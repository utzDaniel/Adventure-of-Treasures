package backend.controller.interfaces;

import java.util.List;

public interface IInventoryService {
    IServiceResponse combination(List<Integer> idItens);

    IServiceResponse use(Integer idItem);

    IServiceResponse equip(Integer idItem);

    IServiceResponse drop(Integer idItem);

    IServiceResponse open();
}
