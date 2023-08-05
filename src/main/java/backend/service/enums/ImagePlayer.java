package backend.service.enums;

public enum ImagePlayer {

    CIMA {
        @Override
        public String select() {
            char pos = CIMA.position == 'A' ? 'C' : CIMA.position;
            return filename + String.format("cima_%s.png", pos);
        }
    },
    BAIXO {
        @Override
        public String select() {
            char pos = BAIXO.position == 'A' ? 'C' : BAIXO.position;
            return filename + String.format("baixo_%s.png", pos);
        }
    },
    DIREITA {
        @Override
        public String select() {
            char pos = DIREITA.position == 'A' ? 'C' : DIREITA.position;
            return filename + String.format("direita_%s.png", pos);
        }
    },
    ESQUERDA {
        @Override
        public String select() {
            char pos = ESQUERDA.position == 'A' ? 'C' : ESQUERDA.position;
            return filename + String.format("esquerda_%s.png", pos);
        }
    };

    private static final String filename = "src/main/resources/image/player/";
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

    public abstract String select();
}
