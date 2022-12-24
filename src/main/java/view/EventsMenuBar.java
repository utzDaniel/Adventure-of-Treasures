package view;

import exception.EventException;
import model.Song;
import model.SoundEffects;

import javax.swing.*;

public class EventsMenuBar {

    private final PopupMenuBarMessage popupMenuMessage;
    private final Song song;
    private final SoundEffects soundEffects;

   public EventsMenuBar(JFrame frame, Song song, SoundEffects soundEffects){
       this.popupMenuMessage = new PopupMenuBarMessage(frame);
       this.song = song;
       this.soundEffects = soundEffects;
   }

    public void event(String name) {
        switch (name) {
            case "Historia" -> popupMenuMessage.history();
            case "Comandos" -> popupMenuMessage.command();
            case "Ajuda" -> popupMenuMessage.help();
            case "Musica" -> song.onPlayer();
            case "Efeitos" -> soundEffects.onPlayer();
            case "Sair" -> System.exit(0);
            default -> throw new EventException("Nome do evento não encontrado");
        }
    }

}
