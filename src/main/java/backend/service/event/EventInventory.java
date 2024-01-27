package backend.service.event;

import backend.repository.interfaces.IEventInventoryEntity;
import backend.service.infra.Cache;
import backend.service.interfaces.IObserver;

public final class EventInventory implements IObserver {
    private final IEventInventoryEntity event;

    public EventInventory(IEventInventoryEntity event) {
        this.event = event;
    }

    @Override
    public void update() {
        var item = Cache.getItem(this.event.idItemNew());
        if (item.isEmpty()) return;
        Cache.getInventory().addItem(item.get());
    }
}
