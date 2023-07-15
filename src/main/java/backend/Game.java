package backend;

import backend.controller.interfaces.IItemDTO;
import backend.controller.interfaces.IService;
import backend.service.Service;
import backend.service.mapper.ItemDTOMapper;
import backend.repository.factory.RepositoryFactory;
import backend.service.infra.Cache;
import backend.service.model.Player;
import backend.service.model.builder.Item;
import backend.service.model.builder.ItemFactory;
import frontend.event.Keyboard;
import frontend.model.Song;
import frontend.model.SoundEffects;
import frontend.model.component.ComponentFactory;
import frontend.service.InterfaceGame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private final Player player;
    private final String pacoteBase = "backend.controller.";

    public Game() {
        player = Player.getInstance();
        initialPlayer();
        InterfaceGame interfaceGame = new InterfaceGame(getComponents());
        Song song = interfaceGame.getSong();
        SoundEffects soundEffects = interfaceGame.getSoundEffects();
        song.play(player.getCurrentMap().getSong());

        var keyboard = new Keyboard(interfaceGame, song, soundEffects, pacoteBase);
        keyboard.registra(IService.class, Service.class);
        keyboard.run();

    }

    private void initialPlayer() {
        player.setCurrentMap(Cache.getMapGame("cais"));
        //TODO não usar itens invisivel, resolver depois
        RepositoryFactory.getRepositoryItem().getAll()
                .stream().filter(item -> !item.visible() && !item.name().equals("chave"))
                .forEach(entity -> player.getInventory().setItemInvisible(new ItemFactory().create(entity)));
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
        new Game();
    }
}


