package backend;

import backend.controller.interfaces.IActionService;
import backend.controller.interfaces.IInventoryService;
import backend.service.ActionService;
import backend.service.InventoryService;
import backend.service.infra.CacheService;
import backend.service.interfaces.ICoordinate;
import backend.service.interfaces.IMove;
import backend.service.mapper.ActionResponseMapper;
import backend.service.model.Inventory;
import backend.service.model.Move;
import backend.service.model.Player;
import frontend.event.Keyboard;
import frontend.service.InterfaceGame;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Game {

    private static final Properties properties = getProp();
    public static final Player player = new Player(createMove(), createInventory());

    public Game() {
        var action = new ActionResponseMapper().apply(player);
        InterfaceGame interfaceGame = new InterfaceGame(action);

        var controller = properties.getProperty("game.controller");
        var keyboard = new Keyboard(interfaceGame, controller);
        keyboard.registra(IActionService.class, ActionService.class);
        keyboard.registra(IInventoryService.class, InventoryService.class);
        keyboard.run();
    }

    private static Inventory createInventory() {
        var capacity = Integer.parseInt(properties.getProperty("backend.inventory.capacity"));
        var maxCapacity = Integer.parseInt(properties.getProperty("backend.inventory.maxCapacity"));
        return new Inventory(capacity, maxCapacity);
    }

    private static IMove createMove() {
        var path = properties.getProperty("backend.player.path");
        int x = Integer.parseInt(properties.getProperty("backend.player.position.x"));
        int y = Integer.parseInt(properties.getProperty("backend.player.position.y"));
        var coordinate = ICoordinate.getInstance(x, y);
        var map = properties.getProperty("backend.player.map");
        var mapGame = CacheService.getMapGame(Integer.parseInt(map));
        return new Move(path, coordinate, mapGame.orElse(null));
    }



    public static void main(String[] args) {
        new Game();
    }

    public static Properties getProp() {
        var name = "src/main/resources/application.properties";
        Properties props = new Properties();
        try (FileInputStream file = new FileInputStream(name)) {
            props.load(file);
        } catch (IOException e) {
            e.getStackTrace();
        }
        return props;
    }
}

