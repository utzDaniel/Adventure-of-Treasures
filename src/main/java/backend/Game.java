package backend;

import backend.controller.interfaces.IItemDTO;
import backend.service.mapper.ItemDTOMapper;
import backend.repository.factory.RepositoryFactory;
import backend.service.infra.Cache;
import backend.service.model.Player;
import backend.service.model.builder.Item;
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

    public Game() {
        player = Player.getInstance();
        initialPlayer();
        InterfaceGame interfaceGame = new InterfaceGame(getComponents());
        Song song = interfaceGame.getSong();
        SoundEffects soundEffects = interfaceGame.getSoundEffects();
        song.play(player.getCurrentMap().getSong());
        new Keyboard(interfaceGame, song, soundEffects).run();
    }

    private void initialPlayer() {
        var repositoryItem = RepositoryFactory.getRepositoryItem();
        player.setCurrentMap(Cache.getMapGame("cais"));

        //TODO não usar itens invisivel, resolver depois
//        for (Item item : repositoryItem
//                .getAll().stream()
//                .filter(Item::isInvisible)
//                .filter(item -> !item.getName().equals("chave"))
//                .toList()) {
//            player.getInventory().setItemInvisible(item);
//        }
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


