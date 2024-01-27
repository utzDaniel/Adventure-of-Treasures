package backend.service.event;

import backend.repository.interfaces.IEventMapEntity;
import backend.service.infra.Cache;
import backend.service.interfaces.IObserver;

public final class EventMap implements IObserver {

    private final IEventMapEntity event;

    public EventMap(IEventMapEntity event) {
        this.event = event;
    }

    @Override
    public void update() {
        var map = Cache.getMapGame(this.event.idMap());
        if (map.isEmpty()) return;
        map.get().setImage(this.event.image());
    }
}
