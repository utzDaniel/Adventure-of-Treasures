package frontend.view;

import frontend.exception.EventException;
import frontend.model.Song;
import frontend.model.SoundEffects;
import frontend.model.component.JOptionPaneFactory;

import java.awt.*;

public class EventsMenuBar {

    private final Container container;
    private final Song song;
    private final SoundEffects soundEffects;

    public EventsMenuBar(Container container, Song song, SoundEffects soundEffects) {
        this.container = container;
        this.song = song;
        this.soundEffects = soundEffects;
    }

    public void action(String name) {
        switch (name) {
            case "Historia" -> JOptionPaneFactory.openHistory(container);
            case "Comandos" -> JOptionPaneFactory.openCommand(container);
            case "Ajuda" -> JOptionPaneFactory.openHelp(container);
            case "Musica" -> song.onPlayer();//TODO Tentar remover esse evento daqui
            case "Efeitos" -> soundEffects.onPlayer();//TODO Tentar remover esse evento daqui
            case "Sair" -> System.exit(0);
            default -> throw new EventException("Nome do evento não encontrado");
        }
    }

}
