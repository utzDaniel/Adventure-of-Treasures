package backend.model.dto;

import rules.interfaces.IOpenInventory;

public class OpenInventoryDTO implements IOpenInventory {

    private boolean open;
    public OpenInventoryDTO(){}

    public OpenInventoryDTO(boolean open){
        this.open = open;
    }

    @Override
    public boolean isOpen() {
        return open;
    }
}
