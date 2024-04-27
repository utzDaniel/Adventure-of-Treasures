package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.interfaces.ICommand;
import backend.service.interfaces.IHandler;
import backend.service.model.Item;

import java.util.List;
import java.util.stream.Collectors;

public final class CombinationCommand implements ICommand {

    private final List<Item> items;
    private final MacroCommand commands;
    private final IHandler<List<Item>> handler;

    public CombinationCommand(List<Item> items, IHandler<List<Item>> handler, MacroCommand commands) {
        this.items = items;
        this.handler = handler;
        this.commands = commands;
    }

    @Override
    public TypeMessage execute() {
        var msg = this.handler.handle(this.items);
        if (msg.isPresent()) return msg.get();

        var type = this.commands.execute();
        if (!type.isSuccess()) return type;

        this.items.get(0).warn();

        var combination = getCombination();
        return getCombinationTypeMessage(combination);
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