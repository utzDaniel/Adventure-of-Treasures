package frontend.model.vo;

import rules.interfaces.IOpenInventory;

public class OpenInventoryVO implements IOpenInventory {

    private boolean open;
    public OpenInventoryVO (){}

    public OpenInventoryVO (boolean open){
        this.open = open;
    }

    @Override
    public boolean isOpen() {
        return open;
    }
}
