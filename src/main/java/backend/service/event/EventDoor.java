package backend.service.event;

import backend.repository.interfaces.IEventDoorEntity;
import backend.service.infra.Cache;
import backend.service.interfaces.IObserver;

public final class EventDoor implements IObserver {

    private final IEventDoorEntity event;

    public EventDoor(IEventDoorEntity event) {
        this.event = event;
    }

    @Override
    public void update() {
        var door = Cache.getDoor(this.event.idDoor());
        if (door.isEmpty()) return;
        door.get().setOpen(this.event.open());
    }
}
