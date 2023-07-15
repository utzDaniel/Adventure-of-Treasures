package backend.service.enums;

import javax.swing.*;

public enum ImagePlayer {

    CIMA {
        @Override
        public ImageIcon select() {
            char pos = CIMA.position == 'A' ? 'C' : CIMA.position;
            return new ImageIcon(filename + String.format("cima_%s.png", pos));
        }
    },
    BAIXO {
        @Override
        public ImageIcon select() {
            char pos = BAIXO.position == 'A' ? 'C' : BAIXO.position;
            return new ImageIcon(filename + String.format("baixo_%s.png", pos));
        }
    },
    DIREITA {
        @Override
        public ImageIcon select() {
            char pos = DIREITA.position == 'A' ? 'C' : DIREITA.position;
            return new ImageIcon(filename + String.format("direita_%s.png", pos));
        }
    },
    ESQUERDA {
        @Override
        public ImageIcon select() {
            char pos = ESQUERDA.position == 'A' ? 'C' : ESQUERDA.position;
            return new ImageIcon(filename + String.format("esquerda_%s.png", pos));
        }
    };

    private static final String filename = "src/main/resources/player/";
    private static final String POSITIONS = "ECDA";
    private char position;

    ImagePlayer() {
        this.position = 'C';
    }

    public void run() {
        this.position = switch (this.position) {
            case 'C' -> POSITIONS.charAt(2);
            case 'D' -> POSITIONS.charAt(3);
            case 'A' -> POSITIONS.charAt(0);
            default -> POSITIONS.charAt(1);
        };
    }

    public abstract ImageIcon select();
}
