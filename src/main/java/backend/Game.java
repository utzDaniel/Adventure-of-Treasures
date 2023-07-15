package backend;

import backend.controller.interfaces.IItemDTO;
import backend.controller.interfaces.IService;
import backend.repository.factory.RepositoryFactory;
import backend.service.Service;
import backend.service.infra.Cache;
import backend.service.interfaces.ICoordinate;
import backend.service.mapper.ItemDTOMapper;
import backend.service.model.Inventory;
import backend.service.model.Player;
import backend.service.model.builder.Item;
import backend.service.model.builder.ItemFactory;
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
        keyboard.registra(IService.class, Service.class);
        keyboard.run();
    }

    private void createPlayer() {

        var capacity = Integer.parseInt(this.properties.getProperty("backend.inventory.capacity"));
        var maxCapacity = Integer.parseInt(this.properties.getProperty("backend.inventory.maxCapacity"));
        var inventory = new Inventory(capacity, maxCapacity);

        var icon = new ImageIcon(this.properties.getProperty("backend.player.icon"));
        int x = Integer.parseInt(this.properties.getProperty("backend.player.x"));
        int y = Integer.parseInt(this.properties.getProperty("backend.player.y"));
        var coordinate = ICoordinate.getInstance(x, y);

        var map = this.properties.getProperty("backend.player.map");
        var mapGame = Cache.getMapGame(map);

        try {
            var player = new Player(icon, coordinate, mapGame, inventory);
            //TODO não usar itens invisivel, resolver depois
            RepositoryFactory.getRepositoryItem().getAll()
                    .stream().filter(item -> !item.visible() && !item.name().equals("chave"))
                    .forEach(entity -> player.getInventory().setItemInvisible(new ItemFactory().create(entity)));
        } catch (Exception e) {
            e.getMessage();
        }

    }

    private List<JLabel> getComponents() {
        var listJLabel = new ArrayList<JLabel>();
        listJLabel.add(ComponentFactory.getJLabel(this.player.getIcon().toString(), this.player.getLocation()));
        listJLabel.add(ComponentFactory.getJLabel("mapa", this.player.getCurrentMap().getIcon().toString()));
        listJLabel.addAll(ComponentFactory.getJLabel(getIItemDTO(this.player.getCurrentMap().getItemVisible())));
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

