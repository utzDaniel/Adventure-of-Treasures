package frontend.service;

import frontend.exception.EventException;
import frontend.model.Song;
import frontend.model.SoundEffects;
import frontend.model.component.GameJOptionPaneFactory;

import java.awt.*;

public final class EventsMenuBar {

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
            case "Historia" -> GameJOptionPaneFactory.openHistory(this.container);
            case "Comandos" -> GameJOptionPaneFactory.openCommand(this.container);
            case "Ajuda" -> GameJOptionPaneFactory.openHelp(this.container);
            case "Musica" -> this.song.onPlayer();// TODO Tentar remover esse evento daqui
            case "Efeitos" -> this.soundEffects.onPlayer();// TODO Tentar remover esse evento daqui
            case "Sair" -> System.exit(0);
            default -> throw new EventException("Nome do evento não encontrado");
        }
    }

}
