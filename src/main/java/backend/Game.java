package backend;

import backend.controller.interfaces.IActionService;
import backend.controller.interfaces.IInventoryService;
import backend.controller.interfaces.IItemDTO;
import backend.service.ActionService;
import backend.service.InventoryService;
import backend.service.infra.Cache;
import backend.service.interfaces.ICoordinate;
import backend.service.mapper.ItemDTOMapper;
import backend.service.model.Inventory;
import backend.service.model.Player;
import backend.service.model.Item;
import frontend.event.Keyboard;
import frontend.model.Song;
import frontend.model.SoundEffects;
import frontend.model.component.ComponentFactory;
import frontend.service.InterfaceGame;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Game {

    private final Properties properties;
    private final Player player;

    public Game(Properties properties) {
        this.properties = properties;
        createPlayer();
        this.player = Player.getInstance();


        InterfaceGame interfaceGame = new InterfaceGame(getComponents());
        Song song = interfaceGame.getSong();
        SoundEffects soundEffects = interfaceGame.getSoundEffects();
        song.play(player.getCurrentMap().getSong());
        var controller = properties.getProperty("game.controller");
        var keyboard = new Keyboard(interfaceGame, song, soundEffects, controller);
        keyboard.registra(IActionService.class, ActionService.class);
        keyboard.registra(IInventoryService.class, InventoryService.class);
        keyboard.run();
    }

    private void createPlayer() {

        var capacity = Integer.parseInt(this.properties.getProperty("backend.inventory.capacity"));
        var maxCapacity = Integer.parseInt(this.properties.getProperty("backend.inventory.maxCapacity"));
        var inventory = new Inventory(capacity, maxCapacity);

        var image = this.properties.getProperty("backend.player.image");
        int x = Integer.parseInt(this.properties.getProperty("backend.player.position.x"));
        int y = Integer.parseInt(this.properties.getProperty("backend.player.position.y"));
        var coordinate = ICoordinate.getInstance(x, y);

        var map = this.properties.getProperty("backend.player.map");
        var mapGame = Cache.getMapGame(Integer.parseInt(map));

        try {
            new Player(image, coordinate, mapGame, inventory);
        } catch (Exception e) {
            e.getMessage();
        }

    }

    private List<JLabel> getComponents() {
        var listJLabel = new ArrayList<JLabel>();
        var coordinatePlayer = ICoordinate.getInstance(player.getLocation().y() * 10, player.getLocation().x() * 10);
        listJLabel.add(ComponentFactory.getJLabel(this.player.getImage(), coordinatePlayer));
        listJLabel.add(ComponentFactory.getJLabel("mapa", this.player.getCurrentMap().getImage()));
        listJLabel.addAll(ComponentFactory.getJLabel(getIItemDTO(this.player.getCurrentMap().getItens())));
        return listJLabel;
    }

    private List<IItemDTO> getIItemDTO(List<Item> itens) {
        return new ArrayList<>(itens.stream()
                .map(item -> new ItemDTOMapper().apply(item))
                .toList());
    }

    public static void main(String[] args) {
        new Game(getProp());
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

