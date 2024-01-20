package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.enums.TypeItem;
import backend.service.interfaces.ICommand;
import backend.service.interfaces.ICoordinate;
import backend.service.interfaces.IUsable;
import backend.service.model.Item;

import java.util.Optional;

public final class UsableCommand implements ICommand {

    private final Item item;
    private final int idMap;
    private final ICoordinate coordinate;


    public UsableCommand(Item item, int idMap, ICoordinate coordinate) {
        this.item = item;
        this.idMap = idMap;
        this.coordinate = coordinate;
    }

    @Override
    public Optional<TypeMessage> execute() {
        var spec = this.item.getSpecialization(TypeItem.USABLE);
        if (spec.isEmpty()) return Optional.empty();
        var usable = (IUsable) spec.get();

        if (usable.getIdMap() != this.idMap) return Optional.of(TypeMessage.USABLE_ERRO_MAP);

        if (!usable.getCoordinate().equals(this.coordinate)) return Optional.of(TypeMessage.USABLE_ERRO_COORDINATE);

        if (!usable.isEnabled()) return Optional.of(TypeMessage.USABLE_ERRO_ENABLE);

        return getEventTypeMessage();
    }


    @Override
    public void undo() {
        // remover, adicionar e event_map
    }

    private Optional<TypeMessage> getEventTypeMessage() {
        return switch (this.item.getId()) {
            case 1 -> Optional.of(TypeMessage.USABLE_KEY);
            case 2 -> Optional.of(TypeMessage.USABLE_LADDER);
            case 11 -> Optional.of(TypeMessage.USABLE_SHOVEL);
            default -> Optional.empty();
        };
    }


}
