package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.enums.TypeItem;
import backend.service.interfaces.ICombinable;
import backend.service.interfaces.ICommand;
import backend.service.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class CombinationCommand implements ICommand {

    private final List<Item> itens;

    public CombinationCommand(List<Item> itens) {
        this.itens = itens;
    }

    @Override
    public Optional<TypeMessage> execute() {

        List<ICombinable> combinables = new ArrayList<>();
        this.itens.forEach(v -> {
            var spec = v.getSpecialization(TypeItem.COMBINABLE);
            spec.ifPresent(s -> combinables.add(((ICombinable) s)));
        });

        if (this.itens.size() != combinables.size()) return Optional.of(TypeMessage.COMBINE_ERRO_ALL);

        if (combinables.get(0).getSizeCombination() > combinables.size())
            return Optional.of(TypeMessage.COMBINE_ERRO_INCOMPLETE);

        if (combinables.get(0).getSizeCombination() < combinables.size())
            return Optional.of(TypeMessage.COMBINE_ERRO_INVALID);

        var isCombine = combinables.stream().allMatch(v -> v.getNewItem() == combinables.get(0).getNewItem());
        if (!isCombine) return Optional.of(TypeMessage.COMBINE_ERRO_COMBINABLE);

        return getEquipTypeMessage(combinables.get(0).getNewItem());
    }

    @Override
    public void undo() {
        // not implements
    }

    private Optional<TypeMessage> getEquipTypeMessage(int idNewItem) {
        return switch (idNewItem) {
            case 8 -> Optional.of(TypeMessage.COMBINE_MAP);
            case 2 -> Optional.of(TypeMessage.COMBINE_LADDER);
            case 16 -> Optional.of(TypeMessage.COMBINE_TORCH);
            default -> Optional.empty();
        };
    }

}
