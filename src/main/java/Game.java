import controller.Keyboard;
import model.Player;
import model.Song;
import model.SoundEffects;
import model.builder.item.Item;
import repository.RepositoryFactory;
import view.InterfaceGame;

public class Game {

    private final Player player;
    private final InterfaceGame interfaceGame;
    private final Song song;
    private final SoundEffects soundEffects;

    public Game() {
        player = Player.getInstance();
        initialPlayer();
        interfaceGame = new InterfaceGame();
        song = interfaceGame.getSong();
        soundEffects = interfaceGame.getSoundEffects();
        song.play(player.getCurrentMap().getSong());
        new Keyboard(interfaceGame, song, soundEffects).run();
    }

    private void initialPlayer() {
        player.setCurrentMap(RepositoryFactory.getRepositoryMapGame().get("cais"));
        for (Item item : RepositoryFactory.getRepositoryItem().getItemInvisible()) {
            player.getInventory().setItemInvisible(item);
        }
    }

    public static void main(String[] args) {
        new Game();
    }

}


