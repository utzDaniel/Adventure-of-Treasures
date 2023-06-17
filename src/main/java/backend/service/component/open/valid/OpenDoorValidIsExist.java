package backend.service.component.open.valid;

import backend.controller.exception.DoorExeption;
import backend.service.interfaces.IOpenDoorValid;
import backend.service.model.Door;

import java.util.Objects;

public final class OpenDoorValidIsExist implements IOpenDoorValid {

    @Override
    public void valid(Door door) {
        if (Objects.isNull(door))
            throw new DoorExeption("Porta não existe!");
    }
}
