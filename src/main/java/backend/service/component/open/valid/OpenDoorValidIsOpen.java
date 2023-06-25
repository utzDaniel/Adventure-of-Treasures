package backend.service.component.open.valid;

import backend.service.exception.DoorExeption;
import backend.service.interfaces.IOpenDoorValid;
import backend.service.model.Door;

public final class OpenDoorValidIsOpen implements IOpenDoorValid {
    @Override
    public void valid(Door door) {
        if (!door.isOpen())
            throw new DoorExeption("Porta está fechada!");
    }
}
