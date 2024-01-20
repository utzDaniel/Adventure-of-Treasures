package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.enums.TypeItem;
import backend.service.infra.Cache;
import backend.service.interfaces.ICommand;
import backend.service.interfaces.IEventMap;
import backend.service.model.Item;

import java.util.Objects;
import java.util.Optional;

public final class EventMapCommand implements ICommand {

    private final Item item;
    private String imageOld;


    public EventMapCommand(Item item) {
        this.item = item;
        this.imageOld = null;
    }

    @Override
    public Optional<TypeMessage> execute() {
        var spec = this.item.getSpecialization(TypeItem.EVENT_MAP);
        if (spec.isEmpty()) return Optional.empty();
        var event = (IEventMap) spec.get();

        var map = Cache.getMapGame(event.getIdMap());
        if (map.isEmpty()) return Optional.of(TypeMessage.EVENT_ERRO_MAP);

        if (Objects.nonNull(event.getMapImage())) {
            this.imageOld = map.get().getImage();
            map.get().setImage(event.getMapImage());
        }

        event.setActive(!event.isActive());

        if (event.getIdDoor() != -1) {
            var door = map.get().getDoor(event.getIdDoor());
            if (door.isEmpty()) return Optional.of(TypeMessage.EVENT_ERRO_DOOR);
            door.get().setOpen(event.isActive());
        }

        return event.isActive() ? getEventTypeMessageActive() : Optional.empty();
    }


    @Override
    public void undo() {
        var spec = this.item.getSpecialization(TypeItem.EVENT_MAP);
        if (spec.isEmpty()) return;
        var event = (IEventMap) spec.get();

        var map = Cache.getMapGame(event.getIdMap());
        if (map.isEmpty()) return;

        if (Objects.nonNull(this.imageOld)) map.get().setImage(this.imageOld);

        event.setActive(!event.isActive());

        if (event.getIdDoor() != -1) {
            var door = map.get().getDoor(event.getIdDoor());
            if (door.isEmpty()) return;
            door.get().setOpen(event.isActive());
        }

    }

    private Optional<TypeMessage> getEventTypeMessageActive() {
        return switch (this.item.getId()) {
            case 16 -> Optional.of(TypeMessage.EVENT_MAP_TORCH);
            case 1 -> Optional.of(TypeMessage.EVENT_MAP_KEY);
            case 2 -> Optional.of(TypeMessage.EVENT_MAP_LADDER);
            case 11 -> Optional.of(TypeMessage.EVENT_MAP_SHOVEL);
            default -> Optional.empty();
        };
    }


}
