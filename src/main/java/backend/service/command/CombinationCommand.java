package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.handler.*;
import backend.service.interfaces.ICommand;
import backend.service.interfaces.IHandler;
import backend.service.model.Inventory;
import backend.service.model.Item;

import java.util.List;
import java.util.stream.Collectors;

public final class CombinationCommand implements ICommand {

    private final List<Item> items;
    private final CommandTool commands;
    private final Inventory inventory;
    private final IHandler<List<Item>> combinableHandler;

    public CombinationCommand(List<Item> items, Inventory inventory) {
        this.commands = new CommandTool();
        this.items = items;
        this.inventory = inventory;
        this.combinableHandler = new CombinableEmptyHandler();
        this.combinableHandler
                .setNextHandler(new CombinableSizeHandler())
                .setNextHandler(new CombinableIncompleteHandler())
                .setNextHandler(new CombinableInvalidHandler())
                .setNextHandler(new CombinableErrorHandler());
    }

    @Override
    public TypeMessage execute() {
        var msg = this.combinableHandler.handle(this.items);
        if (msg.isPresent()) return msg.get();

        this.items.forEach(v -> this.commands.addCommand(new RemoveItemInventoryCommand(v, inventory)));

        var type = this.commands.execute();
        if (!type.isSuccess()) return type;

        var combination = getCombination();
        return getCombinationTypeMessage(combination);
    }

    @Override
    public void undo() {
        this.commands.undo();
    }

    private TypeMessage getCombinationTypeMessage(String combination) {
        return switch (combination) {
            case "5;12" -> TypeMessage.COMBINE_MAP;
            case "7;9;14" -> TypeMessage.COMBINE_LADDER;
            case "3;4;6;13" -> TypeMessage.COMBINE_TORCH;
            default -> TypeMessage.COMBINE;
        };
    }

    public String getCombination() {
        return this.items.stream()
                .map(Item::id)
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(";"));
    }

}