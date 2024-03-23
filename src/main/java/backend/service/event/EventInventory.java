package backend.service.event;

import backend.Game;
import backend.repository.interfaces.IEventInventoryEntity;
import backend.service.infra.CacheService;
import backend.service.interfaces.IObserver;

public final class EventInventory implements IObserver {
    private final IEventInventoryEntity event;

    public EventInventory(IEventInventoryEntity event) {
        this.event = event;
    }

    @Override
    public void update() {
        var item = CacheService.getItem(this.event.idItemNew());
        if (item.isEmpty()) return;
        Game.player.getInventory().addItem(item.get());
    }
}
