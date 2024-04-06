package backend.service.event;

import backend.repository.interfaces.IEventMapEntity;
import backend.repository.singleton.DecorationRepository;
import backend.service.infra.CacheService;
import backend.service.interfaces.IObserver;

public final class EventMap implements IObserver {

    private final IEventMapEntity event;

    public EventMap(IEventMapEntity event) {
        this.event = event;
    }

    @Override
    public void update() {
        var map = CacheService.getMapGame(this.event.idMap());
        if (map.isEmpty()) return;
        DecorationRepository.getInstance().getById(this.event.idDecoration())
                .ifPresentOrElse(d -> map.get().setDecoration(d), () -> map.get().setDecoration(null));
    }
}
