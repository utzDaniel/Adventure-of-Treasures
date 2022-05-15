import javax.swing.*;
import java.util.Objects;

public class CreateImageItem {

    public ImageIcon selectImage(String name) {
        return switch (name) {
            case "chave" -> new ImageIcon("src/main/java/item/chave.png");
            case "escada" -> new ImageIcon("src/main/java/item/escada.png");
            case "faca" -> new ImageIcon("src/main/java/item/faca.png");
            case "frasco" -> new ImageIcon("src/main/java/item/frasco.png");
            case "livro" -> new ImageIcon("src/main/java/item/livro.png");
            case "madeira" -> new ImageIcon("src/main/java/item/madeira.png");
            case "madeiras" -> new ImageIcon("src/main/java/item/madeiras.png");
            case "mapa" -> new ImageIcon("src/main/java/item/mapa.png");
            case "martelo" -> new ImageIcon("src/main/java/item/martelo.png");
            case "mochila" -> new ImageIcon("src/main/java/item/mochila.png");
            case "pa" -> new ImageIcon("src/main/java/item/pa.png");
            case "papel" -> new ImageIcon("src/main/java/item/papel.png");
            case "pederneira" -> new ImageIcon("src/main/java/item/pederneira.png");
            case "pregos" -> new ImageIcon("src/main/java/item/pregos.png");
            case "tesouro" -> new ImageIcon("src/main/java/item/tesouro.png");
            case "tocha" -> new ImageIcon("src/main/java/item/tocha.png");
            default -> null;
        };
    }
}
