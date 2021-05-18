public class ListSoundEffects {

    private String filename;

    public ListSoundEffects() {
        this.filename = "audio/effects/";
    }

    public String commandSoundEffects(String command) {
        if (command.equals("pegar")) {
            filename += "take.wav";
        }  else if (command.equals("remover")) {
            filename += "remover.wav";
        }else if (command.equals("finish")) {
            filename += "finish.wav";
        }else {
            filename += "erro.wav";
        }
        return filename;
    }

    public String listSoundEffects(String command, String nameItem) {
        switch (command) {
            case "usar":
                switch (nameItem) {
                    case "chave" -> filename += "chave.wav";
                    case "escada" -> filename += "escada.wav";
                    case "pa" -> filename += "pa.wav";
                    case "tesouro" -> filename += "saida.wav";
                    default -> filename += "erro.wav";
                }
                break;
            case "combinar":
                switch (nameItem) {
                    case "papel", "livro" -> filename += "mapa.wav";
                    case "madeiras", "pregos", "martelo" -> filename += "construir.wav";
                    case "madeira", "faca", "pederneira", "frasco" -> filename += "fogo.wav";
                    default -> filename += "erro.wav";
                }
                break;
            case "equipar":
                if (nameItem.equals("mochila")) {
                    filename += "mochila.wav";
                } else if (nameItem.equals("tocha")) {
                    filename += "tocha.wav";
                } else {
                    filename += "erro.wav";
                }
                break;
            default:
                filename += "erro.wav";
                break;
        }
        return filename;
    }
}