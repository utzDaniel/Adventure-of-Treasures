package backend.service.event;

import backend.repository.interfaces.IEventItemEntity;
import backend.service.enums.TypeItem;
import backend.service.infra.Cache;
import backend.service.interfaces.IObserver;
import backend.service.interfaces.IUsable;

public final class EventItem implements IObserver {

    private final IEventItemEntity event;

    public EventItem(IEventItemEntity event) {
        this.event = event;
    }

    @Override
    public void update() {
        var itemE = Cache.getItem(this.event.idEnabled());
        if (itemE.isEmpty()) return;
        var itemU = itemE.get().getSpecialization(TypeItem.USABLE);
        if (itemU.isEmpty()) return;
        ((IUsable) itemU.get()).setEnabled(true);
    }
}
