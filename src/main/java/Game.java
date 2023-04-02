import rules.controller.Keyboard;
import frontend.view.InterfaceGame;
import backend.repository.RepositoryFactory;
import backend.model.Player;
import frontend.model.Song;
import frontend.model.SoundEffects;
import backend.model.builder.item.Item;

public class Game {

    private final Player player;

    public Game() {
        player = Player.getInstance();
        initialPlayer();
        InterfaceGame interfaceGame = new InterfaceGame();
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

    public static void main(String[] args) {
        new Game();
    }

}


