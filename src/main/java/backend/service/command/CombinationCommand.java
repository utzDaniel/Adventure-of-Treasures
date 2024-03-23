package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.enums.TypeItem;
import backend.service.interfaces.ICombinable;
import backend.service.interfaces.ICommand;
import backend.service.model.Inventory;
import backend.service.model.Item;

import java.util.ArrayList;
import java.util.List;

public final class CombinationCommand implements ICommand {

    private final List<Item> itens;
    private final CommandTool commands;
    private final Inventory inventory;

    public CombinationCommand(List<Item> itens, Inventory inventory) {
        this.commands = new CommandTool();
        this.itens = itens;
        this.inventory = inventory;
    }

    @Override
    public TypeMessage execute() {
        List<ICombinable> combinables = new ArrayList<>();
        this.itens.forEach(v -> {
            var spec = v.getSpecialization(TypeItem.COMBINABLE);
            spec.ifPresent(s -> combinables.add(((ICombinable) s)));
        });

        if (combinables.isEmpty()) return TypeMessage.ITEM_ERRO_COMBINABLE;

        if (this.itens.size() != combinables.size()) return TypeMessage.COMBINE_ERRO_ALL;

        if (combinables.get(0).sizeCombination() > combinables.size())
            return TypeMessage.COMBINE_ERRO_INCOMPLETE;

        if (combinables.get(0).sizeCombination() < combinables.size())
            return TypeMessage.COMBINE_ERRO_INVALID;

        var isCombine = combinables.stream().allMatch(v -> v.combination() == combinables.get(0).combination());
        if (!isCombine) return TypeMessage.COMBINE_ERRO_COMBINABLE;

        this.itens.forEach(v -> this.commands.addCommand(new RemoveItemInventoryCommand(v, inventory)));

        var type = this.commands.execute();
        if (!type.isSucess()) return type;

        return getEquipTypeMessage(combinables.get(0).combination());
    }

    @Override
    public void undo() {
        this.commands.undo();
    }

    private TypeMessage getEquipTypeMessage(int combination) {
        return switch (combination) {
            case 1 -> TypeMessage.COMBINE_LADDER;
            case 2 -> TypeMessage.COMBINE_MAP;
            case 3 -> TypeMessage.COMBINE_TORCH;
            default -> TypeMessage.COMBINE;
        };
    }

}