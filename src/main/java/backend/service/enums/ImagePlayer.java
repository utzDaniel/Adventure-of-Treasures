package backend.service.enums;

public enum ImagePlayer {

    CIMA {
        @Override
        public String select() {
            char pos = CIMA.position == 'A' ? 'C' : CIMA.position;
            return String.format("src/main/resources/image/player/cima_%s.png", pos);
        }
    },
    BAIXO {
        @Override
        public String select() {
            char pos = BAIXO.position == 'A' ? 'C' : BAIXO.position;
            return String.format("src/main/resources/image/player/baixo_%s.png", pos);
        }
    },
    DIREITA {
        @Override
        public String select() {
            char pos = DIREITA.position == 'A' ? 'C' : DIREITA.position;
            return String.format("src/main/resources/image/player/direita_%s.png", pos);
        }
    },
    ESQUERDA {
        @Override
        public String select() {
            char pos = ESQUERDA.position == 'A' ? 'C' : ESQUERDA.position;
            return String.format("src/main/resources/image/player/esquerda_%s.png", pos);
        }
    };

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
