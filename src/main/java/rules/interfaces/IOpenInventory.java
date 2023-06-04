package rules.interfaces;

import java.util.List;

public interface IOpenInventory {

    boolean isOpen();
    int getCapacity();
    int getMaxCapacity();

    List<IItem> getItens();

}
