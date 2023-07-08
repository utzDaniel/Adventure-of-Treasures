package frontend.event;

import backend.controller.interfaces.IOpenResponse;
import backend.controller.interfaces.ITakeResponse;
import frontend.enums.Direction;
import frontend.event.ioc.ContainerIoC;
import frontend.event.protocolo.Request;
import frontend.event.reflexao.ManipuladorObjeto;
import frontend.event.reflexao.Reflexao;
import frontend.mapper.InventoryOpenMapper;
import frontend.mapper.MoveMapper;
import frontend.mapper.OpenMapper;
import frontend.mapper.TakeMapper;
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

                    var moveRes = executa(String.format("/eventAction/move?arg0=%s", direction));

                    var move = new MoveMapper().apply(moveRes);

                    if (move.message().sucess())
                        interfaceGame.updateComponentsMove(move);


                    //ENTRA NA PORTA
                } else if (keyCode == 97) {

                    var openRes = executa("/eventAction/open");
                    var open = new OpenMapper().apply(openRes);

                    if ("finish".equalsIgnoreCase(open.songMap())) finish(open.songMap());

                    if (Objects.nonNull(open.message())) {
                        if (open.message().sucess())
                            updateItensMapGame(open);
                        else if (Objects.nonNull(open.message().effect()))
                            soundEffects.play(commandEffects(open.message().effect()));
                    }


                    //PEGAR ITEM
                } else if (keyCode == 98) {

                    var takeRes = executa("/eventAction/take");
                    var take = new TakeMapper().apply(takeRes);

                    var effect = take.message().effect();
                    if (Objects.nonNull(effect) && !effect.isEmpty()) {
                        soundEffects.play(commandEffects(effect));
                    }

                    if (take.message().sucess()) updateItensMapGame(take);

                    //INVENTARIO
                } else if (keyCode == 99) {

                    var inventoryOpenRes = executa("/eventAction/inventoryOpen");
                    var inventoryOpen = new InventoryOpenMapper().apply(inventoryOpenRes);

                    if (inventoryOpen.message().sucess()) interfaceInventory.open(inventoryOpen);
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                // not implements
            }
        });
    }

    private void updateItensMapGame(IOpenResponse open) {
        this.interfaceGame.getMapGameJLabel().setIcon(new ImageIcon(open.iconMap()));
        this.interfaceGame.clearJLabelItens();
        this.interfaceGame.setItensJLabel(open.itens(), 1);
        this.interfaceGame.getMapGameJLabel().repaint();
        this.interfaceGame.getPlayerJLabel().setLocation(new Point(open.coordinatePlayer().x(),
                open.coordinatePlayer().y()));
    }

    private void updateItensMapGame(ITakeResponse take) {
        this.interfaceGame.getMapGameJLabel().setIcon(new ImageIcon(take.iconMap()));
        this.interfaceGame.clearJLabelItens();
        this.interfaceGame.setItensJLabel(take.itens(), 1);
        this.interfaceGame.getMapGameJLabel().repaint();
        this.interfaceGame.getPlayerJLabel().setLocation(new Point(take.coordinatePlayer().x(),
                take.coordinatePlayer().y()));
    }

    private void finish(String song) {
        this.song.closePlay();
        this.soundEffects.play(commandEffects(song));
        System.exit(0);
    }

    private String commandEffects(String command) {
        return String.format("src/main/resources/audio/effects/%s.wav",
                switch (command) {
                    case "pegar", "remover ", "finish" -> command;
                    default -> "erro";
                });
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
