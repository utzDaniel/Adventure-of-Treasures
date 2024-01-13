package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.enums.TypeItem;
import backend.service.infra.Cache;
import backend.service.interfaces.ICommand;
import backend.service.interfaces.IEventMap;
import backend.service.model.Item;

import java.util.Optional;

public final class EventMapCommand implements ICommand {

    private final Item item;


    public EventMapCommand(Item item) {
        this.item = item;
    }

    @Override
    public Optional<TypeMessage> execute() {
        var spec = this.item.getSpecialization(TypeItem.EVENT_MAP);
        if (spec.isEmpty()) return Optional.empty();
        var event = (IEventMap) spec.get();

        var map = Cache.getMapGame(event.getIdMap());
        if (map.isEmpty()) return Optional.of(TypeMessage.EVENT_ERRO_MAP);

        var door = map.get().getDoor(event.getIdDoor());
        if (door.isEmpty()) return Optional.of(TypeMessage.EVENT_ERRO_DOOR);

        event.setActive(!event.isActive());
        door.get().setOpen(event.isActive());

        return event.isActive() ? getEventTypeMessageActive() : getEventTypeMessageDeactivated();
    }


    @Override
    public void undo() {
        var spec = this.item.getSpecialization(TypeItem.EVENT_MAP);
        if (spec.isEmpty()) return;
        var event = (IEventMap) spec.get();

        var map = Cache.getMapGame(event.getIdMap());
        if (map.isEmpty()) return;

        var door = map.get().getDoor(event.getIdDoor());
        if (door.isEmpty()) return;

        door.get().setOpen(!door.get().isOpen());
        event.setActive(!event.isActive());
    }

    private Optional<TypeMessage> getEventTypeMessageActive() {
        return switch (this.item.getId()) {
            case 16 -> Optional.of(TypeMessage.EVENT_MAP_TORCH);
            default -> Optional.empty();
        };
    }

    private Optional<TypeMessage> getEventTypeMessageDeactivated() {
        return switch (this.item.getId()) {
            default -> Optional.empty();
        };
    }

}
