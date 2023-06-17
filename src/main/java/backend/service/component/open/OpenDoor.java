package backend.service.component.open;

import backend.service.interfaces.IOpenDoorValid;
import backend.service.model.Door;
import backend.service.component.open.valid.OpenDoorValidIsExist;
import backend.service.component.open.valid.OpenDoorValidIsOpen;

import java.util.ArrayList;
import java.util.List;

public final class OpenDoor {

    private final Door door;
    private final List<IOpenDoorValid> valids;

    public OpenDoor(Door door) {
        this.door = door;
        this.valids = new ArrayList<>();
        valids.add(new OpenDoorValidIsExist());
        valids.add(new OpenDoorValidIsOpen());
    }

    public void run() {
        for (IOpenDoorValid valid : valids) valid.valid(door);
    }
}
