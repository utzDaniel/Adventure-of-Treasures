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

                    if (message.sucess())
                        interfaceGame.updateComponentsMove(action);


                    //ENTRA NA PORTA
                } else if (keyCode == 97) {

                    var actionRes = executa("/action/open");

                    var message = new MessageMapper().apply(actionRes);
                    var action = new ActionMapper().apply(actionRes);

                    if (Objects.isNull(message)) return;

                    var effect = message.effect();

                    if (message.sucess())
                        updateItensMapGame(action);

                    if (Objects.nonNull(effect))
                        soundEffects.play(effect);


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
                } else if (keyCode == 96) {

                    var inventoryOpenRes = executa("/inventory/open");
                    var message = new MessageMapper().apply(inventoryOpenRes);
                    var inventoryOpen = new InventoryMapper().apply(inventoryOpenRes);

                    if (message.sucess()) interfaceInventory.open(inventoryOpen);

                } else if (keyCode == 99) {

                    var actionRes = executa("/action/interact");

                    var message = new MessageMapper().apply(actionRes);
                    var action = new ActionMapper().apply(actionRes);

                    if (Objects.isNull(message)) return;

                    var effect = message.effect();

                    if (effect.contains("finish")) {
                        finish(effect);
                    }

                    if (message.sucess())
                        updateItensMapGame(action);

                    if (Objects.nonNull(effect))
                        soundEffects.play(effect);

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
            } else if (info.name().equals(ComponentsProperties.MAPA.name())) {
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
