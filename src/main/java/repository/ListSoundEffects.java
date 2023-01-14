package repository;

public class ListSoundEffects {

    private String filename;

    public ListSoundEffects() {
        this.filename = "src\\main\\java\\repository\\audio\\effects\\";
    }

    public String commandSoundEffects(String command) {
        return filename +=
                switch (command) {
                    case "pegar" -> "pegar.wav";
                    case "remover" -> "remover.wav";
                    case "finish" -> "finish.wav";
                    default -> "erro.wav";
                };
    }

    public String itensSoundEffects(String command, String nameItem) {
        return filename += switch (command) {
            case "usar" -> switch (nameItem) {
                case "chave" -> "chave.wav";
                case "escada" -> "escada.wav";
                case "pa" -> "pa.wav";
                case "tesouro" -> "saida.wav";
                default -> "erro.wav";
            };
            case "combinar" -> switch (nameItem) {
                case "papel", "livro" -> "mapa.wav";
                case "madeiras", "pregos", "martelo" -> "construir.wav";
                case "madeira", "faca", "pederneira", "frasco" -> "fogo.wav";
                default -> "erro.wav";
            };
            case "equipar" -> switch (nameItem) {
                case "mochila" -> "mochila.wav";
                case "tocha" -> "tocha.wav";
                default -> "erro.wav";
            };
            default -> "erro.wav";
        };
    }
}