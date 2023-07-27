package frontend.event;

import backend.controller.interfaces.IActionResponse;
import backend.service.dto.Message;
import frontend.enums.Direction;
import frontend.event.ioc.ContainerIoC;
import frontend.event.protocolo.Request;
import frontend.event.reflexao.ManipuladorObjeto;
import frontend.event.reflexao.Reflexao;
import frontend.mapper.ActionMapper;
import frontend.mapper.InventoryMapper;
import frontend.mapper.MessageMapper;
import frontend.model.Song;
import frontend.model.SoundEffects;
import frontend.service.InterfaceGame;
import frontend.service.InterfaceInventory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;
import java.util.Objects;

public class Keyboard {

    private final Song song;
    private final InterfaceGame interfaceGame;
    private final SoundEffects soundEffects;
    private final InterfaceInventory interfaceInventory;

    private final String pacoteBase;
    private final ContainerIoC container;

    public Keyboard(InterfaceGame interfaceGame, Song song, SoundEffects soundEffects, String pacoteBase) {
        this.song = song;
        this.interfaceGame = interfaceGame;
        this.soundEffects = soundEffects;
        interfaceInventory = new InterfaceInventory(interfaceGame, this);
        this.pacoteBase = pacoteBase;
        this.container = new ContainerIoC();
    }

    public void run() {
        interfaceGame.getFrame().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //not implements
            }

            @Override
            public void keyPressed(KeyEvent event) {

                var keyCode = event.getKeyCode();

                if (Direction.containsKeyCode(keyCode)) {

                    var direction = Direction.getLabel(keyCode);

                    var actionRes = executa(String.format("/action/move?arg0=%s", direction));

                    var message = new MessageMapper().apply(actionRes);
                    var action = new ActionMapper().apply(actionRes);

                    if (message.sucess())
                        interfaceGame.updateComponentsMove(action);


                    //ENTRA NA PORTA
                } else if (keyCode == 97) {

                    var actionRes = executa("/action/open");

                    var message = new MessageMapper().apply(actionRes);
                    var action = new ActionMapper().apply(actionRes);

                    if ("finish".equalsIgnoreCase(action.songMap())) finish(action.songMap());

                    if (Objects.nonNull(message)) {
                        var effect = message.effect();
                        if (message.sucess())
                            updateItensMapGame(action);
                        else if (Objects.nonNull(effect))
                            soundEffects.play(effect);
                    }


                    //PEGAR ITEM
                } else if (keyCode == 98) {

                    var actionRes = executa("/action/take");
                    var message = new MessageMapper().apply(actionRes);
                    var action = new ActionMapper().apply(actionRes);

                    var effect = message.effect();
                    if (Objects.nonNull(effect) && !effect.isEmpty()) {
                        soundEffects.play(effect);
                    }

                    if (message.sucess()) updateItensMapGame(action);

                    //INVENTARIO
                } else if (keyCode == 99) {

                    var inventoryOpenRes = executa("/inventory/open");
                    var message = new MessageMapper().apply(inventoryOpenRes);
                    var inventoryOpen = new InventoryMapper().apply(inventoryOpenRes);

                    if (message.sucess()) interfaceInventory.open(inventoryOpen);
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                // not implements
            }
        });
    }

    private void updateItensMapGame(IActionResponse action) {
        this.interfaceGame.getMapGameJLabel().setIcon(new ImageIcon(action.iconMap()));
        this.interfaceGame.clearJLabelItens();
        this.interfaceGame.setItensJLabel(action.itens(), action.indexItens());
        this.interfaceGame.getMapGameJLabel().repaint();
        this.interfaceGame.getPlayerJLabel().setLocation(new Point(action.coordinatePlayer().x(),
                action.coordinatePlayer().y()));
    }

    private void finish(String song) {
        this.song.closePlay();
        this.soundEffects.play(song);
        System.exit(0);
    }

    public Object executa(String url) {
        Request request = new Request(url);
        String nomeControle = request.getNomeControle();
        String nomeMetodo = request.getNomeMetodo();
        Map<String, Object> params = request.getQueryParams();
        Class<?> classeControle = (new Reflexao()).getClasse(this.pacoteBase + nomeControle);
        Object instanciaControle = this.container.getInstancia(classeControle);
        return (new ManipuladorObjeto(instanciaControle)).getMetodo(nomeMetodo, params).comTratamentoDeExcecao((metodo, ex) -> {
            System.out.println("Erro no método " + metodo.getName() + " da classe " + metodo.getDeclaringClass().getName() + ".\n\n");
            throw new RuntimeException("Erro no método!");
        }).invoca();
    }

    public <T, K extends T> void registra(Class<T> tipoFonte, Class<K> tipoDestino) {
        this.container.registra(tipoFonte, tipoDestino);
    }
}
