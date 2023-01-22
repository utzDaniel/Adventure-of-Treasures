package repository;

import javax.swing.*;

public enum ImagePlayer {

    CIMA('C'){
        @Override
        public ImageIcon select() {
            CIMA.position = updatePosition(CIMA.position);
            String cima = String.format("cima_%s.png",CIMA.position);
            return new ImageIcon(filename + cima);
        }
    },

    BAIXO('C'){
        @Override
        public ImageIcon select() {
            BAIXO.position = updatePosition(BAIXO.position);
            String baixo = String.format("baixo_%s.png",BAIXO.position);
            return new ImageIcon(filename + baixo);
        }
    },
    DIREITA('C') {
        @Override
        public ImageIcon select() {
            DIREITA.position = updatePosition(DIREITA.position);
            String direita = String.format("direita_%s.png",DIREITA.position);
            return new ImageIcon(filename + direita);
        }
    },
    ESQUERDA('C') {
        @Override
        public ImageIcon select() {
            ESQUERDA.position = updatePosition(ESQUERDA.position);
            String esquerda = String.format("esquerda_%s.png",ESQUERDA.position);
            return new ImageIcon(filename + esquerda);
        }
    };

    static private final String filename = "src\\main\\java\\repository\\player\\";
    static private final String POSITIONS = "ECD";
    private char position;

    ImagePlayer(char position) {
        this.position = position;
    }

    static private char updatePosition(char position){
        return switch (position) {
            case 'C' -> POSITIONS.charAt(2);
            case 'D' -> POSITIONS.charAt(0);
            default -> POSITIONS.charAt(1);
        };
    }

    public abstract ImageIcon select();
}
