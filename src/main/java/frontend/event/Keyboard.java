package frontend.event;

import backend.controller.interfaces.IActionResponse;
import frontend.enums.ComponentsProperties;
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

    public Keyboard(InterfaceGame interfaceGame, String pacoteBase) {
        this.song = interfaceGame.getSong();
        this.interfaceGame = interfaceGame;
        this.soundEffects = interfaceGame.getSoundEffects();
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

                    if (message.success())
                        interfaceGame.updateComponentsMove(action);


                } else if (keyCode == 97) {

                    var actionRes = executa("/action/interact");
                    var message = new MessageMapper().apply(actionRes);
                    var action = new ActionMapper().apply(actionRes);

                    if (Objects.isNull(message)) return;

                    var effect = message.effect();

                    if (Objects.nonNull(effect) && effect.contains("finish")) {
                        finish(effect);
                    }

                    if (message.success()) updateItensMapGame(action);

                    if (Objects.nonNull(effect) && !effect.isEmpty()) {
                        soundEffects.play(effect);
                    }

                } else if (keyCode == 96) {

                    var inventoryOpenRes = executa("/inventory/open");
                    var message = new MessageMapper().apply(inventoryOpenRes);
                    var inventoryOpen = new InventoryMapper().apply(inventoryOpenRes);

                    if (message.success()) interfaceInventory.open(inventoryOpen);

                } else if (keyCode == 116) {

                    var actionRes = executa("/action/load");
                    var message = new MessageMapper().apply(actionRes);
                    var action = new ActionMapper().apply(actionRes);

                    if (message.success())
                        interfaceGame.updateComponentsMove(action);

                    System.out.println(message.text());

                } else if (keyCode == 117) {

                    var actionRes = executa("/action/save");
                    var message = new MessageMapper().apply(actionRes);
                    System.out.println(message.text());

                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                // not implements
            }
        });
    }

    private void updateItensMapGame(IActionResponse action) {
        action.components().forEach(info -> {
            if (info.name().equals(ComponentsProperties.PLAYER.name())) {
                this.interfaceGame.getPlayerJLabel().setLocation(info.point());
            } else if (info.name().equals(ComponentsProperties.MAP.name())) {
                this.interfaceGame.getMapGameJLabel().setIcon(new ImageIcon(info.image()));
            }
        });

        this.interfaceGame.clearJLabelItens();
        var lisItem = action.components().stream()
                .filter(v -> v.name().equals(ComponentsProperties.ITEM.name()))
                .toList();
        if (!lisItem.isEmpty()) {
            var index = 1;
            this.interfaceGame.setItensJLabel(lisItem, index);
        }

        this.interfaceGame.clearJLabelDecoration();
        var decorations = action.components().stream()
                .filter(v -> v.name().equals(ComponentsProperties.DECORATION.name()))
                .toList();
        if (!decorations.isEmpty()) {
            var index = 1;
            this.interfaceGame.setDecorationJLabel(decorations, index);
        }

        this.interfaceGame.getMapGameJLabel().repaint();

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
