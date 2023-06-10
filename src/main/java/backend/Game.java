package backend;

import backend.service.model.Player;
import backend.service.model.builder.Item;
import backend.dto.ItemDTO;
import backend.repository.factory.RepositoryFactory;
import frontend.event.Keyboard;
import frontend.model.Song;
import frontend.model.SoundEffects;
import frontend.model.component.ComponentFactory;
import frontend.view.InterfaceGame;
import backend.controller.interfaces.IItem;

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
        var repositoryMapGame = RepositoryFactory.getRepositoryMapGame();
        var repositoryItem = RepositoryFactory.getRepositoryItem();
        player.setCurrentMap(repositoryMapGame.get("cais"));

        //TODO não usar itens invisivel, resolver depois
        for (Item item : repositoryItem
                .getAll().stream()
                .filter(Item::isInvisible)
                .filter(item -> !item.getName().equals("chave"))
                .toList()) {
            player.getInventory().setItemInvisible(item);
        }
    }

    private List<JLabel> getComponents() {
        var listJLabel = new ArrayList<JLabel>();
        listJLabel.add(ComponentFactory.getJLabel(this.player));
        listJLabel.add(ComponentFactory.getJLabel(this.player.getCurrentMap()));
        listJLabel.addAll(ComponentFactory.getJLabel(getIItemDTO(this.player.getCurrentMap().getItemVisible())));
        return listJLabel;
    }

    private List<IItem> getIItemDTO(List<Item> itens) {
        return new ArrayList<>(itens.stream()
                .map(item -> new ItemDTO(item.getIcon().toString(), item.getLocation(), item.getName(),
                        item.getDescription(), item.getEffect(), item.getWeight(), item.getClass().getName(), item.equipped()))
                .toList());
    }

    public static void main(String[] args) {
        new Game();
    }

}

