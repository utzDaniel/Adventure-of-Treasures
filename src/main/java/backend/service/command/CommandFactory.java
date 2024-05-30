package backend.service.command;

import backend.Game;
import backend.service.enums.*;
import backend.service.handler.*;
import backend.service.interfaces.ICommand;
import backend.service.interfaces.IEquippable;
import backend.service.interfaces.IMove;
import backend.service.memento.BackupMementoFactory;
import backend.service.model.*;

import java.util.List;

public final class CommandFactory {

    private static final String SAVE_FILENAME = "src/main/resources/save/player.txt";
    private static final Player PLAYER = Game.player;


    private CommandFactory() {
    }

    public static ICommand create(TypeInventoryCommand type, List<Item> items) {
        return switch (type) {
            case USE -> createUsableCommand(PLAYER, items);
            case DROP -> createDropItemCommand(PLAYER, items);
            case EQUIP -> createEquippableCommand(PLAYER.getInventory(), items);
            case COMBINATION -> createCombinationCommand(PLAYER.getInventory(), items);
        };
    }

    public static ICommand createMoveCommand(IMove player, Direction direction) {
        return new MoveCommand(player, direction);
    }

    public static ICommand create(TypeCommand typeCommand) {
        return switch (typeCommand) {
            case INTERACT -> createInteractCommand(PLAYER);
            case SAVE -> createSaveCommand();
            case LOAD -> createLoadCommand();
        };
    }

    public static ICommand createCombinationCommand(Inventory inventory, List<Item> items) {
        var handler = new CombinableEmptyHandler();
        handler
                .setNextHandler(new CombinableSizeHandler())
                .setNextHandler(new CombinableIncompleteHandler())
                .setNextHandler(new CombinableInvalidHandler())
                .setNextHandler(new CombinableErrorHandler());

        var commands = new CommandComposite();
        items.forEach(v -> commands.add(createRemoveItemInventoryCommand(inventory, v)));
        var command = new CombinationCommand(items, commands);

        return new CommandDecorator<>(items, command, handler);
    }

    public static ICommand createUsableCommand(Player player, List<Item> items) {
        var idMap = player.getCurrentMap().id();
        var coordinate = player.getCoordinate();

        var handler = new ItemsSizeHandler(items);
        handler
                .setNextHandler(new SpecializationHandler(TypeItem.USABLE))
                .setNextHandler(new UsableMapHandler(idMap))
                .setNextHandler(new UsableCoordinateHandler(coordinate))
                .setNextHandler(new UsableEnableHandler());

        var commands = new CommandComposite();
        commands.add(createRemoveItemInventoryCommand(player.getInventory(), items.get(0)));

        var command = new UsableCommand(items.get(0), commands);

        return new CommandDecorator<>(items.get(0), command, handler);
    }

    public static ICommand createEquippableCommand(Inventory inventory, List<Item> items) {
        var handler = new ItemsSizeHandler(items);
        handler.setNextHandler(new SpecializationHandler(TypeItem.EQUIPPABLE));

        var command = new EquippableCommand(items.get(0), inventory);

        return new CommandDecorator<>(items.get(0), command, handler);
    }

    public static ICommand createDropItemCommand(Player player, List<Item> items) {
        var handler = new ItemsSizeHandler(items);

        var commands = new CommandComposite();
        commands.add(createRemoveItemInventoryCommand(player.getInventory(), items.get(0)));
        commands.add(createAddItemMapGameCommand(player.getCurrentMap(), items.get(0)));

        var command = new DropItemCommand(items.get(0), player, commands);

        return new CommandDecorator<>(items.get(0), command, handler);
    }


    public static ICommand createRemoveItemInventoryCommand(Inventory inventory, Item item) {
        var handler = new SpecializationHandler(TypeItem.MISSION);
        handler
                .setNextHandler(new RemoveItemInventoryEquipHandler())
                .setNextHandler(new RemoveItemInventoryFoundHandler(inventory));

        var command = new RemoveItemInventoryCommand(item, inventory);

        return new CommandDecorator<>(item, command, handler);
    }

    public static ICommand createRemoveItemMapGameCommand(MapGame mapGame, Item item) {
        return new RemoveItemMapGameCommand(item, mapGame);
    }

    public static ICommand createAddItemMapGameCommand(MapGame mapGame, Item item) {
        return new AddItemMapGameCommand(item, mapGame);
    }

    public static ICommand createAddItemInventoryCommand(Inventory inventory, Item item) {
        var handler = new AddItemInventoryFullHandler(inventory);
        var command = new AddItemInventoryCommand(item, inventory);
        return new CommandDecorator<>(item, command, handler);
    }

    public static ICommand createEquipCommand(Inventory inventory, IEquippable equippable) {
        var handler = new EquippableErroHandler(false);

        var command = new EquipCommand(equippable, inventory);

        return new CommandDecorator<>(equippable, command, handler);
    }

    public static ICommand createUnequipCommand(Inventory inventory, IEquippable equippable) {
        var handler = new EquippableErroHandler(true);
        handler.setNextHandler(new UnequipCapacityHandler(inventory));

        var command = new UnequipCommand(equippable, inventory);

        return new CommandDecorator<>(equippable, command, handler);
    }

    public static ICommand createInteractCommand(Player player) {
        var handler = new InteractCommandErrorHandler();

        return new InteractCommand(player, handler);
    }

    public static ICommand createInteractCommand(Door door, Player player) {
        var handler = new FoundHandler<Door>(TypeObject.DOOR);
        handler.setNextHandler(new InteractDoorClosedHandler())
                .setNextHandler(new InteractDoorMapFoundHandler());

        var command = new InteractDoorCommand(door, player);

        return new CommandDecorator<>(door, command, handler);
    }

    public static ICommand createInteractCommand(Item item, Player player) {
        var handler = new FoundHandler<Item>(TypeObject.ITEM);

        var command = new InteractItemCommand(item, player);

        return new CommandDecorator<>(item, command, handler);
    }

    public static ICommand createInteractCommand(NPC npc, Player player) {
        var handler = new FoundHandler<NPC>(TypeObject.NPC);
        handler.setNextHandler(new InteractNPCDoorFoundHandler())
                .setNextHandler(new InteractNPCMapFoundHandler());

        var command = new InteractNPCCommand(npc, player);

        return new CommandDecorator<>(npc, command, handler);
    }

    public static ICommand createMoveNextSceneryCommand(IMove person, Direction direction) {
        var handler = new MoveNextSceneryExitHandler(direction);
        handler.setNextHandler(new MoveNextSceneryMapFoundHandler(direction));

        return new MoveNextSceneryCommand(person, direction, handler);
    }

    public static ICommand createMoveSceneryCommand(IMove person, Direction direction) {
        var handler = new MoveSceneryBlockedHandler(direction);

        return new MoveSceneryCommand(person, direction, handler);
    }

    private static ICommand createLoadCommand() {
        return new LoadCommand(SAVE_FILENAME);
    }

    private static ICommand createSaveCommand() {
        var memento = new BackupMementoFactory().create();
        return new SaveCommand(SAVE_FILENAME, memento);
    }

}
